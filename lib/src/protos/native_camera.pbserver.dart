///
//  Generated code. Do not modify.
//  source: native_camera.proto
//
// @dart = 2.3
// ignore_for_file: camel_case_types,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type

import 'dart:async' as $async;

import 'package:protobuf/protobuf.dart' as $pb;

import 'dart:core' as $core;
import 'native_camera.pb.dart' as $2;
import 'google/protobuf/empty.pb.dart' as $1;
import 'native_camera.pbjson.dart';

export 'native_camera.pb.dart';

abstract class NativeCameraServiceBase extends $pb.GeneratedService {
  $async.Future<$2.InitializeCameraResponse> initializeCamera($pb.ServerContext ctx, $2.InitializeCameraRequest request);
  $async.Future<$2.ListCamerasResponse> listCameras($pb.ServerContext ctx, $1.Empty request);

  $pb.GeneratedMessage createRequest($core.String method) {
    switch (method) {
      case 'InitializeCamera': return $2.InitializeCameraRequest();
      case 'ListCameras': return $1.Empty();
      default: throw $core.ArgumentError('Unknown method: $method');
    }
  }

  $async.Future<$pb.GeneratedMessage> handleCall($pb.ServerContext ctx, $core.String method, $pb.GeneratedMessage request) {
    switch (method) {
      case 'InitializeCamera': return this.initializeCamera(ctx, request);
      case 'ListCameras': return this.listCameras(ctx, request);
      default: throw $core.ArgumentError('Unknown method: $method');
    }
  }

  $core.Map<$core.String, $core.dynamic> get $json => NativeCameraServiceBase$json;
  $core.Map<$core.String, $core.Map<$core.String, $core.dynamic>> get $messageJson => NativeCameraServiceBase$messageJson;
}

