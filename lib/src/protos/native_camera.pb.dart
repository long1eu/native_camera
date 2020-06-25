///
//  Generated code. Do not modify.
//  source: native_camera.proto
//
// @dart = 2.3
// ignore_for_file: camel_case_types,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type

import 'dart:async' as $async;
import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import 'models.pb.dart' as $0;
import 'google/protobuf/wrappers.pb.dart' as $1;
import 'google/protobuf/empty.pb.dart' as $2;

import 'models.pbenum.dart' as $0;

class InitializeCameraRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo('InitializeCameraRequest', createEmptyInstance: create)
    ..aOS(1, 'channelName')
    ..aOM<$0.CameraState>(2, 'state', subBuilder: $0.CameraState.create)
    ..hasRequiredFields = false
  ;

  InitializeCameraRequest._() : super();
  factory InitializeCameraRequest() => create();
  factory InitializeCameraRequest.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory InitializeCameraRequest.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  InitializeCameraRequest clone() => InitializeCameraRequest()..mergeFromMessage(this);
  InitializeCameraRequest copyWith(void Function(InitializeCameraRequest) updates) => super.copyWith((message) => updates(message as InitializeCameraRequest));
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static InitializeCameraRequest create() => InitializeCameraRequest._();
  InitializeCameraRequest createEmptyInstance() => create();
  static $pb.PbList<InitializeCameraRequest> createRepeated() => $pb.PbList<InitializeCameraRequest>();
  @$core.pragma('dart2js:noInline')
  static InitializeCameraRequest getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<InitializeCameraRequest>(create);
  static InitializeCameraRequest _defaultInstance;

  @$pb.TagNumber(1)
  $core.String get channelName => $_getSZ(0);
  @$pb.TagNumber(1)
  set channelName($core.String v) { $_setString(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasChannelName() => $_has(0);
  @$pb.TagNumber(1)
  void clearChannelName() => clearField(1);

  @$pb.TagNumber(2)
  $0.CameraState get state => $_getN(1);
  @$pb.TagNumber(2)
  set state($0.CameraState v) { setField(2, v); }
  @$pb.TagNumber(2)
  $core.bool hasState() => $_has(1);
  @$pb.TagNumber(2)
  void clearState() => clearField(2);
  @$pb.TagNumber(2)
  $0.CameraState ensureState() => $_ensure(1);
}

class InitializeCameraResponse extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo('InitializeCameraResponse', createEmptyInstance: create)
    ..aInt64(1, 'textureId', protoName: 'textureId')
    ..aOM<$0.CameraState>(2, 'state', subBuilder: $0.CameraState.create)
    ..hasRequiredFields = false
  ;

  InitializeCameraResponse._() : super();
  factory InitializeCameraResponse() => create();
  factory InitializeCameraResponse.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory InitializeCameraResponse.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  InitializeCameraResponse clone() => InitializeCameraResponse()..mergeFromMessage(this);
  InitializeCameraResponse copyWith(void Function(InitializeCameraResponse) updates) => super.copyWith((message) => updates(message as InitializeCameraResponse));
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static InitializeCameraResponse create() => InitializeCameraResponse._();
  InitializeCameraResponse createEmptyInstance() => create();
  static $pb.PbList<InitializeCameraResponse> createRepeated() => $pb.PbList<InitializeCameraResponse>();
  @$core.pragma('dart2js:noInline')
  static InitializeCameraResponse getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<InitializeCameraResponse>(create);
  static InitializeCameraResponse _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get textureId => $_getI64(0);
  @$pb.TagNumber(1)
  set textureId($fixnum.Int64 v) { $_setInt64(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasTextureId() => $_has(0);
  @$pb.TagNumber(1)
  void clearTextureId() => clearField(1);

  @$pb.TagNumber(2)
  $0.CameraState get state => $_getN(1);
  @$pb.TagNumber(2)
  set state($0.CameraState v) { setField(2, v); }
  @$pb.TagNumber(2)
  $core.bool hasState() => $_has(1);
  @$pb.TagNumber(2)
  void clearState() => clearField(2);
  @$pb.TagNumber(2)
  $0.CameraState ensureState() => $_ensure(1);
}

class ListCamerasResponse extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo('ListCamerasResponse', createEmptyInstance: create)
    ..pc<$0.CameraInfo>(1, 'cameras', $pb.PbFieldType.PM, subBuilder: $0.CameraInfo.create)
    ..hasRequiredFields = false
  ;

  ListCamerasResponse._() : super();
  factory ListCamerasResponse() => create();
  factory ListCamerasResponse.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory ListCamerasResponse.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  ListCamerasResponse clone() => ListCamerasResponse()..mergeFromMessage(this);
  ListCamerasResponse copyWith(void Function(ListCamerasResponse) updates) => super.copyWith((message) => updates(message as ListCamerasResponse));
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static ListCamerasResponse create() => ListCamerasResponse._();
  ListCamerasResponse createEmptyInstance() => create();
  static $pb.PbList<ListCamerasResponse> createRepeated() => $pb.PbList<ListCamerasResponse>();
  @$core.pragma('dart2js:noInline')
  static ListCamerasResponse getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<ListCamerasResponse>(create);
  static ListCamerasResponse _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.CameraInfo> get cameras => $_getList(0);
}

class TakePictureRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo('TakePictureRequest', createEmptyInstance: create)
    ..aOS(1, 'path')
    ..m<$core.String, $core.String>(2, 'exif', entryClassName: 'TakePictureRequest.ExifEntry', keyFieldType: $pb.PbFieldType.OS, valueFieldType: $pb.PbFieldType.OS)
    ..aOB(3, 'writeExif')
    ..a<$core.double>(4, 'quality', $pb.PbFieldType.OD)
    ..aOB(5, 'pauseAfterCapture')
    ..aOB(6, 'fixOrientation')
    ..aOB(7, 'forceUpOrientation')
    ..a<$core.int>(8, 'width', $pb.PbFieldType.O3)
    ..aOB(9, 'mirrorImage')
    ..aOB(10, 'doNotSave')
    ..aOB(11, 'returnBytes')
    ..e<$0.CameraState_Orientation>(12, 'orientation', $pb.PbFieldType.OE, defaultOrMaker: $0.CameraState_Orientation.ORIENTATION_AUTO, valueOf: $0.CameraState_Orientation.valueOf, enumValues: $0.CameraState_Orientation.values)
    ..hasRequiredFields = false
  ;

  TakePictureRequest._() : super();
  factory TakePictureRequest() => create();
  factory TakePictureRequest.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory TakePictureRequest.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  TakePictureRequest clone() => TakePictureRequest()..mergeFromMessage(this);
  TakePictureRequest copyWith(void Function(TakePictureRequest) updates) => super.copyWith((message) => updates(message as TakePictureRequest));
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static TakePictureRequest create() => TakePictureRequest._();
  TakePictureRequest createEmptyInstance() => create();
  static $pb.PbList<TakePictureRequest> createRepeated() => $pb.PbList<TakePictureRequest>();
  @$core.pragma('dart2js:noInline')
  static TakePictureRequest getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<TakePictureRequest>(create);
  static TakePictureRequest _defaultInstance;

  @$pb.TagNumber(1)
  $core.String get path => $_getSZ(0);
  @$pb.TagNumber(1)
  set path($core.String v) { $_setString(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasPath() => $_has(0);
  @$pb.TagNumber(1)
  void clearPath() => clearField(1);

  @$pb.TagNumber(2)
  $core.Map<$core.String, $core.String> get exif => $_getMap(1);

  @$pb.TagNumber(3)
  $core.bool get writeExif => $_getBF(2);
  @$pb.TagNumber(3)
  set writeExif($core.bool v) { $_setBool(2, v); }
  @$pb.TagNumber(3)
  $core.bool hasWriteExif() => $_has(2);
  @$pb.TagNumber(3)
  void clearWriteExif() => clearField(3);

  @$pb.TagNumber(4)
  $core.double get quality => $_getN(3);
  @$pb.TagNumber(4)
  set quality($core.double v) { $_setDouble(3, v); }
  @$pb.TagNumber(4)
  $core.bool hasQuality() => $_has(3);
  @$pb.TagNumber(4)
  void clearQuality() => clearField(4);

  @$pb.TagNumber(5)
  $core.bool get pauseAfterCapture => $_getBF(4);
  @$pb.TagNumber(5)
  set pauseAfterCapture($core.bool v) { $_setBool(4, v); }
  @$pb.TagNumber(5)
  $core.bool hasPauseAfterCapture() => $_has(4);
  @$pb.TagNumber(5)
  void clearPauseAfterCapture() => clearField(5);

  @$pb.TagNumber(6)
  $core.bool get fixOrientation => $_getBF(5);
  @$pb.TagNumber(6)
  set fixOrientation($core.bool v) { $_setBool(5, v); }
  @$pb.TagNumber(6)
  $core.bool hasFixOrientation() => $_has(5);
  @$pb.TagNumber(6)
  void clearFixOrientation() => clearField(6);

  @$pb.TagNumber(7)
  $core.bool get forceUpOrientation => $_getBF(6);
  @$pb.TagNumber(7)
  set forceUpOrientation($core.bool v) { $_setBool(6, v); }
  @$pb.TagNumber(7)
  $core.bool hasForceUpOrientation() => $_has(6);
  @$pb.TagNumber(7)
  void clearForceUpOrientation() => clearField(7);

  @$pb.TagNumber(8)
  $core.int get width => $_getIZ(7);
  @$pb.TagNumber(8)
  set width($core.int v) { $_setSignedInt32(7, v); }
  @$pb.TagNumber(8)
  $core.bool hasWidth() => $_has(7);
  @$pb.TagNumber(8)
  void clearWidth() => clearField(8);

  @$pb.TagNumber(9)
  $core.bool get mirrorImage => $_getBF(8);
  @$pb.TagNumber(9)
  set mirrorImage($core.bool v) { $_setBool(8, v); }
  @$pb.TagNumber(9)
  $core.bool hasMirrorImage() => $_has(8);
  @$pb.TagNumber(9)
  void clearMirrorImage() => clearField(9);

  @$pb.TagNumber(10)
  $core.bool get doNotSave => $_getBF(9);
  @$pb.TagNumber(10)
  set doNotSave($core.bool v) { $_setBool(9, v); }
  @$pb.TagNumber(10)
  $core.bool hasDoNotSave() => $_has(9);
  @$pb.TagNumber(10)
  void clearDoNotSave() => clearField(10);

  @$pb.TagNumber(11)
  $core.bool get returnBytes => $_getBF(10);
  @$pb.TagNumber(11)
  set returnBytes($core.bool v) { $_setBool(10, v); }
  @$pb.TagNumber(11)
  $core.bool hasReturnBytes() => $_has(10);
  @$pb.TagNumber(11)
  void clearReturnBytes() => clearField(11);

  @$pb.TagNumber(12)
  $0.CameraState_Orientation get orientation => $_getN(11);
  @$pb.TagNumber(12)
  set orientation($0.CameraState_Orientation v) { setField(12, v); }
  @$pb.TagNumber(12)
  $core.bool hasOrientation() => $_has(11);
  @$pb.TagNumber(12)
  void clearOrientation() => clearField(12);
}

class TakePictureResponse extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo('TakePictureResponse', createEmptyInstance: create)
    ..e<$0.CameraState_Orientation>(1, 'deviceOrientation', $pb.PbFieldType.OE, defaultOrMaker: $0.CameraState_Orientation.ORIENTATION_AUTO, valueOf: $0.CameraState_Orientation.valueOf, enumValues: $0.CameraState_Orientation.values)
    ..e<$0.CameraState_Orientation>(2, 'pictureOrientation', $pb.PbFieldType.OE, defaultOrMaker: $0.CameraState_Orientation.ORIENTATION_AUTO, valueOf: $0.CameraState_Orientation.valueOf, enumValues: $0.CameraState_Orientation.values)
    ..m<$core.String, $core.String>(3, 'exif', entryClassName: 'TakePictureResponse.ExifEntry', keyFieldType: $pb.PbFieldType.OS, valueFieldType: $pb.PbFieldType.OS)
    ..a<$core.int>(4, 'width', $pb.PbFieldType.O3)
    ..a<$core.int>(5, 'height', $pb.PbFieldType.O3)
    ..aOS(6, 'uri')
    ..a<$core.List<$core.int>>(7, 'data', $pb.PbFieldType.OY)
    ..hasRequiredFields = false
  ;

  TakePictureResponse._() : super();
  factory TakePictureResponse() => create();
  factory TakePictureResponse.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory TakePictureResponse.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  TakePictureResponse clone() => TakePictureResponse()..mergeFromMessage(this);
  TakePictureResponse copyWith(void Function(TakePictureResponse) updates) => super.copyWith((message) => updates(message as TakePictureResponse));
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static TakePictureResponse create() => TakePictureResponse._();
  TakePictureResponse createEmptyInstance() => create();
  static $pb.PbList<TakePictureResponse> createRepeated() => $pb.PbList<TakePictureResponse>();
  @$core.pragma('dart2js:noInline')
  static TakePictureResponse getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<TakePictureResponse>(create);
  static TakePictureResponse _defaultInstance;

  @$pb.TagNumber(1)
  $0.CameraState_Orientation get deviceOrientation => $_getN(0);
  @$pb.TagNumber(1)
  set deviceOrientation($0.CameraState_Orientation v) { setField(1, v); }
  @$pb.TagNumber(1)
  $core.bool hasDeviceOrientation() => $_has(0);
  @$pb.TagNumber(1)
  void clearDeviceOrientation() => clearField(1);

  @$pb.TagNumber(2)
  $0.CameraState_Orientation get pictureOrientation => $_getN(1);
  @$pb.TagNumber(2)
  set pictureOrientation($0.CameraState_Orientation v) { setField(2, v); }
  @$pb.TagNumber(2)
  $core.bool hasPictureOrientation() => $_has(1);
  @$pb.TagNumber(2)
  void clearPictureOrientation() => clearField(2);

  @$pb.TagNumber(3)
  $core.Map<$core.String, $core.String> get exif => $_getMap(2);

  @$pb.TagNumber(4)
  $core.int get width => $_getIZ(3);
  @$pb.TagNumber(4)
  set width($core.int v) { $_setSignedInt32(3, v); }
  @$pb.TagNumber(4)
  $core.bool hasWidth() => $_has(3);
  @$pb.TagNumber(4)
  void clearWidth() => clearField(4);

  @$pb.TagNumber(5)
  $core.int get height => $_getIZ(4);
  @$pb.TagNumber(5)
  set height($core.int v) { $_setSignedInt32(4, v); }
  @$pb.TagNumber(5)
  $core.bool hasHeight() => $_has(4);
  @$pb.TagNumber(5)
  void clearHeight() => clearField(5);

  @$pb.TagNumber(6)
  $core.String get uri => $_getSZ(5);
  @$pb.TagNumber(6)
  set uri($core.String v) { $_setString(5, v); }
  @$pb.TagNumber(6)
  $core.bool hasUri() => $_has(5);
  @$pb.TagNumber(6)
  void clearUri() => clearField(6);

  @$pb.TagNumber(7)
  $core.List<$core.int> get data => $_getN(6);
  @$pb.TagNumber(7)
  set data($core.List<$core.int> v) { $_setBytes(6, v); }
  @$pb.TagNumber(7)
  $core.bool hasData() => $_has(6);
  @$pb.TagNumber(7)
  void clearData() => clearField(7);
}

class NativeCameraApi {
  $pb.RpcClient _client;
  NativeCameraApi(this._client);

  $async.Future<InitializeCameraResponse> initializeCamera($pb.ClientContext ctx, InitializeCameraRequest request) {
    var emptyResponse = InitializeCameraResponse();
    return _client.invoke<InitializeCameraResponse>(ctx, 'NativeCamera', 'InitializeCamera', request, emptyResponse);
  }
  $async.Future<$0.CameraState> setCamera($pb.ClientContext ctx, $1.StringValue request) {
    var emptyResponse = $0.CameraState();
    return _client.invoke<$0.CameraState>(ctx, 'NativeCamera', 'SetCamera', request, emptyResponse);
  }
  $async.Future<$0.CameraState> setUsingCamera2Api($pb.ClientContext ctx, $1.BoolValue request) {
    var emptyResponse = $0.CameraState();
    return _client.invoke<$0.CameraState>(ctx, 'NativeCamera', 'SetUsingCamera2Api', request, emptyResponse);
  }
  $async.Future<$0.CameraState> updateCamera($pb.ClientContext ctx, $0.CameraState request) {
    var emptyResponse = $0.CameraState();
    return _client.invoke<$0.CameraState>(ctx, 'NativeCamera', 'UpdateCamera', request, emptyResponse);
  }
  $async.Future<$2.Empty> autoFocusPointOfInterest($pb.ClientContext ctx, $0.Point request) {
    var emptyResponse = $2.Empty();
    return _client.invoke<$2.Empty>(ctx, 'NativeCamera', 'AutoFocusPointOfInterest', request, emptyResponse);
  }
  $async.Future<ListCamerasResponse> listCameras($pb.ClientContext ctx, $2.Empty request) {
    var emptyResponse = ListCamerasResponse();
    return _client.invoke<ListCamerasResponse>(ctx, 'NativeCamera', 'ListCameras', request, emptyResponse);
  }
  $async.Future<ListCamerasResponse> takePicture($pb.ClientContext ctx, TakePictureRequest request) {
    var emptyResponse = ListCamerasResponse();
    return _client.invoke<ListCamerasResponse>(ctx, 'NativeCamera', 'TakePicture', request, emptyResponse);
  }
}

