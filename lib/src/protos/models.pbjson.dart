///
//  Generated code. Do not modify.
//  source: models.proto
//
// @dart = 2.3
// ignore_for_file: camel_case_types,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type

const CameraInfo$json = const {
  '1': 'CameraInfo',
  '2': const [
    const {'1': 'id', '3': 1, '4': 1, '5': 9, '10': 'id'},
    const {'1': 'facing', '3': 2, '4': 1, '5': 14, '6': '.CameraInfo.LensFacing', '10': 'facing'},
  ],
  '4': const [CameraInfo_LensFacing$json],
};

const CameraInfo_LensFacing$json = const {
  '1': 'LensFacing',
  '2': const [
    const {'1': 'FRONT', '2': 0},
    const {'1': 'BACK', '2': 1},
    const {'1': 'EXTERNAL', '2': 2},
  ],
};

const CameraState$json = const {
  '1': 'CameraState',
  '2': const [
    const {'1': 'zoom', '3': 1, '4': 1, '5': 2, '10': 'zoom'},
    const {'1': 'max_zoom', '3': 2, '4': 1, '5': 2, '10': 'maxZoom'},
    const {'1': 'ratio', '3': 3, '4': 1, '5': 11, '6': '.CameraAspectRatio', '10': 'ratio'},
    const {'1': 'focus_depth', '3': 4, '4': 1, '5': 2, '10': 'focusDepth'},
    const {'1': 'camera_id', '3': 5, '4': 1, '5': 9, '10': 'cameraId'},
    const {'1': 'auto_focus', '3': 6, '4': 1, '5': 8, '10': 'autoFocus'},
    const {'1': 'flash', '3': 7, '4': 1, '5': 14, '6': '.CameraState.Flash', '10': 'flash'},
    const {'1': 'exposure', '3': 8, '4': 1, '5': 2, '10': 'exposure'},
    const {'1': 'white_balance', '3': 9, '4': 1, '5': 14, '6': '.CameraState.WhiteBalance', '10': 'whiteBalance'},
    const {'1': 'play_sound_on_capture', '3': 10, '4': 1, '5': 8, '10': 'playSoundOnCapture'},
    const {'1': 'use_camera_2', '3': 11, '4': 1, '5': 8, '10': 'useCamera2'},
    const {'1': 'orientation', '3': 12, '4': 1, '5': 14, '6': '.CameraState.Orientation', '10': 'orientation'},
    const {'1': 'preview_size', '3': 13, '4': 1, '5': 11, '6': '.Size', '10': 'previewSize'},
    const {'1': 'supported_ratio', '3': 14, '4': 3, '5': 11, '6': '.CameraAspectRatio', '10': 'supportedRatio'},
    const {'1': 'supported_preview_fps', '3': 15, '4': 3, '5': 11, '6': '.Range', '10': 'supportedPreviewFps'},
  ],
  '4': const [CameraState_Flash$json, CameraState_Orientation$json, CameraState_VideoCodec$json, CameraState_VideoQuality$json, CameraState_VideoStabilization$json, CameraState_WhiteBalance$json],
};

const CameraState_Flash$json = const {
  '1': 'Flash',
  '2': const [
    const {'1': 'FLASH_ON', '2': 0},
    const {'1': 'FLASH_AUTO', '2': 1},
    const {'1': 'FLASH_OFF', '2': 2},
    const {'1': 'FLASH_TORCH', '2': 3},
    const {'1': 'FLASH_RED_EYE', '2': 4},
  ],
};

const CameraState_Orientation$json = const {
  '1': 'Orientation',
  '2': const [
    const {'1': 'ORIENTATION_AUTO', '2': 0},
    const {'1': 'ORIENTATION_LEFT', '2': 1},
    const {'1': 'ORIENTATION_RIGHT', '2': 2},
    const {'1': 'ORIENTATION_UP', '2': 3},
    const {'1': 'ORIENTATION_DOWN', '2': 4},
  ],
};

const CameraState_VideoCodec$json = const {
  '1': 'VideoCodec',
  '2': const [
    const {'1': 'VIDEO_CODEC_APPLE_PRO_RES422', '2': 0},
    const {'1': 'VIDEO_CODEC_APPLE_PRO_RES4444', '2': 1},
    const {'1': 'VIDEO_CODEC_H264', '2': 2},
    const {'1': 'VIDEO_CODEC_HVEC', '2': 3},
    const {'1': 'VIDEO_CODEC_JPEG', '2': 4},
  ],
};

const CameraState_VideoQuality$json = const {
  '1': 'VideoQuality',
  '2': const [
    const {'1': 'VIDEO_QUALITY_2160P', '2': 0},
    const {'1': 'VIDEO_QUALITY_1080P', '2': 1},
    const {'1': 'VIDEO_QUALITY_720P', '2': 2},
    const {'1': 'VIDEO_QUALITY_480P', '2': 3},
    const {'1': 'VIDEO_QUALITY_4_3', '2': 4},
    const {'1': 'VIDEO_QUALITY_288P', '2': 5},
  ],
};

const CameraState_VideoStabilization$json = const {
  '1': 'VideoStabilization',
  '2': const [
    const {'1': 'VIDEO_STABILIZATION_AUTO', '2': 0},
    const {'1': 'VIDEO_STABILIZATION_STANDARD', '2': 1},
    const {'1': 'VIDEO_STABILIZATION_OFF', '2': 2},
    const {'1': 'VIDEO_STABILIZATION_CINEMATIC', '2': 3},
  ],
};

const CameraState_WhiteBalance$json = const {
  '1': 'WhiteBalance',
  '2': const [
    const {'1': 'WHITE_BALANCE_AUTO', '2': 0},
    const {'1': 'WHITE_BALANCE_CLOUDY', '2': 1},
    const {'1': 'WHITE_BALANCE_FLUORESCENT', '2': 2},
    const {'1': 'WHITE_BALANCE_INCANDESCENT', '2': 3},
    const {'1': 'WHITE_BALANCE_SHADOW', '2': 4},
    const {'1': 'WHITE_BALANCE_SUNNY', '2': 5},
  ],
};

const CameraAspectRatio$json = const {
  '1': 'CameraAspectRatio',
  '2': const [
    const {'1': 'x', '3': 1, '4': 1, '5': 5, '10': 'x'},
    const {'1': 'y', '3': 2, '4': 1, '5': 5, '10': 'y'},
  ],
};

const Size$json = const {
  '1': 'Size',
  '2': const [
    const {'1': 'width', '3': 1, '4': 1, '5': 5, '10': 'width'},
    const {'1': 'height', '3': 2, '4': 1, '5': 5, '10': 'height'},
  ],
};

const Range$json = const {
  '1': 'Range',
  '2': const [
    const {'1': 'min', '3': 1, '4': 1, '5': 5, '10': 'min'},
    const {'1': 'max', '3': 2, '4': 1, '5': 5, '10': 'max'},
  ],
};

const Point$json = const {
  '1': 'Point',
  '2': const [
    const {'1': 'x', '3': 1, '4': 1, '5': 2, '10': 'x'},
    const {'1': 'y', '3': 2, '4': 1, '5': 2, '10': 'y'},
  ],
};

