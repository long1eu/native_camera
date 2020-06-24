// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: models.proto

package eu.long1.nativecamera.proto;

public interface CameraStateOrBuilder extends
    // @@protoc_insertion_point(interface_extends:CameraState)
    com.google.protobuf.MessageLiteOrBuilder {

  /**
   * <code>float zoom = 1;</code>
   * @return The zoom.
   */
  float getZoom();

  /**
   * <pre>
   * Only used on iOS
   * </pre>
   *
   * <code>float max_zoom = 2;</code>
   * @return The maxZoom.
   */
  float getMaxZoom();

  /**
   * <code>.AspectRatio ratio = 3;</code>
   * @return Whether the ratio field is set.
   */
  boolean hasRatio();
  /**
   * <code>.AspectRatio ratio = 3;</code>
   * @return The ratio.
   */
  eu.long1.nativecamera.proto.AspectRatio getRatio();

  /**
   * <code>float focus_depth = 4;</code>
   * @return The focusDepth.
   */
  float getFocusDepth();

  /**
   * <code>string camera_id = 5;</code>
   * @return The cameraId.
   */
  java.lang.String getCameraId();
  /**
   * <code>string camera_id = 5;</code>
   * @return The bytes for cameraId.
   */
  com.google.protobuf.ByteString
      getCameraIdBytes();

  /**
   * <code>bool auto_focus = 6;</code>
   * @return The autoFocus.
   */
  boolean getAutoFocus();

  /**
   * <code>.CameraState.Flash flash = 7;</code>
   * @return The enum numeric value on the wire for flash.
   */
  int getFlashValue();
  /**
   * <code>.CameraState.Flash flash = 7;</code>
   * @return The flash.
   */
  eu.long1.nativecamera.proto.CameraState.Flash getFlash();

  /**
   * <code>float exposure = 8;</code>
   * @return The exposure.
   */
  float getExposure();

  /**
   * <code>.CameraState.WhiteBalance white_balance = 9;</code>
   * @return The enum numeric value on the wire for whiteBalance.
   */
  int getWhiteBalanceValue();
  /**
   * <code>.CameraState.WhiteBalance white_balance = 9;</code>
   * @return The whiteBalance.
   */
  eu.long1.nativecamera.proto.CameraState.WhiteBalance getWhiteBalance();

  /**
   * <code>bool play_sound_on_capture = 10;</code>
   * @return The playSoundOnCapture.
   */
  boolean getPlaySoundOnCapture();

  /**
   * <pre>
   * Only used on Android &gt; 21
   * </pre>
   *
   * <code>bool use_camera_2 = 11;</code>
   * @return The useCamera2.
   */
  boolean getUseCamera2();
}