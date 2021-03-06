// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: native_camera.proto

package eu.long1.nativecamera.proto;

/**
 * Protobuf type {@code TakePictureResponse}
 */
public  final class TakePictureResponse extends
    com.google.protobuf.GeneratedMessageLite<
        TakePictureResponse, TakePictureResponse.Builder> implements
    // @@protoc_insertion_point(message_implements:TakePictureResponse)
    TakePictureResponseOrBuilder {
  private TakePictureResponse() {
    uri_ = "";
    data_ = com.google.protobuf.ByteString.EMPTY;
  }
  public static final int DEVICE_ORIENTATION_FIELD_NUMBER = 1;
  private int deviceOrientation_;
  /**
   * <code>.CameraState.Orientation device_orientation = 1;</code>
   * @return The enum numeric value on the wire for deviceOrientation.
   */
  @java.lang.Override
  public int getDeviceOrientationValue() {
    return deviceOrientation_;
  }
  /**
   * <code>.CameraState.Orientation device_orientation = 1;</code>
   * @return The deviceOrientation.
   */
  @java.lang.Override
  public eu.long1.nativecamera.proto.CameraState.Orientation getDeviceOrientation() {
    eu.long1.nativecamera.proto.CameraState.Orientation result = eu.long1.nativecamera.proto.CameraState.Orientation.forNumber(deviceOrientation_);
    return result == null ? eu.long1.nativecamera.proto.CameraState.Orientation.UNRECOGNIZED : result;
  }
  /**
   * <code>.CameraState.Orientation device_orientation = 1;</code>
   * @param value The enum numeric value on the wire for deviceOrientation to set.
   */
  private void setDeviceOrientationValue(int value) {
      deviceOrientation_ = value;
  }
  /**
   * <code>.CameraState.Orientation device_orientation = 1;</code>
   * @param value The deviceOrientation to set.
   */
  private void setDeviceOrientation(eu.long1.nativecamera.proto.CameraState.Orientation value) {
    deviceOrientation_ = value.getNumber();
    
  }
  /**
   * <code>.CameraState.Orientation device_orientation = 1;</code>
   */
  private void clearDeviceOrientation() {
    
    deviceOrientation_ = 0;
  }

  public static final int PICTURE_ORIENTATION_FIELD_NUMBER = 2;
  private int pictureOrientation_;
  /**
   * <code>.CameraState.Orientation picture_orientation = 2;</code>
   * @return The enum numeric value on the wire for pictureOrientation.
   */
  @java.lang.Override
  public int getPictureOrientationValue() {
    return pictureOrientation_;
  }
  /**
   * <code>.CameraState.Orientation picture_orientation = 2;</code>
   * @return The pictureOrientation.
   */
  @java.lang.Override
  public eu.long1.nativecamera.proto.CameraState.Orientation getPictureOrientation() {
    eu.long1.nativecamera.proto.CameraState.Orientation result = eu.long1.nativecamera.proto.CameraState.Orientation.forNumber(pictureOrientation_);
    return result == null ? eu.long1.nativecamera.proto.CameraState.Orientation.UNRECOGNIZED : result;
  }
  /**
   * <code>.CameraState.Orientation picture_orientation = 2;</code>
   * @param value The enum numeric value on the wire for pictureOrientation to set.
   */
  private void setPictureOrientationValue(int value) {
      pictureOrientation_ = value;
  }
  /**
   * <code>.CameraState.Orientation picture_orientation = 2;</code>
   * @param value The pictureOrientation to set.
   */
  private void setPictureOrientation(eu.long1.nativecamera.proto.CameraState.Orientation value) {
    pictureOrientation_ = value.getNumber();
    
  }
  /**
   * <code>.CameraState.Orientation picture_orientation = 2;</code>
   */
  private void clearPictureOrientation() {
    
    pictureOrientation_ = 0;
  }

  public static final int EXIF_FIELD_NUMBER = 3;
  private static final class ExifDefaultEntryHolder {
    static final com.google.protobuf.MapEntryLite<
        java.lang.String, java.lang.String> defaultEntry =
            com.google.protobuf.MapEntryLite
            .<java.lang.String, java.lang.String>newDefaultInstance(
                com.google.protobuf.WireFormat.FieldType.STRING,
                "",
                com.google.protobuf.WireFormat.FieldType.STRING,
                "");
  }
  private com.google.protobuf.MapFieldLite<
      java.lang.String, java.lang.String> exif_ =
          com.google.protobuf.MapFieldLite.emptyMapField();
  private com.google.protobuf.MapFieldLite<java.lang.String, java.lang.String>
  internalGetExif() {
    return exif_;
  }
  private com.google.protobuf.MapFieldLite<java.lang.String, java.lang.String>
  internalGetMutableExif() {
    if (!exif_.isMutable()) {
      exif_ = exif_.mutableCopy();
    }
    return exif_;
  }
  @java.lang.Override

  public int getExifCount() {
    return internalGetExif().size();
  }
  /**
   * <code>map&lt;string, string&gt; exif = 3;</code>
   */
  @java.lang.Override

  public boolean containsExif(
      java.lang.String key) {
    key.getClass();
    return internalGetExif().containsKey(key);
  }
  /**
   * Use {@link #getExifMap()} instead.
   */
  @java.lang.Override
  @java.lang.Deprecated
  public java.util.Map<java.lang.String, java.lang.String> getExif() {
    return getExifMap();
  }
  /**
   * <code>map&lt;string, string&gt; exif = 3;</code>
   */
  @java.lang.Override

  public java.util.Map<java.lang.String, java.lang.String> getExifMap() {
    return java.util.Collections.unmodifiableMap(
        internalGetExif());
  }
  /**
   * <code>map&lt;string, string&gt; exif = 3;</code>
   */
  @java.lang.Override

  public java.lang.String getExifOrDefault(
      java.lang.String key,
      java.lang.String defaultValue) {
    key.getClass();
    java.util.Map<java.lang.String, java.lang.String> map =
        internalGetExif();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  /**
   * <code>map&lt;string, string&gt; exif = 3;</code>
   */
  @java.lang.Override

  public java.lang.String getExifOrThrow(
      java.lang.String key) {
    key.getClass();
    java.util.Map<java.lang.String, java.lang.String> map =
        internalGetExif();
    if (!map.containsKey(key)) {
      throw new java.lang.IllegalArgumentException();
    }
    return map.get(key);
  }
  /**
   * <code>map&lt;string, string&gt; exif = 3;</code>
   */
  private java.util.Map<java.lang.String, java.lang.String>
  getMutableExifMap() {
    return internalGetMutableExif();
  }

  public static final int WIDTH_FIELD_NUMBER = 4;
  private int width_;
  /**
   * <code>int32 width = 4;</code>
   * @return The width.
   */
  @java.lang.Override
  public int getWidth() {
    return width_;
  }
  /**
   * <code>int32 width = 4;</code>
   * @param value The width to set.
   */
  private void setWidth(int value) {
    
    width_ = value;
  }
  /**
   * <code>int32 width = 4;</code>
   */
  private void clearWidth() {
    
    width_ = 0;
  }

  public static final int HEIGHT_FIELD_NUMBER = 5;
  private int height_;
  /**
   * <code>int32 height = 5;</code>
   * @return The height.
   */
  @java.lang.Override
  public int getHeight() {
    return height_;
  }
  /**
   * <code>int32 height = 5;</code>
   * @param value The height to set.
   */
  private void setHeight(int value) {
    
    height_ = value;
  }
  /**
   * <code>int32 height = 5;</code>
   */
  private void clearHeight() {
    
    height_ = 0;
  }

  public static final int URI_FIELD_NUMBER = 6;
  private java.lang.String uri_;
  /**
   * <code>string uri = 6;</code>
   * @return The uri.
   */
  @java.lang.Override
  public java.lang.String getUri() {
    return uri_;
  }
  /**
   * <code>string uri = 6;</code>
   * @return The bytes for uri.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getUriBytes() {
    return com.google.protobuf.ByteString.copyFromUtf8(uri_);
  }
  /**
   * <code>string uri = 6;</code>
   * @param value The uri to set.
   */
  private void setUri(
      java.lang.String value) {
    value.getClass();
  
    uri_ = value;
  }
  /**
   * <code>string uri = 6;</code>
   */
  private void clearUri() {
    
    uri_ = getDefaultInstance().getUri();
  }
  /**
   * <code>string uri = 6;</code>
   * @param value The bytes for uri to set.
   */
  private void setUriBytes(
      com.google.protobuf.ByteString value) {
    checkByteStringIsUtf8(value);
    uri_ = value.toStringUtf8();
    
  }

  public static final int DATA_FIELD_NUMBER = 7;
  private com.google.protobuf.ByteString data_;
  /**
   * <code>bytes data = 7;</code>
   * @return The data.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getData() {
    return data_;
  }
  /**
   * <code>bytes data = 7;</code>
   * @param value The data to set.
   */
  private void setData(com.google.protobuf.ByteString value) {
    value.getClass();
  
    data_ = value;
  }
  /**
   * <code>bytes data = 7;</code>
   */
  private void clearData() {
    
    data_ = getDefaultInstance().getData();
  }

  public static eu.long1.nativecamera.proto.TakePictureResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static eu.long1.nativecamera.proto.TakePictureResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static eu.long1.nativecamera.proto.TakePictureResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static eu.long1.nativecamera.proto.TakePictureResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static eu.long1.nativecamera.proto.TakePictureResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static eu.long1.nativecamera.proto.TakePictureResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static eu.long1.nativecamera.proto.TakePictureResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static eu.long1.nativecamera.proto.TakePictureResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static eu.long1.nativecamera.proto.TakePictureResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }
  public static eu.long1.nativecamera.proto.TakePictureResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static eu.long1.nativecamera.proto.TakePictureResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static eu.long1.nativecamera.proto.TakePictureResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return (Builder) DEFAULT_INSTANCE.createBuilder();
  }
  public static Builder newBuilder(eu.long1.nativecamera.proto.TakePictureResponse prototype) {
    return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
  }

  /**
   * Protobuf type {@code TakePictureResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        eu.long1.nativecamera.proto.TakePictureResponse, Builder> implements
      // @@protoc_insertion_point(builder_implements:TakePictureResponse)
      eu.long1.nativecamera.proto.TakePictureResponseOrBuilder {
    // Construct using eu.long1.nativecamera.proto.TakePictureResponse.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }


    /**
     * <code>.CameraState.Orientation device_orientation = 1;</code>
     * @return The enum numeric value on the wire for deviceOrientation.
     */
    @java.lang.Override
    public int getDeviceOrientationValue() {
      return instance.getDeviceOrientationValue();
    }
    /**
     * <code>.CameraState.Orientation device_orientation = 1;</code>
     * @param value The deviceOrientation to set.
     * @return This builder for chaining.
     */
    public Builder setDeviceOrientationValue(int value) {
      copyOnWrite();
      instance.setDeviceOrientationValue(value);
      return this;
    }
    /**
     * <code>.CameraState.Orientation device_orientation = 1;</code>
     * @return The deviceOrientation.
     */
    @java.lang.Override
    public eu.long1.nativecamera.proto.CameraState.Orientation getDeviceOrientation() {
      return instance.getDeviceOrientation();
    }
    /**
     * <code>.CameraState.Orientation device_orientation = 1;</code>
     * @param value The enum numeric value on the wire for deviceOrientation to set.
     * @return This builder for chaining.
     */
    public Builder setDeviceOrientation(eu.long1.nativecamera.proto.CameraState.Orientation value) {
      copyOnWrite();
      instance.setDeviceOrientation(value);
      return this;
    }
    /**
     * <code>.CameraState.Orientation device_orientation = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearDeviceOrientation() {
      copyOnWrite();
      instance.clearDeviceOrientation();
      return this;
    }

    /**
     * <code>.CameraState.Orientation picture_orientation = 2;</code>
     * @return The enum numeric value on the wire for pictureOrientation.
     */
    @java.lang.Override
    public int getPictureOrientationValue() {
      return instance.getPictureOrientationValue();
    }
    /**
     * <code>.CameraState.Orientation picture_orientation = 2;</code>
     * @param value The pictureOrientation to set.
     * @return This builder for chaining.
     */
    public Builder setPictureOrientationValue(int value) {
      copyOnWrite();
      instance.setPictureOrientationValue(value);
      return this;
    }
    /**
     * <code>.CameraState.Orientation picture_orientation = 2;</code>
     * @return The pictureOrientation.
     */
    @java.lang.Override
    public eu.long1.nativecamera.proto.CameraState.Orientation getPictureOrientation() {
      return instance.getPictureOrientation();
    }
    /**
     * <code>.CameraState.Orientation picture_orientation = 2;</code>
     * @param value The enum numeric value on the wire for pictureOrientation to set.
     * @return This builder for chaining.
     */
    public Builder setPictureOrientation(eu.long1.nativecamera.proto.CameraState.Orientation value) {
      copyOnWrite();
      instance.setPictureOrientation(value);
      return this;
    }
    /**
     * <code>.CameraState.Orientation picture_orientation = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearPictureOrientation() {
      copyOnWrite();
      instance.clearPictureOrientation();
      return this;
    }

    @java.lang.Override

    public int getExifCount() {
      return instance.getExifMap().size();
    }
    /**
     * <code>map&lt;string, string&gt; exif = 3;</code>
     */
    @java.lang.Override

    public boolean containsExif(
        java.lang.String key) {
      key.getClass();
      return instance.getExifMap().containsKey(key);
    }

    public Builder clearExif() {
      copyOnWrite();
      instance.getMutableExifMap().clear();
      return this;
    }
    /**
     * <code>map&lt;string, string&gt; exif = 3;</code>
     */

    public Builder removeExif(
        java.lang.String key) {
      key.getClass();
      copyOnWrite();
      instance.getMutableExifMap().remove(key);
      return this;
    }
    /**
     * Use {@link #getExifMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String> getExif() {
      return getExifMap();
    }
    /**
     * <code>map&lt;string, string&gt; exif = 3;</code>
     */
    @java.lang.Override
    public java.util.Map<java.lang.String, java.lang.String> getExifMap() {
      return java.util.Collections.unmodifiableMap(
          instance.getExifMap());
    }
    /**
     * <code>map&lt;string, string&gt; exif = 3;</code>
     */
    @java.lang.Override

    public java.lang.String getExifOrDefault(
        java.lang.String key,
        java.lang.String defaultValue) {
      key.getClass();
      java.util.Map<java.lang.String, java.lang.String> map =
          instance.getExifMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, string&gt; exif = 3;</code>
     */
    @java.lang.Override

    public java.lang.String getExifOrThrow(
        java.lang.String key) {
      key.getClass();
      java.util.Map<java.lang.String, java.lang.String> map =
          instance.getExifMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }
    /**
     * <code>map&lt;string, string&gt; exif = 3;</code>
     */
    public Builder putExif(
        java.lang.String key,
        java.lang.String value) {
      key.getClass();
      value.getClass();
      copyOnWrite();
      instance.getMutableExifMap().put(key, value);
      return this;
    }
    /**
     * <code>map&lt;string, string&gt; exif = 3;</code>
     */
    public Builder putAllExif(
        java.util.Map<java.lang.String, java.lang.String> values) {
      copyOnWrite();
      instance.getMutableExifMap().putAll(values);
      return this;
    }

    /**
     * <code>int32 width = 4;</code>
     * @return The width.
     */
    @java.lang.Override
    public int getWidth() {
      return instance.getWidth();
    }
    /**
     * <code>int32 width = 4;</code>
     * @param value The width to set.
     * @return This builder for chaining.
     */
    public Builder setWidth(int value) {
      copyOnWrite();
      instance.setWidth(value);
      return this;
    }
    /**
     * <code>int32 width = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearWidth() {
      copyOnWrite();
      instance.clearWidth();
      return this;
    }

    /**
     * <code>int32 height = 5;</code>
     * @return The height.
     */
    @java.lang.Override
    public int getHeight() {
      return instance.getHeight();
    }
    /**
     * <code>int32 height = 5;</code>
     * @param value The height to set.
     * @return This builder for chaining.
     */
    public Builder setHeight(int value) {
      copyOnWrite();
      instance.setHeight(value);
      return this;
    }
    /**
     * <code>int32 height = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearHeight() {
      copyOnWrite();
      instance.clearHeight();
      return this;
    }

    /**
     * <code>string uri = 6;</code>
     * @return The uri.
     */
    @java.lang.Override
    public java.lang.String getUri() {
      return instance.getUri();
    }
    /**
     * <code>string uri = 6;</code>
     * @return The bytes for uri.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getUriBytes() {
      return instance.getUriBytes();
    }
    /**
     * <code>string uri = 6;</code>
     * @param value The uri to set.
     * @return This builder for chaining.
     */
    public Builder setUri(
        java.lang.String value) {
      copyOnWrite();
      instance.setUri(value);
      return this;
    }
    /**
     * <code>string uri = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearUri() {
      copyOnWrite();
      instance.clearUri();
      return this;
    }
    /**
     * <code>string uri = 6;</code>
     * @param value The bytes for uri to set.
     * @return This builder for chaining.
     */
    public Builder setUriBytes(
        com.google.protobuf.ByteString value) {
      copyOnWrite();
      instance.setUriBytes(value);
      return this;
    }

    /**
     * <code>bytes data = 7;</code>
     * @return The data.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getData() {
      return instance.getData();
    }
    /**
     * <code>bytes data = 7;</code>
     * @param value The data to set.
     * @return This builder for chaining.
     */
    public Builder setData(com.google.protobuf.ByteString value) {
      copyOnWrite();
      instance.setData(value);
      return this;
    }
    /**
     * <code>bytes data = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearData() {
      copyOnWrite();
      instance.clearData();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:TakePictureResponse)
  }
  @java.lang.Override
  @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
  protected final java.lang.Object dynamicMethod(
      com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
      java.lang.Object arg0, java.lang.Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new eu.long1.nativecamera.proto.TakePictureResponse();
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case BUILD_MESSAGE_INFO: {
          java.lang.Object[] objects = new java.lang.Object[] {
            "deviceOrientation_",
            "pictureOrientation_",
            "exif_",
            ExifDefaultEntryHolder.defaultEntry,
            "width_",
            "height_",
            "uri_",
            "data_",
          };
          java.lang.String info =
              "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0001\u0000\u0000\u0001\f\u0002\f\u0003" +
              "2\u0004\u0004\u0005\u0004\u0006\u0208\u0007\n";
          return newMessageInfo(DEFAULT_INSTANCE, info, objects);
      }
      // fall through
      case GET_DEFAULT_INSTANCE: {
        return DEFAULT_INSTANCE;
      }
      case GET_PARSER: {
        com.google.protobuf.Parser<eu.long1.nativecamera.proto.TakePictureResponse> parser = PARSER;
        if (parser == null) {
          synchronized (eu.long1.nativecamera.proto.TakePictureResponse.class) {
            parser = PARSER;
            if (parser == null) {
              parser =
                  new DefaultInstanceBasedParser<eu.long1.nativecamera.proto.TakePictureResponse>(
                      DEFAULT_INSTANCE);
              PARSER = parser;
            }
          }
        }
        return parser;
    }
    case GET_MEMOIZED_IS_INITIALIZED: {
      return (byte) 1;
    }
    case SET_MEMOIZED_IS_INITIALIZED: {
      return null;
    }
    }
    throw new UnsupportedOperationException();
  }


  // @@protoc_insertion_point(class_scope:TakePictureResponse)
  private static final eu.long1.nativecamera.proto.TakePictureResponse DEFAULT_INSTANCE;
  static {
    TakePictureResponse defaultInstance = new TakePictureResponse();
    // New instances are implicitly immutable so no need to make
    // immutable.
    DEFAULT_INSTANCE = defaultInstance;
    com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
      TakePictureResponse.class, defaultInstance);
  }

  public static eu.long1.nativecamera.proto.TakePictureResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<TakePictureResponse> PARSER;

  public static com.google.protobuf.Parser<TakePictureResponse> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}

