/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.cameraview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.CamcorderProfile;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;

import eu.long1.nativecamera.proto.CameraState;

public class CameraImplementation {

  CameraViewImpl mImpl;

  private final CallbackBridge mCallbacks;

  private Context mContext;

  protected HandlerThread mBgThread;
  protected Handler mBgHandler;

  @SuppressWarnings("WrongConstant")
  public CameraImplementation(Context context, boolean fallbackToOldApi, SurfaceTexture surfaceTexture) {
    // bg hanadler for non UI heavy work
    mBgThread = new HandlerThread("RNCamera-Handler-Thread");
    mBgThread.start();
    mBgHandler = new Handler(mBgThread.getLooper());

    mContext = context;

    // Internal setup
    final PreviewImpl preview = createPreviewImpl(surfaceTexture);
    mCallbacks = new CallbackBridge();
    if (fallbackToOldApi || Build.VERSION.SDK_INT < 21 || Camera2.isLegacy(context)) {
      mImpl = new Camera1(mCallbacks, preview, mBgHandler);
    } else if (Build.VERSION.SDK_INT < 23) {
      mImpl = new Camera2(mCallbacks, preview, context, mBgHandler);
    } else {
      mImpl = new Camera2Api23(mCallbacks, preview, context, mBgHandler);
    }
  }

  public void cleanup() {
    if (mBgThread != null) {
      if (Build.VERSION.SDK_INT < 18) {
        mBgThread.quit();
      } else {
        mBgThread.quitSafely();
      }

      mBgThread = null;
    }
  }

  @NonNull
  private PreviewImpl createPreviewImpl(SurfaceTexture surfaceTexture) {
    return new TextureViewPreview(surfaceTexture);
  }

    /*
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            mDisplayOrientationDetector.enable(ViewCompat.getDisplay(this));
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (!isInEditMode()) {
            mDisplayOrientationDetector.disable();
        }
        super.onDetachedFromWindow();
    }
    */

  public boolean getUsingCamera2Api() {
    return mImpl instanceof Camera2;
  }

  public void setUsingCamera2Api(boolean useCamera2) {
    if (Build.VERSION.SDK_INT < 21) {
      return;
    }

    boolean wasOpened = isCameraOpened();

    if (useCamera2 && !Camera2.isLegacy(mContext)) {
      if (wasOpened) {
        stop();
      }
      if (Build.VERSION.SDK_INT < 23) {
        mImpl = new Camera2(mCallbacks, mImpl.mPreview, mContext, mBgHandler);
      } else {
        mImpl = new Camera2Api23(mCallbacks, mImpl.mPreview, mContext, mBgHandler);
      }
    } else {
      if (mImpl instanceof Camera1) {
        return;
      }

      if (wasOpened) {
        stop();
      }
      mImpl = new Camera1(mCallbacks, mImpl.mPreview, mBgHandler);
    }
    if (wasOpened) {
      start();
    }
  }

  /**
   * Open a camera device and start showing camera preview. This is typically called from
   */
  public boolean start() {
    return mImpl.start();
  }

  /**
   * Stop camera preview and close the device. This is typically called from
   */
  public void stop() {
    mImpl.stop();
  }

  /**
   * @return {@code true} if the camera is opened.
   */
  public boolean isCameraOpened() {
    return mImpl.isCameraOpened();
  }

  /**
   * Add a new callback.
   *
   * @param callback The {@link Callback} to add.
   * @see #removeCallback(Callback)
   */
  public void addCallback(@NonNull Callback callback) {
    mCallbacks.add(callback);
  }

  /**
   * Remove a callback.
   *
   * @param callback The {@link Callback} to remove.
   * @see #addCallback(Callback)
   */
  public void removeCallback(@NonNull Callback callback) {
    mCallbacks.remove(callback);
  }

  /**
   * Chooses camera by its camera iD
   *
   * @param id The camera ID
   */
  public void setCameraId(String id) {
    mImpl.setCameraId(id);
  }

  /**
   * Gets the currently set camera ID
   *
   * @return The camera facing.
   */
  public String getCameraId() {
    return mImpl.getCameraId();
  }

  /**
   * Gets all the aspect ratios supported by the current camera.
   */
  public Set<AspectRatio> getSupportedAspectRatios() {
    return mImpl.getSupportedAspectRatios();
  }

  /**
   * Gets all the camera IDs supported by the phone as a String
   */
  public List<Properties> getCameraIds() {
    return mImpl.getCameraIds();
  }

  /**
   * Gets the current aspect ratio of camera.
   *
   * @return The current {@link AspectRatio}. Can be {@code null} if no camera is opened yet.
   */
  @Nullable
  public AspectRatio getAspectRatio() {
    return mImpl.getAspectRatio();
  }

  /**
   * Gets the current aspect ratio of camera.
   *
   * @return The current {@link AspectRatio}. Can be {@code null} if no camera is opened yet.
   */
  public boolean setAspectRatio(AspectRatio ratio) {
    return mImpl.setAspectRatio(ratio);
  }

  /**
   * Gets all the picture sizes for particular ratio supported by the current camera.
   *
   * @param ratio {@link AspectRatio} for which the available image sizes will be returned.
   */
  public SortedSet<Size> getAvailablePictureSizes(@NonNull AspectRatio ratio) {
    return mImpl.getAvailablePictureSizes(ratio);
  }

  /**
   * Sets the size of taken pictures.
   *
   * @param size The {@link Size} to be set.
   */
  public void setPictureSize(Size size) {
    mImpl.setPictureSize(size);
  }

  /**
   * Gets the size of pictures that will be taken.
   */
  public Size getPictureSize() {
    return mImpl.getPictureSize();
  }

  /**
   * Enables or disables the continuous auto-focus mode. When the current camera doesn't support
   * auto-focus, calling this method will be ignored.
   *
   * @param autoFocus {@code true} to enable continuous auto-focus mode. {@code false} to
   *                  disable it.
   */
  public void setAutoFocus(boolean autoFocus) {
    mImpl.setAutoFocus(autoFocus);
  }

  /**
   * Returns whether the continuous auto-focus mode is enabled.
   *
   * @return {@code true} if the continuous auto-focus mode is enabled. {@code false} if it is
   * disabled, or if it is not supported by the current camera.
   */
  public boolean getAutoFocus() {
    return mImpl.getAutoFocus();
  }

  /**
   * Sets the flash mode.
   *
   * @param flash The desired flash mode.
   */
  public void setFlash(CameraState.Flash flash) {
    System.out.println(flash);
    mImpl.setFlash(flash);
  }

  public ArrayList<int[]> getSupportedPreviewFpsRange() {
    return mImpl.getSupportedPreviewFpsRange();
  }

  /**
   * Gets the current flash mode.
   *
   * @return The current flash mode.
   */
  public CameraState.Flash getFlash() {
    return mImpl.getFlash();
  }

  public void setExposureCompensation(float exposure) {
    mImpl.setExposureCompensation(exposure);
  }

  public float getExposureCompensation() {
    return mImpl.getExposureCompensation();
  }


  /**
   * Gets the camera orientation relative to the devices native orientation.
   *
   * @return The orientation of the camera.
   */
  public CameraState.Orientation getCameraOrientation() {
    return mImpl.getCameraOrientation();
  }

  /**
   * Sets the auto focus point.
   *
   * @param x sets the x coordinate for camera auto focus
   * @param y sets the y coordinate for camera auto focus
   */
  public void setAutoFocusPointOfInterest(float x, float y) {
    mImpl.setFocusArea(x, y);
  }

  public void setFocusDepth(float value) {
    mImpl.setFocusDepth(value);
  }

  public float getFocusDepth() {
    return mImpl.getFocusDepth();
  }

  public void setZoom(float zoom) {
    mImpl.setZoom(zoom);
  }

  public float getZoom() {
    return mImpl.getZoom();
  }

  public void setWhiteBalance(CameraState.WhiteBalance whiteBalance) {
    mImpl.setWhiteBalance(whiteBalance);
  }

  public CameraState.WhiteBalance getWhiteBalance() {
    return mImpl.getWhiteBalance();
  }

  public void setPlaySoundOnCapture(boolean playSoundOnCapture) {
    mImpl.setPlaySoundOnCapture(playSoundOnCapture);
  }

  public boolean getPlaySoundOnCapture() {
    return mImpl.getPlaySoundOnCapture();
  }

  public void setScanning(boolean isScanning) {
    mImpl.setScanning(isScanning);
  }

  public boolean getScanning() {
    return mImpl.getScanning();
  }

  /**
   * Take a picture. The result will be returned to
   * {@link Callback#onPictureTaken(CameraImplementation, byte[], CameraState.Orientation)}.
   */
  public void takePicture(Map<String, Object> options) {
    mImpl.takePicture(options);
  }

  /**
   * Record a video and save it to file. The result will be returned to
   * {@link Callback#onVideoRecorded(CameraImplementation, String, CameraState.Orientation, CameraState.Orientation)}.
   *
   * @param path        Path to file that video will be saved to.
   * @param maxDuration Maximum duration of the recording, in seconds.
   * @param maxFileSize Maximum recording file size, in bytes.
   * @param profile     Quality profile of the recording.
   *                    <p>
   *                    fires {@link Callback#onRecordingStart(CameraImplementation, String, CameraState.Orientation, CameraState.Orientation)} and {@link Callback#onRecordingEnd(CameraImplementation)}.
   */
  public boolean record(String path, int maxDuration, int maxFileSize,
                        boolean recordAudio, CamcorderProfile profile, CameraState.Orientation orientation, int fps) {
    return mImpl.record(path, maxDuration, maxFileSize, recordAudio, profile, orientation, fps);
  }

  public void stopRecording() {
    mImpl.stopRecording();
  }

  public void resumePreview() {
    mImpl.resumePreview();
  }

  public void pausePreview() {
    mImpl.pausePreview();
  }

  public void setPreviewTexture(SurfaceTexture surfaceTexture) {
    mImpl.setPreviewTexture(surfaceTexture);
  }

  public Size getPreviewSize() {
    return mImpl.getPreviewSize();
  }

  private class CallbackBridge implements CameraViewImpl.Callback {

    private final ArrayList<Callback> mCallbacks = new ArrayList<>();

    private boolean mRequestLayoutOnOpen;

    CallbackBridge() {
    }

    public void add(Callback callback) {
      mCallbacks.add(callback);
    }

    public void remove(Callback callback) {
      mCallbacks.remove(callback);
    }

    @Override
    public void onCameraOpened() {
      if (mRequestLayoutOnOpen) {
        mRequestLayoutOnOpen = false;
      }
      for (Callback callback : mCallbacks) {
        callback.onCameraOpened(CameraImplementation.this);
      }
    }

    @Override
    public void onCameraClosed() {
      for (Callback callback : mCallbacks) {
        callback.onCameraClosed(CameraImplementation.this);
      }
    }

    @Override
    public void onPictureTaken(byte[] data, CameraState.Orientation deviceOrientation) {
      for (Callback callback : mCallbacks) {
        callback.onPictureTaken(CameraImplementation.this, data, deviceOrientation);
      }
    }

    @Override
    public void onRecordingStart(String path, CameraState.Orientation videoOrientation, CameraState.Orientation deviceOrientation) {
      for (Callback callback : mCallbacks) {
        callback.onRecordingStart(CameraImplementation.this, path, videoOrientation, deviceOrientation);
      }
    }

    @Override
    public void onRecordingEnd() {
      for (Callback callback : mCallbacks) {
        callback.onRecordingEnd(CameraImplementation.this);
      }
    }

    @Override
    public void onVideoRecorded(String path, CameraState.Orientation videoOrientation, CameraState.Orientation deviceOrientation) {
      for (Callback callback : mCallbacks) {
        callback.onVideoRecorded(CameraImplementation.this, path, videoOrientation, deviceOrientation);
      }
    }

    @Override
    public void onFramePreview(byte[] data, int width, int height, CameraState.Orientation orientation) {
      for (Callback callback : mCallbacks) {
        callback.onFramePreview(CameraImplementation.this, data, width, height, orientation);
      }
    }

    @Override
    public void onMountError() {
      for (Callback callback : mCallbacks) {
        callback.onMountError(CameraImplementation.this);
      }
    }

    public void reserveRequestLayoutOnOpen() {
      mRequestLayoutOnOpen = true;
    }
  }

  /**
   * Callback for monitoring events about {@link CameraImplementation}.
   */
  @SuppressWarnings("UnusedParameters")
  public abstract static class Callback {

    /**
     * Called when camera is opened.
     *
     * @param cameraController The associated {@link CameraImplementation}.
     */
    public void onCameraOpened(CameraImplementation cameraController) {
    }

    /**
     * Called when camera is closed.
     *
     * @param cameraController The associated {@link CameraImplementation}.
     */
    public void onCameraClosed(CameraImplementation cameraController) {
    }

    /**
     * Called when a picture is taken.
     *
     * @param cameraController The associated {@link CameraImplementation}.
     * @param data             JPEG data.
     */
    public void onPictureTaken(CameraImplementation cameraController, byte[] data, CameraState.Orientation deviceOrientation) {
    }

    /**
     * Called when a video recording starts
     *
     * @param cameraController The associated {@link CameraImplementation}.
     * @param path             Path to recoredd video file.
     */
    public void onRecordingStart(CameraImplementation cameraController, String path, CameraState.Orientation videoOrientation, CameraState.Orientation deviceOrientation) {
    }

    /**
     * Called when a video recording ends, but before video is saved/processed.
     *
     * @param cameraController The associated {@link CameraImplementation}.
     */
    public void onRecordingEnd(CameraImplementation cameraController) {
    }

    /**
     * Called when a video is recorded.
     *
     * @param cameraController The associated {@link CameraImplementation}.
     * @param path             Path to recoredd video file.
     */
    public void onVideoRecorded(CameraImplementation cameraController, String path, CameraState.Orientation videoOrientation, CameraState.Orientation deviceOrientation) {
    }

    public void onFramePreview(CameraImplementation cameraController, byte[] data, int width, int height, CameraState.Orientation orientation) {
    }

    public void onMountError(CameraImplementation cameraController) {
    }
  }

}
