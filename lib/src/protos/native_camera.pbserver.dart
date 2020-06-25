///
//  Generated code. Do not modify.
//  source: native_camera.proto
//
// @dart = 2.3
// ignore_for_file: camel_case_types,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type

import 'dart:async' as $async;

import 'package:protobuf/protobuf.dart' as $pb;

import 'dart:core' as $core;
import 'native_camera.pb.dart' as $3;
import 'google/protobuf/wrappers.pb.dart' as $1;
import 'models.pb.dart' as $0;
import 'google/protobuf/empty.pb.dart' as $2;
import 'native_camera.pbjson.dart';

export 'native_camera.pb.dart';

abstract class NativeCameraServiceBase extends $pb.GeneratedService {
  $async.Future<$3.InitializeCameraResponse> initializeCamera($pb.ServerContext ctx, $3.InitializeCameraRequest request);
  $async.Future<$0.CameraState> setCamera($pb.ServerContext ctx, $1.StringValue request);
  $async.Future<$0.CameraState> setUsingCamera2Api($pb.ServerContext ctx, $1.BoolValue request);
  $async.Future<$0.CameraState> updateCamera($pb.ServerContext ctx, $0.CameraState request);
  $async.Future<$2.Empty> autoFocusPointOfInterest($pb.ServerContext ctx, $0.Point request);
  $async.Future<$3.ListCamerasResponse> listCameras($pb.ServerContext ctx, $2.Empty request);
  $async.Future<$3.ListCamerasResponse> takePicture($pb.ServerContext ctx, $3.TakePictureRequest request);

  $pb.GeneratedMessage createRequest($core.String method) {
    switch (method) {
      case 'InitializeCamera': return $3.InitializeCameraRequest();
      case 'SetCamera': return $1.StringValue();
      case 'SetUsingCamera2Api': return $1.BoolValue();
      case 'UpdateCamera': return $0.CameraState();
      case 'AutoFocusPointOfInterest': return $0.Point();
      case 'ListCameras': return $2.Empty();
      case 'TakePicture': return $3.TakePictureRequest();
      default: throw $core.ArgumentError('Unknown method: $method');
    }
  }

  $async.Future<$pb.GeneratedMessage> handleCall($pb.ServerContext ctx, $core.String method, $pb.GeneratedMessage request) {
    switch (method) {
      case 'InitializeCamera': return this.initializeCamera(ctx, request);
      case 'SetCamera': return this.setCamera(ctx, request);
      case 'SetUsingCamera2Api': return this.setUsingCamera2Api(ctx, request);
      case 'UpdateCamera': return this.updateCamera(ctx, request);
      case 'AutoFocusPointOfInterest': return this.autoFocusPointOfInterest(ctx, request);
      case 'ListCameras': return this.listCameras(ctx, request);
      case 'TakePicture': return this.takePicture(ctx, request);
      default: throw $core.ArgumentError('Unknown method: $method');
    }
  }

  $core.Map<$core.String, $core.dynamic> get $json => NativeCameraServiceBase$json;
  $core.Map<$core.String, $core.Map<$core.String, $core.dynamic>> get $messageJson => NativeCameraServiceBase$messageJson;
}

