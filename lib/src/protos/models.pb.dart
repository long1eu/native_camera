///
//  Generated code. Do not modify.
//  source: models.proto
//
// @dart = 2.3
// ignore_for_file: camel_case_types,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import 'models.pbenum.dart';

export 'models.pbenum.dart';

class CameraInfo extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo('CameraInfo', createEmptyInstance: create)
    ..aOS(1, 'id')
    ..e<CameraInfo_LensFacing>(2, 'facing', $pb.PbFieldType.OE, defaultOrMaker: CameraInfo_LensFacing.FRONT, valueOf: CameraInfo_LensFacing.valueOf, enumValues: CameraInfo_LensFacing.values)
    ..hasRequiredFields = false
  ;

  CameraInfo._() : super();
  factory CameraInfo() => create();
  factory CameraInfo.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory CameraInfo.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  CameraInfo clone() => CameraInfo()..mergeFromMessage(this);
  CameraInfo copyWith(void Function(CameraInfo) updates) => super.copyWith((message) => updates(message as CameraInfo));
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static CameraInfo create() => CameraInfo._();
  CameraInfo createEmptyInstance() => create();
  static $pb.PbList<CameraInfo> createRepeated() => $pb.PbList<CameraInfo>();
  @$core.pragma('dart2js:noInline')
  static CameraInfo getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<CameraInfo>(create);
  static CameraInfo _defaultInstance;

  @$pb.TagNumber(1)
  $core.String get id => $_getSZ(0);
  @$pb.TagNumber(1)
  set id($core.String v) { $_setString(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasId() => $_has(0);
  @$pb.TagNumber(1)
  void clearId() => clearField(1);

  @$pb.TagNumber(2)
  CameraInfo_LensFacing get facing => $_getN(1);
  @$pb.TagNumber(2)
  set facing(CameraInfo_LensFacing v) { setField(2, v); }
  @$pb.TagNumber(2)
  $core.bool hasFacing() => $_has(1);
  @$pb.TagNumber(2)
  void clearFacing() => clearField(2);
}

class CameraState extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo('CameraState', createEmptyInstance: create)
    ..a<$core.double>(1, 'zoom', $pb.PbFieldType.OF)
    ..a<$core.double>(2, 'maxZoom', $pb.PbFieldType.OF)
    ..aOM<AspectRatio>(3, 'ratio', subBuilder: AspectRatio.create)
    ..a<$core.double>(4, 'focusDepth', $pb.PbFieldType.OF)
    ..aOS(5, 'cameraId')
    ..aOB(6, 'autoFocus')
    ..e<CameraState_Flash>(7, 'flash', $pb.PbFieldType.OE, defaultOrMaker: CameraState_Flash.FLASH_ON, valueOf: CameraState_Flash.valueOf, enumValues: CameraState_Flash.values)
    ..a<$core.double>(8, 'exposure', $pb.PbFieldType.OF)
    ..e<CameraState_WhiteBalance>(9, 'whiteBalance', $pb.PbFieldType.OE, defaultOrMaker: CameraState_WhiteBalance.WHITE_BALANCE_AUTO, valueOf: CameraState_WhiteBalance.valueOf, enumValues: CameraState_WhiteBalance.values)
    ..aOB(10, 'playSoundOnCapture')
    ..aOB(11, 'useCamera2', protoName: 'use_camera_2')
    ..hasRequiredFields = false
  ;

  CameraState._() : super();
  factory CameraState() => create();
  factory CameraState.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory CameraState.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  CameraState clone() => CameraState()..mergeFromMessage(this);
  CameraState copyWith(void Function(CameraState) updates) => super.copyWith((message) => updates(message as CameraState));
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static CameraState create() => CameraState._();
  CameraState createEmptyInstance() => create();
  static $pb.PbList<CameraState> createRepeated() => $pb.PbList<CameraState>();
  @$core.pragma('dart2js:noInline')
  static CameraState getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<CameraState>(create);
  static CameraState _defaultInstance;

  @$pb.TagNumber(1)
  $core.double get zoom => $_getN(0);
  @$pb.TagNumber(1)
  set zoom($core.double v) { $_setFloat(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasZoom() => $_has(0);
  @$pb.TagNumber(1)
  void clearZoom() => clearField(1);

  @$pb.TagNumber(2)
  $core.double get maxZoom => $_getN(1);
  @$pb.TagNumber(2)
  set maxZoom($core.double v) { $_setFloat(1, v); }
  @$pb.TagNumber(2)
  $core.bool hasMaxZoom() => $_has(1);
  @$pb.TagNumber(2)
  void clearMaxZoom() => clearField(2);

  @$pb.TagNumber(3)
  AspectRatio get ratio => $_getN(2);
  @$pb.TagNumber(3)
  set ratio(AspectRatio v) { setField(3, v); }
  @$pb.TagNumber(3)
  $core.bool hasRatio() => $_has(2);
  @$pb.TagNumber(3)
  void clearRatio() => clearField(3);
  @$pb.TagNumber(3)
  AspectRatio ensureRatio() => $_ensure(2);

  @$pb.TagNumber(4)
  $core.double get focusDepth => $_getN(3);
  @$pb.TagNumber(4)
  set focusDepth($core.double v) { $_setFloat(3, v); }
  @$pb.TagNumber(4)
  $core.bool hasFocusDepth() => $_has(3);
  @$pb.TagNumber(4)
  void clearFocusDepth() => clearField(4);

  @$pb.TagNumber(5)
  $core.String get cameraId => $_getSZ(4);
  @$pb.TagNumber(5)
  set cameraId($core.String v) { $_setString(4, v); }
  @$pb.TagNumber(5)
  $core.bool hasCameraId() => $_has(4);
  @$pb.TagNumber(5)
  void clearCameraId() => clearField(5);

  @$pb.TagNumber(6)
  $core.bool get autoFocus => $_getBF(5);
  @$pb.TagNumber(6)
  set autoFocus($core.bool v) { $_setBool(5, v); }
  @$pb.TagNumber(6)
  $core.bool hasAutoFocus() => $_has(5);
  @$pb.TagNumber(6)
  void clearAutoFocus() => clearField(6);

  @$pb.TagNumber(7)
  CameraState_Flash get flash => $_getN(6);
  @$pb.TagNumber(7)
  set flash(CameraState_Flash v) { setField(7, v); }
  @$pb.TagNumber(7)
  $core.bool hasFlash() => $_has(6);
  @$pb.TagNumber(7)
  void clearFlash() => clearField(7);

  @$pb.TagNumber(8)
  $core.double get exposure => $_getN(7);
  @$pb.TagNumber(8)
  set exposure($core.double v) { $_setFloat(7, v); }
  @$pb.TagNumber(8)
  $core.bool hasExposure() => $_has(7);
  @$pb.TagNumber(8)
  void clearExposure() => clearField(8);

  @$pb.TagNumber(9)
  CameraState_WhiteBalance get whiteBalance => $_getN(8);
  @$pb.TagNumber(9)
  set whiteBalance(CameraState_WhiteBalance v) { setField(9, v); }
  @$pb.TagNumber(9)
  $core.bool hasWhiteBalance() => $_has(8);
  @$pb.TagNumber(9)
  void clearWhiteBalance() => clearField(9);

  @$pb.TagNumber(10)
  $core.bool get playSoundOnCapture => $_getBF(9);
  @$pb.TagNumber(10)
  set playSoundOnCapture($core.bool v) { $_setBool(9, v); }
  @$pb.TagNumber(10)
  $core.bool hasPlaySoundOnCapture() => $_has(9);
  @$pb.TagNumber(10)
  void clearPlaySoundOnCapture() => clearField(10);

  @$pb.TagNumber(11)
  $core.bool get useCamera2 => $_getBF(10);
  @$pb.TagNumber(11)
  set useCamera2($core.bool v) { $_setBool(10, v); }
  @$pb.TagNumber(11)
  $core.bool hasUseCamera2() => $_has(10);
  @$pb.TagNumber(11)
  void clearUseCamera2() => clearField(11);
}

class AspectRatio extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo('AspectRatio', createEmptyInstance: create)
    ..a<$core.int>(1, 'x', $pb.PbFieldType.O3)
    ..a<$core.int>(2, 'y', $pb.PbFieldType.O3)
    ..hasRequiredFields = false
  ;

  AspectRatio._() : super();
  factory AspectRatio() => create();
  factory AspectRatio.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory AspectRatio.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  AspectRatio clone() => AspectRatio()..mergeFromMessage(this);
  AspectRatio copyWith(void Function(AspectRatio) updates) => super.copyWith((message) => updates(message as AspectRatio));
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static AspectRatio create() => AspectRatio._();
  AspectRatio createEmptyInstance() => create();
  static $pb.PbList<AspectRatio> createRepeated() => $pb.PbList<AspectRatio>();
  @$core.pragma('dart2js:noInline')
  static AspectRatio getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<AspectRatio>(create);
  static AspectRatio _defaultInstance;

  @$pb.TagNumber(1)
  $core.int get x => $_getIZ(0);
  @$pb.TagNumber(1)
  set x($core.int v) { $_setSignedInt32(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasX() => $_has(0);
  @$pb.TagNumber(1)
  void clearX() => clearField(1);

  @$pb.TagNumber(2)
  $core.int get y => $_getIZ(1);
  @$pb.TagNumber(2)
  set y($core.int v) { $_setSignedInt32(1, v); }
  @$pb.TagNumber(2)
  $core.bool hasY() => $_has(1);
  @$pb.TagNumber(2)
  void clearY() => clearField(2);
}

class Size extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo('Size', createEmptyInstance: create)
    ..a<$core.int>(1, 'width', $pb.PbFieldType.O3)
    ..a<$core.int>(2, 'height', $pb.PbFieldType.O3)
    ..hasRequiredFields = false
  ;

  Size._() : super();
  factory Size() => create();
  factory Size.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory Size.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  Size clone() => Size()..mergeFromMessage(this);
  Size copyWith(void Function(Size) updates) => super.copyWith((message) => updates(message as Size));
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static Size create() => Size._();
  Size createEmptyInstance() => create();
  static $pb.PbList<Size> createRepeated() => $pb.PbList<Size>();
  @$core.pragma('dart2js:noInline')
  static Size getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<Size>(create);
  static Size _defaultInstance;

  @$pb.TagNumber(1)
  $core.int get width => $_getIZ(0);
  @$pb.TagNumber(1)
  set width($core.int v) { $_setSignedInt32(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasWidth() => $_has(0);
  @$pb.TagNumber(1)
  void clearWidth() => clearField(1);

  @$pb.TagNumber(2)
  $core.int get height => $_getIZ(1);
  @$pb.TagNumber(2)
  set height($core.int v) { $_setSignedInt32(1, v); }
  @$pb.TagNumber(2)
  $core.bool hasHeight() => $_has(1);
  @$pb.TagNumber(2)
  void clearHeight() => clearField(2);
}

class Range extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo('Range', createEmptyInstance: create)
    ..a<$core.int>(1, 'min', $pb.PbFieldType.O3)
    ..a<$core.int>(2, 'max', $pb.PbFieldType.O3)
    ..hasRequiredFields = false
  ;

  Range._() : super();
  factory Range() => create();
  factory Range.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory Range.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  Range clone() => Range()..mergeFromMessage(this);
  Range copyWith(void Function(Range) updates) => super.copyWith((message) => updates(message as Range));
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static Range create() => Range._();
  Range createEmptyInstance() => create();
  static $pb.PbList<Range> createRepeated() => $pb.PbList<Range>();
  @$core.pragma('dart2js:noInline')
  static Range getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<Range>(create);
  static Range _defaultInstance;

  @$pb.TagNumber(1)
  $core.int get min => $_getIZ(0);
  @$pb.TagNumber(1)
  set min($core.int v) { $_setSignedInt32(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasMin() => $_has(0);
  @$pb.TagNumber(1)
  void clearMin() => clearField(1);

  @$pb.TagNumber(2)
  $core.int get max => $_getIZ(1);
  @$pb.TagNumber(2)
  set max($core.int v) { $_setSignedInt32(1, v); }
  @$pb.TagNumber(2)
  $core.bool hasMax() => $_has(1);
  @$pb.TagNumber(2)
  void clearMax() => clearField(2);
}

