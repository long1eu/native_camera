///
//  Generated code. Do not modify.
//  source: native_camera.proto
//
// @dart = 2.3
// ignore_for_file: camel_case_types,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type

import 'models.pbjson.dart' as $0;
import 'google/protobuf/empty.pbjson.dart' as $1;

const InitializeCameraRequest$json = const {
  '1': 'InitializeCameraRequest',
  '2': const [
    const {'1': 'channel_name', '3': 1, '4': 1, '5': 9, '10': 'channelName'},
    const {'1': 'state', '3': 2, '4': 1, '5': 11, '6': '.CameraState', '10': 'state'},
  ],
};

const InitializeCameraResponse$json = const {
  '1': 'InitializeCameraResponse',
  '2': const [
    const {'1': 'textureId', '3': 1, '4': 1, '5': 3, '10': 'textureId'},
    const {'1': 'state', '3': 2, '4': 1, '5': 11, '6': '.CameraState', '10': 'state'},
  ],
};

const ListCamerasResponse$json = const {
  '1': 'ListCamerasResponse',
  '2': const [
    const {'1': 'cameras', '3': 1, '4': 3, '5': 11, '6': '.CameraInfo', '10': 'cameras'},
  ],
};

const NativeCameraServiceBase$json = const {
  '1': 'NativeCamera',
  '2': const [
    const {'1': 'InitializeCamera', '2': '.InitializeCameraRequest', '3': '.InitializeCameraResponse', '4': const {}},
    const {'1': 'UpdateCamera', '2': '.CameraState', '3': '.CameraState', '4': const {}},
    const {'1': 'AutoFocusPointOfInterest', '2': '.Point', '3': '.google.protobuf.Empty', '4': const {}},
    const {'1': 'ListCameras', '2': '.google.protobuf.Empty', '3': '.ListCamerasResponse', '4': const {}},
  ],
};

const NativeCameraServiceBase$messageJson = const {
  '.InitializeCameraRequest': InitializeCameraRequest$json,
  '.CameraState': $0.CameraState$json,
  '.CameraAspectRatio': $0.CameraAspectRatio$json,
  '.Size': $0.Size$json,
  '.Range': $0.Range$json,
  '.InitializeCameraResponse': InitializeCameraResponse$json,
  '.Point': $0.Point$json,
  '.google.protobuf.Empty': $1.Empty$json,
  '.ListCamerasResponse': ListCamerasResponse$json,
  '.CameraInfo': $0.CameraInfo$json,
};

