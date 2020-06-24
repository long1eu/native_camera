///
//  Generated code. Do not modify.
//  source: models.proto
//
// @dart = 2.3
// ignore_for_file: camel_case_types,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type

// ignore_for_file: UNDEFINED_SHOWN_NAME,UNUSED_SHOWN_NAME
import 'dart:core' as $core;
import 'package:protobuf/protobuf.dart' as $pb;

class CameraInfo_LensFacing extends $pb.ProtobufEnum {
  static const CameraInfo_LensFacing FRONT = CameraInfo_LensFacing._(0, 'FRONT');
  static const CameraInfo_LensFacing BACK = CameraInfo_LensFacing._(1, 'BACK');
  static const CameraInfo_LensFacing EXTERNAL = CameraInfo_LensFacing._(2, 'EXTERNAL');

  static const $core.List<CameraInfo_LensFacing> values = <CameraInfo_LensFacing> [
    FRONT,
    BACK,
    EXTERNAL,
  ];

  static final $core.Map<$core.int, CameraInfo_LensFacing> _byValue = $pb.ProtobufEnum.initByValue(values);
  static CameraInfo_LensFacing valueOf($core.int value) => _byValue[value];

  const CameraInfo_LensFacing._($core.int v, $core.String n) : super(v, n);
}

class CameraState_Flash extends $pb.ProtobufEnum {
  static const CameraState_Flash FLASH_ON = CameraState_Flash._(0, 'FLASH_ON');
  static const CameraState_Flash FLASH_AUTO = CameraState_Flash._(1, 'FLASH_AUTO');
  static const CameraState_Flash FLASH_OFF = CameraState_Flash._(2, 'FLASH_OFF');
  static const CameraState_Flash FLASH_TORCH = CameraState_Flash._(3, 'FLASH_TORCH');
  static const CameraState_Flash FLASH_RED_EYE = CameraState_Flash._(4, 'FLASH_RED_EYE');

  static const $core.List<CameraState_Flash> values = <CameraState_Flash> [
    FLASH_ON,
    FLASH_AUTO,
    FLASH_OFF,
    FLASH_TORCH,
    FLASH_RED_EYE,
  ];

  static final $core.Map<$core.int, CameraState_Flash> _byValue = $pb.ProtobufEnum.initByValue(values);
  static CameraState_Flash valueOf($core.int value) => _byValue[value];

  const CameraState_Flash._($core.int v, $core.String n) : super(v, n);
}

class CameraState_Orientation extends $pb.ProtobufEnum {
  static const CameraState_Orientation ORIENTATION_AUTO = CameraState_Orientation._(0, 'ORIENTATION_AUTO');
  static const CameraState_Orientation ORIENTATION_LEFT = CameraState_Orientation._(1, 'ORIENTATION_LEFT');
  static const CameraState_Orientation ORIENTATION_RIGHT = CameraState_Orientation._(2, 'ORIENTATION_RIGHT');
  static const CameraState_Orientation ORIENTATION_UP = CameraState_Orientation._(3, 'ORIENTATION_UP');
  static const CameraState_Orientation ORIENTATION_DOWN = CameraState_Orientation._(4, 'ORIENTATION_DOWN');

  static const $core.List<CameraState_Orientation> values = <CameraState_Orientation> [
    ORIENTATION_AUTO,
    ORIENTATION_LEFT,
    ORIENTATION_RIGHT,
    ORIENTATION_UP,
    ORIENTATION_DOWN,
  ];

  static final $core.Map<$core.int, CameraState_Orientation> _byValue = $pb.ProtobufEnum.initByValue(values);
  static CameraState_Orientation valueOf($core.int value) => _byValue[value];

  const CameraState_Orientation._($core.int v, $core.String n) : super(v, n);
}

class CameraState_VideoCodec extends $pb.ProtobufEnum {
  static const CameraState_VideoCodec VIDEO_CODEC_APPLE_PRO_RES422 = CameraState_VideoCodec._(0, 'VIDEO_CODEC_APPLE_PRO_RES422');
  static const CameraState_VideoCodec VIDEO_CODEC_APPLE_PRO_RES4444 = CameraState_VideoCodec._(1, 'VIDEO_CODEC_APPLE_PRO_RES4444');
  static const CameraState_VideoCodec VIDEO_CODEC_H264 = CameraState_VideoCodec._(2, 'VIDEO_CODEC_H264');
  static const CameraState_VideoCodec VIDEO_CODEC_HVEC = CameraState_VideoCodec._(3, 'VIDEO_CODEC_HVEC');
  static const CameraState_VideoCodec VIDEO_CODEC_JPEG = CameraState_VideoCodec._(4, 'VIDEO_CODEC_JPEG');

  static const $core.List<CameraState_VideoCodec> values = <CameraState_VideoCodec> [
    VIDEO_CODEC_APPLE_PRO_RES422,
    VIDEO_CODEC_APPLE_PRO_RES4444,
    VIDEO_CODEC_H264,
    VIDEO_CODEC_HVEC,
    VIDEO_CODEC_JPEG,
  ];

  static final $core.Map<$core.int, CameraState_VideoCodec> _byValue = $pb.ProtobufEnum.initByValue(values);
  static CameraState_VideoCodec valueOf($core.int value) => _byValue[value];

  const CameraState_VideoCodec._($core.int v, $core.String n) : super(v, n);
}

class CameraState_VideoQuality extends $pb.ProtobufEnum {
  static const CameraState_VideoQuality VIDEO_QUALITY_2160P = CameraState_VideoQuality._(0, 'VIDEO_QUALITY_2160P');
  static const CameraState_VideoQuality VIDEO_QUALITY_1080P = CameraState_VideoQuality._(1, 'VIDEO_QUALITY_1080P');
  static const CameraState_VideoQuality VIDEO_QUALITY_720P = CameraState_VideoQuality._(2, 'VIDEO_QUALITY_720P');
  static const CameraState_VideoQuality VIDEO_QUALITY_480P = CameraState_VideoQuality._(3, 'VIDEO_QUALITY_480P');
  static const CameraState_VideoQuality VIDEO_QUALITY_4_3 = CameraState_VideoQuality._(4, 'VIDEO_QUALITY_4_3');
  static const CameraState_VideoQuality VIDEO_QUALITY_288P = CameraState_VideoQuality._(5, 'VIDEO_QUALITY_288P');

  static const $core.List<CameraState_VideoQuality> values = <CameraState_VideoQuality> [
    VIDEO_QUALITY_2160P,
    VIDEO_QUALITY_1080P,
    VIDEO_QUALITY_720P,
    VIDEO_QUALITY_480P,
    VIDEO_QUALITY_4_3,
    VIDEO_QUALITY_288P,
  ];

  static final $core.Map<$core.int, CameraState_VideoQuality> _byValue = $pb.ProtobufEnum.initByValue(values);
  static CameraState_VideoQuality valueOf($core.int value) => _byValue[value];

  const CameraState_VideoQuality._($core.int v, $core.String n) : super(v, n);
}

class CameraState_VideoStabilization extends $pb.ProtobufEnum {
  static const CameraState_VideoStabilization VIDEO_STABILIZATION_AUTO = CameraState_VideoStabilization._(0, 'VIDEO_STABILIZATION_AUTO');
  static const CameraState_VideoStabilization VIDEO_STABILIZATION_STANDARD = CameraState_VideoStabilization._(1, 'VIDEO_STABILIZATION_STANDARD');
  static const CameraState_VideoStabilization VIDEO_STABILIZATION_OFF = CameraState_VideoStabilization._(2, 'VIDEO_STABILIZATION_OFF');
  static const CameraState_VideoStabilization VIDEO_STABILIZATION_CINEMATIC = CameraState_VideoStabilization._(3, 'VIDEO_STABILIZATION_CINEMATIC');

  static const $core.List<CameraState_VideoStabilization> values = <CameraState_VideoStabilization> [
    VIDEO_STABILIZATION_AUTO,
    VIDEO_STABILIZATION_STANDARD,
    VIDEO_STABILIZATION_OFF,
    VIDEO_STABILIZATION_CINEMATIC,
  ];

  static final $core.Map<$core.int, CameraState_VideoStabilization> _byValue = $pb.ProtobufEnum.initByValue(values);
  static CameraState_VideoStabilization valueOf($core.int value) => _byValue[value];

  const CameraState_VideoStabilization._($core.int v, $core.String n) : super(v, n);
}

class CameraState_WhiteBalance extends $pb.ProtobufEnum {
  static const CameraState_WhiteBalance WHITE_BALANCE_AUTO = CameraState_WhiteBalance._(0, 'WHITE_BALANCE_AUTO');
  static const CameraState_WhiteBalance WHITE_BALANCE_CLOUDY = CameraState_WhiteBalance._(1, 'WHITE_BALANCE_CLOUDY');
  static const CameraState_WhiteBalance WHITE_BALANCE_FLUORESCENT = CameraState_WhiteBalance._(2, 'WHITE_BALANCE_FLUORESCENT');
  static const CameraState_WhiteBalance WHITE_BALANCE_INCANDESCENT = CameraState_WhiteBalance._(3, 'WHITE_BALANCE_INCANDESCENT');
  static const CameraState_WhiteBalance WHITE_BALANCE_SHADOW = CameraState_WhiteBalance._(4, 'WHITE_BALANCE_SHADOW');
  static const CameraState_WhiteBalance WHITE_BALANCE_SUNNY = CameraState_WhiteBalance._(5, 'WHITE_BALANCE_SUNNY');

  static const $core.List<CameraState_WhiteBalance> values = <CameraState_WhiteBalance> [
    WHITE_BALANCE_AUTO,
    WHITE_BALANCE_CLOUDY,
    WHITE_BALANCE_FLUORESCENT,
    WHITE_BALANCE_INCANDESCENT,
    WHITE_BALANCE_SHADOW,
    WHITE_BALANCE_SUNNY,
  ];

  static final $core.Map<$core.int, CameraState_WhiteBalance> _byValue = $pb.ProtobufEnum.initByValue(values);
  static CameraState_WhiteBalance valueOf($core.int value) => _byValue[value];

  const CameraState_WhiteBalance._($core.int v, $core.String n) : super(v, n);
}

