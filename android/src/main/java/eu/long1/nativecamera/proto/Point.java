// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: models.proto

package eu.long1.nativecamera.proto;

/**
 * Protobuf type {@code Point}
 */
public  final class Point extends
    com.google.protobuf.GeneratedMessageLite<
        Point, Point.Builder> implements
    // @@protoc_insertion_point(message_implements:Point)
    PointOrBuilder {
  private Point() {
  }
  public static final int X_FIELD_NUMBER = 1;
  private float x_;
  /**
   * <code>float x = 1;</code>
   * @return The x.
   */
  @java.lang.Override
  public float getX() {
    return x_;
  }
  /**
   * <code>float x = 1;</code>
   * @param value The x to set.
   */
  private void setX(float value) {
    
    x_ = value;
  }
  /**
   * <code>float x = 1;</code>
   */
  private void clearX() {
    
    x_ = 0F;
  }

  public static final int Y_FIELD_NUMBER = 2;
  private float y_;
  /**
   * <code>float y = 2;</code>
   * @return The y.
   */
  @java.lang.Override
  public float getY() {
    return y_;
  }
  /**
   * <code>float y = 2;</code>
   * @param value The y to set.
   */
  private void setY(float value) {
    
    y_ = value;
  }
  /**
   * <code>float y = 2;</code>
   */
  private void clearY() {
    
    y_ = 0F;
  }

  public static eu.long1.nativecamera.proto.Point parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static eu.long1.nativecamera.proto.Point parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static eu.long1.nativecamera.proto.Point parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static eu.long1.nativecamera.proto.Point parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static eu.long1.nativecamera.proto.Point parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static eu.long1.nativecamera.proto.Point parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static eu.long1.nativecamera.proto.Point parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static eu.long1.nativecamera.proto.Point parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static eu.long1.nativecamera.proto.Point parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }
  public static eu.long1.nativecamera.proto.Point parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static eu.long1.nativecamera.proto.Point parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static eu.long1.nativecamera.proto.Point parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return (Builder) DEFAULT_INSTANCE.createBuilder();
  }
  public static Builder newBuilder(eu.long1.nativecamera.proto.Point prototype) {
    return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
  }

  /**
   * Protobuf type {@code Point}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        eu.long1.nativecamera.proto.Point, Builder> implements
      // @@protoc_insertion_point(builder_implements:Point)
      eu.long1.nativecamera.proto.PointOrBuilder {
    // Construct using eu.long1.nativecamera.proto.Point.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }


    /**
     * <code>float x = 1;</code>
     * @return The x.
     */
    @java.lang.Override
    public float getX() {
      return instance.getX();
    }
    /**
     * <code>float x = 1;</code>
     * @param value The x to set.
     * @return This builder for chaining.
     */
    public Builder setX(float value) {
      copyOnWrite();
      instance.setX(value);
      return this;
    }
    /**
     * <code>float x = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearX() {
      copyOnWrite();
      instance.clearX();
      return this;
    }

    /**
     * <code>float y = 2;</code>
     * @return The y.
     */
    @java.lang.Override
    public float getY() {
      return instance.getY();
    }
    /**
     * <code>float y = 2;</code>
     * @param value The y to set.
     * @return This builder for chaining.
     */
    public Builder setY(float value) {
      copyOnWrite();
      instance.setY(value);
      return this;
    }
    /**
     * <code>float y = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearY() {
      copyOnWrite();
      instance.clearY();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:Point)
  }
  @java.lang.Override
  @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
  protected final java.lang.Object dynamicMethod(
      com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
      java.lang.Object arg0, java.lang.Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new eu.long1.nativecamera.proto.Point();
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case BUILD_MESSAGE_INFO: {
          java.lang.Object[] objects = new java.lang.Object[] {
            "x_",
            "y_",
          };
          java.lang.String info =
              "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0001\u0002\u0001" +
              "";
          return newMessageInfo(DEFAULT_INSTANCE, info, objects);
      }
      // fall through
      case GET_DEFAULT_INSTANCE: {
        return DEFAULT_INSTANCE;
      }
      case GET_PARSER: {
        com.google.protobuf.Parser<eu.long1.nativecamera.proto.Point> parser = PARSER;
        if (parser == null) {
          synchronized (eu.long1.nativecamera.proto.Point.class) {
            parser = PARSER;
            if (parser == null) {
              parser =
                  new DefaultInstanceBasedParser<eu.long1.nativecamera.proto.Point>(
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


  // @@protoc_insertion_point(class_scope:Point)
  private static final eu.long1.nativecamera.proto.Point DEFAULT_INSTANCE;
  static {
    Point defaultInstance = new Point();
    // New instances are implicitly immutable so no need to make
    // immutable.
    DEFAULT_INSTANCE = defaultInstance;
    com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
      Point.class, defaultInstance);
  }

  public static eu.long1.nativecamera.proto.Point getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<Point> PARSER;

  public static com.google.protobuf.Parser<Point> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}

