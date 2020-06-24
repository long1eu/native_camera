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
import 'google/protobuf/empty.pb.dart' as $1;

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
    ..e<$0.CameraState_Orientation>(3, 'orientation', $pb.PbFieldType.OE, defaultOrMaker: $0.CameraState_Orientation.ORIENTATION_AUTO, valueOf: $0.CameraState_Orientation.valueOf, enumValues: $0.CameraState_Orientation.values)
    ..aOM<$0.Size>(4, 'previewSize', subBuilder: $0.Size.create)
    ..pc<$0.AspectRatio>(5, 'supportedRatio', $pb.PbFieldType.PM, subBuilder: $0.AspectRatio.create)
    ..pc<$0.Range>(6, 'supportedPreviewFps', $pb.PbFieldType.PM, subBuilder: $0.Range.create)
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

  @$pb.TagNumber(3)
  $0.CameraState_Orientation get orientation => $_getN(2);
  @$pb.TagNumber(3)
  set orientation($0.CameraState_Orientation v) { setField(3, v); }
  @$pb.TagNumber(3)
  $core.bool hasOrientation() => $_has(2);
  @$pb.TagNumber(3)
  void clearOrientation() => clearField(3);

  @$pb.TagNumber(4)
  $0.Size get previewSize => $_getN(3);
  @$pb.TagNumber(4)
  set previewSize($0.Size v) { setField(4, v); }
  @$pb.TagNumber(4)
  $core.bool hasPreviewSize() => $_has(3);
  @$pb.TagNumber(4)
  void clearPreviewSize() => clearField(4);
  @$pb.TagNumber(4)
  $0.Size ensurePreviewSize() => $_ensure(3);

  @$pb.TagNumber(5)
  $core.List<$0.AspectRatio> get supportedRatio => $_getList(4);

  @$pb.TagNumber(6)
  $core.List<$0.Range> get supportedPreviewFps => $_getList(5);
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

class NativeCameraApi {
  $pb.RpcClient _client;
  NativeCameraApi(this._client);

  $async.Future<InitializeCameraResponse> initializeCamera($pb.ClientContext ctx, InitializeCameraRequest request) {
    var emptyResponse = InitializeCameraResponse();
    return _client.invoke<InitializeCameraResponse>(ctx, 'NativeCamera', 'InitializeCamera', request, emptyResponse);
  }
  $async.Future<ListCamerasResponse> listCameras($pb.ClientContext ctx, $1.Empty request) {
    var emptyResponse = ListCamerasResponse();
    return _client.invoke<ListCamerasResponse>(ctx, 'NativeCamera', 'ListCameras', request, emptyResponse);
  }
}

