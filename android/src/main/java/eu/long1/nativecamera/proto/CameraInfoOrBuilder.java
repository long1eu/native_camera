// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: models.proto

package eu.long1.nativecamera.proto;

public interface CameraInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:CameraInfo)
    com.google.protobuf.MessageLiteOrBuilder {

  /**
   * <code>string id = 1;</code>
   * @return The id.
   */
  java.lang.String getId();
  /**
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <code>.CameraInfo.LensFacing facing = 2;</code>
   * @return The enum numeric value on the wire for facing.
   */
  int getFacingValue();
  /**
   * <code>.CameraInfo.LensFacing facing = 2;</code>
   * @return The facing.
   */
  eu.long1.nativecamera.proto.CameraInfo.LensFacing getFacing();
}
