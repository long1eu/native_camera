///
//  Generated code. Do not modify.
//  source: native_camera.proto
//
// @dart = 2.3
// ignore_for_file: camel_case_types,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type

import 'models.pbjson.dart' as $0;
import 'google/protobuf/wrappers.pbjson.dart' as $1;
import 'google/protobuf/empty.pbjson.dart' as $2;

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

const TakePictureRequest$json = const {
  '1': 'TakePictureRequest',
  '2': const [
    const {'1': 'path', '3': 1, '4': 1, '5': 9, '10': 'path'},
    const {'1': 'exif', '3': 2, '4': 3, '5': 11, '6': '.TakePictureRequest.ExifEntry', '10': 'exif'},
    const {'1': 'write_exif', '3': 3, '4': 1, '5': 8, '10': 'writeExif'},
    const {'1': 'quality', '3': 4, '4': 1, '5': 1, '10': 'quality'},
    const {'1': 'pause_after_capture', '3': 5, '4': 1, '5': 8, '10': 'pauseAfterCapture'},
    const {'1': 'fix_orientation', '3': 6, '4': 1, '5': 8, '10': 'fixOrientation'},
    const {'1': 'force_up_orientation', '3': 7, '4': 1, '5': 8, '10': 'forceUpOrientation'},
    const {'1': 'width', '3': 8, '4': 1, '5': 5, '10': 'width'},
    const {'1': 'mirror_image', '3': 9, '4': 1, '5': 8, '10': 'mirrorImage'},
    const {'1': 'do_not_save', '3': 10, '4': 1, '5': 8, '10': 'doNotSave'},
    const {'1': 'return_bytes', '3': 11, '4': 1, '5': 8, '10': 'returnBytes'},
    const {'1': 'orientation', '3': 12, '4': 1, '5': 14, '6': '.CameraState.Orientation', '10': 'orientation'},
  ],
  '3': const [TakePictureRequest_ExifEntry$json],
};

const TakePictureRequest_ExifEntry$json = const {
  '1': 'ExifEntry',
  '2': const [
    const {'1': 'key', '3': 1, '4': 1, '5': 9, '10': 'key'},
    const {'1': 'value', '3': 2, '4': 1, '5': 9, '10': 'value'},
  ],
  '7': const {'7': true},
};

const TakePictureResponse$json = const {
  '1': 'TakePictureResponse',
  '2': const [
    const {'1': 'device_orientation', '3': 1, '4': 1, '5': 14, '6': '.CameraState.Orientation', '10': 'deviceOrientation'},
    const {'1': 'picture_orientation', '3': 2, '4': 1, '5': 14, '6': '.CameraState.Orientation', '10': 'pictureOrientation'},
    const {'1': 'exif', '3': 3, '4': 3, '5': 11, '6': '.TakePictureResponse.ExifEntry', '10': 'exif'},
    const {'1': 'width', '3': 4, '4': 1, '5': 5, '10': 'width'},
    const {'1': 'height', '3': 5, '4': 1, '5': 5, '10': 'height'},
    const {'1': 'uri', '3': 6, '4': 1, '5': 9, '10': 'uri'},
    const {'1': 'data', '3': 7, '4': 1, '5': 12, '10': 'data'},
  ],
  '3': const [TakePictureResponse_ExifEntry$json],
};

const TakePictureResponse_ExifEntry$json = const {
  '1': 'ExifEntry',
  '2': const [
    const {'1': 'key', '3': 1, '4': 1, '5': 9, '10': 'key'},
    const {'1': 'value', '3': 2, '4': 1, '5': 9, '10': 'value'},
  ],
  '7': const {'7': true},
};

const NativeCameraServiceBase$json = const {
  '1': 'NativeCamera',
  '2': const [
    const {'1': 'InitializeCamera', '2': '.InitializeCameraRequest', '3': '.InitializeCameraResponse', '4': const {}},
    const {'1': 'SetCamera', '2': '.google.protobuf.StringValue', '3': '.CameraState', '4': const {}},
    const {'1': 'SetUsingCamera2Api', '2': '.google.protobuf.BoolValue', '3': '.CameraState', '4': const {}},
    const {'1': 'UpdateCamera', '2': '.CameraState', '3': '.CameraState', '4': const {}},
    const {'1': 'AutoFocusPointOfInterest', '2': '.Point', '3': '.google.protobuf.Empty', '4': const {}},
    const {'1': 'ListCameras', '2': '.google.protobuf.Empty', '3': '.ListCamerasResponse', '4': const {}},
    const {'1': 'TakePicture', '2': '.TakePictureRequest', '3': '.ListCamerasResponse', '4': const {}},
  ],
};

const NativeCameraServiceBase$messageJson = const {
  '.InitializeCameraRequest': InitializeCameraRequest$json,
  '.CameraState': $0.CameraState$json,
  '.CameraAspectRatio': $0.CameraAspectRatio$json,
  '.Size': $0.Size$json,
  '.Range': $0.Range$json,
  '.InitializeCameraResponse': InitializeCameraResponse$json,
  '.google.protobuf.StringValue': $1.StringValue$json,
  '.google.protobuf.BoolValue': $1.BoolValue$json,
  '.Point': $0.Point$json,
  '.google.protobuf.Empty': $2.Empty$json,
  '.ListCamerasResponse': ListCamerasResponse$json,
  '.CameraInfo': $0.CameraInfo$json,
  '.TakePictureRequest': TakePictureRequest$json,
  '.TakePictureRequest.ExifEntry': TakePictureRequest_ExifEntry$json,
};

