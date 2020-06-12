package org.reactnative.camera;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.media.CamcorderProfile;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.google.android.cameraview.CameraImplementation;

import org.reactnative.camera.events.CameraMountErrorEvent;
import org.reactnative.camera.events.CameraReadyEvent;
import org.reactnative.camera.events.PictureSavedEvent;
import org.reactnative.camera.events.PictureTakenEvent;
import org.reactnative.camera.events.RecordingEndEvent;
import org.reactnative.camera.events.RecordingStartEvent;
import org.reactnative.camera.tasks.PictureSavedDelegate;
import org.reactnative.camera.tasks.ResolveTakenPictureAsyncTask;
import org.reactnative.camera.utils.RNFileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.embedding.engine.plugins.lifecycle.FlutterLifecycleAdapter;
import io.flutter.plugin.common.MethodChannel;

@SuppressWarnings("ConstantConditions")
public class RNCameraController extends CameraImplementation implements ActivityAware, PictureSavedDelegate, LifecycleObserver {
    private Context mContext;
    private Queue<MethodChannel.Result> mPictureTakenPromises = new ConcurrentLinkedQueue<>();
    private Map<MethodChannel.Result, Map<String, Object>> mPictureTakenOptions = new ConcurrentHashMap<>();
    private Map<MethodChannel.Result, File> mPictureTakenDirectories = new ConcurrentHashMap<>();
    private MethodChannel.Result mVideoRecordedPromise;
    private DartMessenger mDartMessenger;
    private Lifecycle lifecycle;

    private boolean mIsPaused = false;
    private boolean mIsNew = true;
    private Boolean mIsRecording = false;
    private Boolean mIsRecordingInterrupted = false;

    public RNCameraController(Context context, DartMessenger dartMessenger, SurfaceTexture surfaceTexture) {
        super(context, true, surfaceTexture);
        mContext = context;
        mDartMessenger = dartMessenger;

        addCallback(new Callback() {
            @Override
            public void onCameraOpened(CameraImplementation cameraController) {
                CameraReadyEvent.obtain().dispatch(mDartMessenger.getEventSink());
            }

            @Override
            public void onMountError(CameraImplementation cameraController) {
                System.out.println("Camera view threw an error - component could not be rendered.");
                CameraMountErrorEvent.obtain("Camera view threw an error - component could not be rendered.").dispatch(mDartMessenger.getEventSink());
            }

            @SuppressWarnings("ConstantConditions")
            @Override
            public void onPictureTaken(CameraImplementation cameraController, final byte[] data, int deviceOrientation) {
                MethodChannel.Result promise = mPictureTakenPromises.poll();
                Map<String, Object> options = mPictureTakenOptions.remove(promise);
                if (options.containsKey("fastMode") && (Boolean) options.get("fastMode")) {
                    promise.success(null);
                }
                final File cacheDirectory = mPictureTakenDirectories.remove(promise);
                new ResolveTakenPictureAsyncTask(data, promise, options, cacheDirectory, deviceOrientation, RNCameraController.this)
                        .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                PictureTakenEvent.obtain().dispatch(mDartMessenger.getEventSink());
            }

            @Override
            public void onRecordingStart(CameraImplementation cameraController, String path, int videoOrientation, int deviceOrientation) {
                Map<String, Object> result = new HashMap<>();
                result.put("videoOrientation", videoOrientation);
                result.put("deviceOrientation", deviceOrientation);
                result.put("uri", RNFileUtils.uriFromFile(new File(path)).toString());
                RecordingStartEvent.obtain(result).dispatch(mDartMessenger.getEventSink());
            }

            @Override
            public void onRecordingEnd(CameraImplementation cameraController) {
                RecordingEndEvent.obtain().dispatch(mDartMessenger.getEventSink());
            }

            @Override
            public void onVideoRecorded(CameraImplementation cameraController, String path, int videoOrientation, int deviceOrientation) {
                if (mVideoRecordedPromise != null) {
                    if (path != null) {
                        Map<String, Object> result = new HashMap<>();
                        result.put("isRecordingInterrupted", mIsRecordingInterrupted);
                        result.put("videoOrientation", videoOrientation);
                        result.put("deviceOrientation", deviceOrientation);
                        result.put("uri", RNFileUtils.uriFromFile(new File(path)).toString());
                        mVideoRecordedPromise.success(result);
                    } else {
                        mVideoRecordedPromise.error("E_RECORDING", "Couldn't stop recording - there is none in progress", null);
                    }
                    mIsRecording = false;
                    mIsRecordingInterrupted = false;
                    mVideoRecordedPromise = null;
                }
            }

            @Override
            public void onFramePreview(CameraImplementation cameraController, byte[] data, int width, int height, int rotation) {
            }
        });
    }

    public void takePicture(final Map<String, Object> options, final MethodChannel.Result promise, final File cacheDirectory) {
        mBgHandler.post(() -> {
            mPictureTakenPromises.add(promise);
            mPictureTakenOptions.put(promise, options);
            mPictureTakenDirectories.put(promise, cacheDirectory);

            try {
                RNCameraController.super.takePicture(options);
            } catch (Exception e) {
                mPictureTakenPromises.remove(promise);
                mPictureTakenOptions.remove(promise);
                mPictureTakenDirectories.remove(promise);

                promise.error("E_TAKE_PICTURE_FAILED", e.getMessage(), null);
            }
        });
    }

    @Override
    public void onPictureSaved(Map<String, Object> response) {
        PictureSavedEvent.obtain(response).dispatch(mDartMessenger.getEventSink());
    }

    public void record(final Map<String, Object> options, final MethodChannel.Result promise, final File cacheDirectory) {
        mBgHandler.post(() -> {
            try {
                String path = options.containsKey("path") ? (String) options.get("path") : RNFileUtils.getOutputFilePath(cacheDirectory, ".mp4");
                int maxDuration = options.containsKey("maxDuration") ? (Integer) options.get("maxDuration") : -1;
                int maxFileSize = options.containsKey("maxFileSize") ? (Integer) options.get("maxFileSize") : -1;
                int fps = options.containsKey("fps") ? (Integer) options.get("fps") : -1;

                CamcorderProfile profile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
                if (options.containsKey("quality")) {
                    profile = RNCameraViewHelper.getCamcorderProfile((Integer) options.get("quality"));
                }
                if (options.containsKey("videoBitrate")) {
                    profile.videoBitRate = (Integer) options.get("videoBitrate");
                }

                boolean recordAudio = true;
                if (options.containsKey("mute")) {
                    recordAudio = !((Boolean) options.get("mute"));
                }

                int orientation = Constants.ORIENTATION_AUTO;
                if (options.containsKey("orientation")) {
                    orientation = (Integer) options.get("orientation");
                }

                if (RNCameraController.super.record(path, maxDuration * 1000, maxFileSize, recordAudio, profile, orientation, fps)) {
                    mIsRecording = true;
                    mVideoRecordedPromise = promise;
                } else {
                    promise.error("E_RECORDING_FAILED", "Starting video recording failed. Another recording might be in progress.", null);
                }
            } catch (IOException e) {
                promise.error("E_RECORDING_FAILED", "Starting video recording failed - could not create video file.", null);
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onHostResume() {
        if (hasCameraPermissions()) {
            mBgHandler.post(() -> {
                if ((mIsPaused && !isCameraOpened()) || mIsNew) {
                    mIsPaused = false;
                    mIsNew = false;
                    start();
                }
            });
        } else {
            CameraMountErrorEvent.obtain("Camera permissions not granted - component could not be rendered.").dispatch(mDartMessenger.getEventSink());
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onHostPause() {
        if (mIsRecording) {
            mIsRecordingInterrupted = true;
        }
        if (!mIsPaused && isCameraOpened()) {
            mIsPaused = true;
            stop();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onHostDestroy() {
        // camera release can be quite expensive. Run in on bg handler
        // and cleanup last once everything has finished
        mBgHandler.post(() -> {
            stop();
            cleanup();
        });
    }

    private boolean hasCameraPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA);
            return result == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        lifecycle = FlutterLifecycleAdapter.getActivityLifecycle(binding);
        lifecycle.addObserver(this);
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        lifecycle.removeObserver(this);
        lifecycle = null;
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        lifecycle = FlutterLifecycleAdapter.getActivityLifecycle(binding);
        lifecycle.addObserver(this);
    }

    @Override
    public void onDetachedFromActivity() {
        lifecycle.removeObserver(this);
        lifecycle = null;
    }
}
