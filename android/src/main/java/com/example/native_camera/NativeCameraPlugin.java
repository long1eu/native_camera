package com.example.native_camera;

import android.Manifest;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.cameraview.AspectRatio;
import com.google.android.cameraview.CameraImplementation;
import com.google.android.cameraview.Size;

import org.reactnative.camera.Constants;
import org.reactnative.camera.DartMessenger;
import org.reactnative.camera.RNCameraController;
import org.reactnative.camera.utils.ScopedContext;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.view.TextureRegistry;

/**
 * NativeCameraPlugin
 */
public class NativeCameraPlugin implements FlutterPlugin, MethodCallHandler {

    private TextureRegistry textureRegistry;
    private BinaryMessenger binaryMessenger;
    private ScopedContext mScopedContext;
    private MethodChannel channel;
    private RNCameraController controller;

    public static final int VIDEO_2160P = 0;
    public static final int VIDEO_1080P = 1;
    public static final int VIDEO_720P = 2;
    public static final int VIDEO_480P = 3;
    public static final int VIDEO_4x3 = 4;

    @Nullable
    public Map<String, Object> getConstants() {
        return Collections.unmodifiableMap(new HashMap<String, Object>() {
            {
                put("Type", getTypeConstants());
                put("FlashMode", getFlashModeConstants());
                put("AutoFocus", getAutoFocusConstants());
                put("WhiteBalance", getWhiteBalanceConstants());
                put("VideoQuality", getVideoQualityConstants());
                put("Orientation", Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("auto", Constants.ORIENTATION_AUTO);
                        put("portrait", Constants.ORIENTATION_UP);
                        put("portraitUpsideDown", Constants.ORIENTATION_DOWN);
                        put("landscapeLeft", Constants.ORIENTATION_LEFT);
                        put("landscapeRight", Constants.ORIENTATION_RIGHT);
                    }
                }));
            }

            private Map<String, Object> getTypeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("front", Constants.FACING_FRONT);
                        put("back", Constants.FACING_BACK);
                    }
                });
            }

            private Map<String, Object> getFlashModeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("off", Constants.FLASH_OFF);
                        put("on", Constants.FLASH_ON);
                        put("auto", Constants.FLASH_AUTO);
                        put("torch", Constants.FLASH_TORCH);
                    }
                });
            }

            private Map<String, Object> getAutoFocusConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("on", true);
                        put("off", false);
                    }
                });
            }

            private Map<String, Object> getWhiteBalanceConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("auto", Constants.WB_AUTO);
                        put("cloudy", Constants.WB_CLOUDY);
                        put("sunny", Constants.WB_SUNNY);
                        put("shadow", Constants.WB_SHADOW);
                        put("fluorescent", Constants.WB_FLUORESCENT);
                        put("incandescent", Constants.WB_INCANDESCENT);
                    }
                });
            }

            private Map<String, Object> getVideoQualityConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("2160p", VIDEO_2160P);
                        put("1080p", VIDEO_1080P);
                        put("720p", VIDEO_720P);
                        put("480p", VIDEO_480P);
                        put("4:3", VIDEO_4x3);
                    }
                });
            }
        });
    }


    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
        mScopedContext = new ScopedContext(binding.getApplicationContext());
        textureRegistry = binding.getTextureRegistry();
        binaryMessenger = binding.getBinaryMessenger();
        channel = new MethodChannel(binding.getBinaryMessenger(), "native_camera");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    @Override
    @SuppressWarnings({"unchecked", "ConstantConditions"})
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        switch (call.method) {
            case "initialize":
                instantiateCamera((Map<String, Object>) call.arguments, result);
                break;
            case "start":
                controller.start();
                final Size _size = controller.getPreviewSize();
                Map<String, Object> map = new HashMap<>();
                map.put("previewWidth", _size.getHeight());
                map.put("previewHeight", _size.getHeight());
                result.success(map);
                break;
            case "pausePreview":
                pausePreview();
                result.success(null);
                break;
            case "resumePreview":
                resumePreview();
                result.success(null);
                break;
            case "takePicture":
                takePicture((Map<String, Object>) call.arguments, result);
                break;
            case "record":
                record((Map<String, Object>) call.arguments, result);
                break;
            case "stopRecording":
                stopRecording();
                result.success(null);
                break;
            case "getSupportedRatios":
                getSupportedRatios(result);
                break;
            case "getCameraIds":
                getCameraIds(result);
                break;
            case "getAvailablePictureSizes":
                getAvailablePictureSizes((String) call.arguments, result);
                break;
            case "checkIfRecordAudioPermissionsAreDefined":
                checkIfRecordAudioPermissionsAreDefined(result);
                break;
            case "getSupportedPreviewFpsRange":
                getSupportedPreviewFpsRange(result);
                break;
            case "type":
                @CameraImplementation.Facing int type = (int) call.arguments;
                controller.setFacing(type);
                break;
            case "cameraId":
                String id = (String) call.arguments;
                controller.setCameraId(id);
                break;
            case "flashMode":
                @CameraImplementation.Flash int flash = (int) call.arguments;
                controller.setFlash(flash);
                break;
            case "exposure":
                float exposure = (float) call.arguments;
                controller.setExposureCompensation(exposure);
                break;
            case "autoFocus":
                boolean autoFocus = (boolean) call.arguments;
                controller.setAutoFocus(autoFocus);
                break;
            case "focusDepth":
                float focusDepth = (float) call.arguments;
                controller.setFocusDepth(focusDepth);
                break;
            case "autoFocusPointOfInterest":
                float x = call.argument("x");
                float y = call.argument("y");
                controller.setAutoFocusPointOfInterest(x, y);
                break;
            case "zoom":
                float zoom = (float) call.arguments;
                controller.setZoom(zoom);
                break;
            case "whiteBalance":
                int whiteBalance = (int) call.arguments;
                controller.setWhiteBalance(whiteBalance);
                break;
            case "pictureSize":
                String size = (String) call.arguments;
                controller.setPictureSize(size.equals("None") ? null : Size.parse(size));
                break;
            case "playSoundOnCapture":
                boolean playSoundOnCapture = (boolean) call.arguments;
                controller.setPlaySoundOnCapture(playSoundOnCapture);
                break;
            case "useCamera2Api":
                boolean useCamera2Api = (boolean) call.arguments;
                controller.setUsingCamera2Api(useCamera2Api);
                break;
            default:
                result.notImplemented();
                break;
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void instantiateCamera(Map<String, Object> options, MethodChannel.Result result) {
        TextureRegistry.SurfaceTextureEntry flutterSurfaceTexture = textureRegistry.createSurfaceTexture();
        DartMessenger dartMessenger = new DartMessenger(binaryMessenger, flutterSurfaceTexture.id());
        SurfaceTexture surfaceTexture = flutterSurfaceTexture.surfaceTexture();
        controller = new RNCameraController(mScopedContext.getContext(), dartMessenger, surfaceTexture);

        Boolean enableAudio = (Boolean) options.get("enableAudio");
        controller.setPlaySoundOnCapture(enableAudio);

        Map<String, Object> map = new HashMap<>();
        map.put("textureId", flutterSurfaceTexture.id());
        result.success(map);
    }

    public void pausePreview() {
        if (controller.isCameraOpened()) {
            controller.pausePreview();
        }
    }


    public void resumePreview() {
        if (controller.isCameraOpened()) {
            controller.resumePreview();
        }
    }


    public void takePicture(final Map<String, Object> options, final MethodChannel.Result promise) {
        try {
            final File cacheDirectory = mScopedContext.getCacheDirectory();
            if (controller.isCameraOpened()) {
                controller.takePicture(options, promise, cacheDirectory);
            } else {
                promise.error("E_CAMERA_UNAVAILABLE", "Camera is not running", null);
            }
        } catch (Exception e) {
            promise.error("E_TAKE_PICTURE_FAILED", e.getMessage(), null);
        }
    }


    public void record(final Map<String, Object> options, final MethodChannel.Result promise) {
        final File cacheDirectory = mScopedContext.getCacheDirectory();
        try {
            if (controller.isCameraOpened()) {
                controller.record(options, promise, cacheDirectory);
            } else {
                promise.error("E_CAMERA_UNAVAILABLE", "Camera is not running", controller);
            }
        } catch (Exception e) {
            promise.error("E_CAPTURE_FAILED", e.getMessage(), controller);
        }
    }


    public void stopRecording() {
        if (controller.isCameraOpened()) {
            controller.stopRecording();
        }
    }


    public void getSupportedRatios(final MethodChannel.Result promise) {
        ArrayList<String> result = new ArrayList<>();
        if (controller.isCameraOpened()) {
            Set<AspectRatio> ratios = controller.getSupportedAspectRatios();
            for (AspectRatio ratio : ratios) {
                result.add(ratio.toString());
            }
            promise.success(result);
        } else {
            promise.error("E_CAMERA_UNAVAILABLE", "Camera is not running", null);
        }
    }


    public void getCameraIds(final MethodChannel.Result promise) {
        try {
            ArrayList<Map<String, Object>> result = new ArrayList<>();
            List<Properties> ids = controller.getCameraIds();
            for (Properties p : ids) {
                Map<String, Object> m = new HashMap<>();
                m.put("id", p.getProperty("id"));
                m.put("type", Integer.valueOf(p.getProperty("type")));
                result.add(m);
            }
            promise.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            promise.error("E_CAMERA_FAILED", e.getMessage(), null);
        }
    }


    public void getAvailablePictureSizes(final String ratio, final MethodChannel.Result promise) {
        try {
            ArrayList<String> result = new ArrayList<>();
            if (controller.isCameraOpened()) {
                SortedSet<Size> sizes = controller.getAvailablePictureSizes(AspectRatio.parse(ratio));
                for (Size size : sizes) {
                    result.add(size.toString());
                }
                promise.success(result);
            } else {
                promise.error("E_CAMERA_UNAVAILABLE", "Camera is not running", null);
            }
        } catch (Exception e) {
            promise.error("E_CAMERA_BAD_VIEWTAG", "getAvailablePictureSizesAsync: Expected a Camera component", null);
        }
    }


    public void checkIfRecordAudioPermissionsAreDefined(final MethodChannel.Result promise) {
        try {
            PackageInfo info = mScopedContext.getPackageManager().getPackageInfo(mScopedContext.getContext().getPackageName(), PackageManager.GET_PERMISSIONS);
            if (info.requestedPermissions != null) {
                for (String p : info.requestedPermissions) {
                    if (p.equals(Manifest.permission.RECORD_AUDIO)) {
                        promise.success(true);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        promise.success(false);
    }


    public void getSupportedPreviewFpsRange(final MethodChannel.Result promise) {
        try {
            ArrayList<Map<String, Object>> result = new ArrayList<>();
            ArrayList<int[]> ranges = controller.getSupportedPreviewFpsRange();
            for (int[] range : ranges) {
                HashMap<String, Object> m = new HashMap<>();
                m.put("MAXIMUM_FPS", range[0]);
                m.put("MINIMUM_FPS", range[1]);
                result.add(m);
            }
            promise.success(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
