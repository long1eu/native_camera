// File created by
// Lung Razvan <long1eu>
// on 20/06/2020

library native_camera2;

import 'dart:async';
import 'dart:typed_data';

import 'package:collection/collection.dart';
import 'package:flutter/material.dart';
import 'package:flutter/material.dart' as flutter;
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';

import 'src/protos.dart' as p;

export 'src/protos.dart';

part 'src/aspect_ratio.dart';

class Camera extends StatefulWidget {
  const Camera({
    Key key,
    @required this.camera,
    this.onChange,
    this.onCameraReady,
    this.value = const CameraValue(),
  }) : super(key: key);

  final p.CameraInfo camera;
  final ValueChanged<CameraValue> onChange;
  final CameraValue value;
  final ValueChanged<CameraController> onCameraReady;

  @override
  _CameraState createState() => _CameraState();
}

class _CameraState extends State<Camera> {
  CameraController _controller;

  @override
  void initState() {
    super.initState();
    _controller = CameraController();
    _controller.addListener(_valueChanged);
    _initialize();
  }

  Future<void> _initialize() async {
    final CameraValue value = _controller.value;
    await _controller.initialize(
      cameraId: widget.camera.id,
      zoom: value.zoom,
      maxZoom: value.maxZoom,
      ratio: value.ratio,
      focusDepth: value.focusDepth,
      autoFocus: value.autoFocus,
      flash: value.flash,
      exposure: value.exposure,
      whiteBalance: value.whiteBalance,
      playSoundOnCapture: value.playSoundOnCapture,
      useCamera2: value.useCamera2,
    );
  }

  @override
  void didUpdateWidget(Camera oldWidget) {
    super.didUpdateWidget(oldWidget);

    if (_controller.value.isInitialized && widget.value != oldWidget.value) {
      _controller.update(widget.value);
    }
  }

  void _valueChanged() {
    widget.onChange?.call(_controller.value);
    print(_controller.value);

    if (_controller.value != widget.value) {
      _controller.update(_controller.value);
    }
    setState(() {});
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    if (_controller.value.textureId == null) {
      return Container();
    }

    print(_controller.value.ratio.value);
    return Center(
      child: flutter.AspectRatio(
        aspectRatio: _controller.value.ratio.value,
        child: Texture(
          textureId: _controller.value.textureId,
        ),
      ),
    );
  }
}

class CameraValue {
  const CameraValue({
    this.zoom = 0.0,
    this.maxZoom = 0.0,
    this.ratio = const AspectRatio(4, 3),
    this.focusDepth = 0.0,
    this.autoFocus = true,
    this.flash = p.CameraState_Flash.FLASH_AUTO,
    this.exposure = 0.0,
    this.whiteBalance = p.CameraState_WhiteBalance.WHITE_BALANCE_AUTO,
    this.playSoundOnCapture = true,
    this.useCamera2 = true,
  })  : textureId = null,
        cameraId = null,
        channelName = null,
        orientation = null,
        previewSize = null,
        supportedRatios = null,
        supportedPreviewFps = null,
        assert(zoom != null),
        assert(maxZoom != null),
        assert(ratio != null),
        assert(focusDepth != null),
        assert(autoFocus != null),
        assert(flash != null),
        assert(exposure != null),
        assert(whiteBalance != null),
        assert(playSoundOnCapture != null),
        assert(useCamera2 != null);

  const CameraValue._({
    this.textureId,
    this.cameraId,
    this.channelName,
    this.orientation,
    this.previewSize,
    this.supportedRatios,
    this.supportedPreviewFps,
    this.zoom = 0.0,
    this.maxZoom = 0.0,
    this.ratio = const AspectRatio(4, 3),
    this.focusDepth = 0.0,
    this.autoFocus = true,
    this.flash = p.CameraState_Flash.FLASH_AUTO,
    this.exposure = 0.0,
    this.whiteBalance = p.CameraState_WhiteBalance.WHITE_BALANCE_AUTO,
    this.playSoundOnCapture = true,
    this.useCamera2 = true,
  })  : assert(zoom != null),
        assert(maxZoom != null),
        assert(ratio != null),
        assert(focusDepth != null),
        assert(autoFocus != null),
        assert(flash != null),
        assert(exposure != null),
        assert(whiteBalance != null),
        assert(playSoundOnCapture != null),
        assert(useCamera2 != null);

  bool get isInitialized => textureId != null;

  final int textureId;
  final String cameraId;
  final String channelName;
  final p.CameraState_Orientation orientation;
  final p.Size previewSize;
  final List<AspectRatio> supportedRatios;
  final List<p.Range> supportedPreviewFps;
  final double zoom;
  final double maxZoom;
  final AspectRatio ratio;
  final double focusDepth;
  final bool autoFocus;
  final p.CameraState_Flash flash;
  final double exposure;
  final p.CameraState_WhiteBalance whiteBalance;
  final bool playSoundOnCapture;
  final bool useCamera2;

  CameraValue copyWith({
    double zoom,
    double maxZoom,
    AspectRatio ratio,
    double focusDepth,
    bool autoFocus,
    p.CameraState_Flash flash,
    double exposure,
    p.CameraState_WhiteBalance whiteBalance,
    bool playSoundOnCapture,
    bool useCamera2,
  }) {
    return CameraValue._(
      textureId: textureId,
      cameraId: cameraId,
      channelName: channelName,
      orientation: orientation,
      previewSize: previewSize,
      supportedRatios: supportedRatios,
      supportedPreviewFps: supportedPreviewFps,
      zoom: zoom ?? this.zoom,
      maxZoom: maxZoom ?? this.maxZoom,
      ratio: ratio ?? this.ratio,
      focusDepth: focusDepth ?? this.focusDepth,
      autoFocus: autoFocus ?? this.autoFocus,
      flash: flash ?? this.flash,
      exposure: exposure ?? this.exposure,
      whiteBalance: whiteBalance ?? this.whiteBalance,
      playSoundOnCapture: playSoundOnCapture ?? this.playSoundOnCapture,
      useCamera2: useCamera2 ?? this.useCamera2,
    );
  }

  CameraValue _internalCopyWith({
    int textureId,
    String cameraId,
    String channelName,
    p.CameraState_Orientation orientation,
    p.Size previewSize,
    List<AspectRatio> supportedRatios,
    List<p.Range> supportedPreviewFps,
    double zoom,
    double maxZoom,
    AspectRatio ratio,
    double focusDepth,
    bool autoFocus,
    p.CameraState_Flash flash,
    double exposure,
    p.CameraState_WhiteBalance whiteBalance,
    bool playSoundOnCapture,
    bool useCamera2,
  }) {
    return CameraValue._(
      textureId: textureId ?? this.textureId,
      cameraId: cameraId ?? this.cameraId,
      channelName: channelName ?? this.channelName,
      orientation: orientation ?? this.orientation,
      previewSize: previewSize ?? this.previewSize,
      supportedRatios: supportedRatios ?? this.supportedRatios,
      supportedPreviewFps: supportedPreviewFps ?? this.supportedPreviewFps,
      zoom: zoom ?? this.zoom,
      maxZoom: maxZoom ?? this.maxZoom,
      ratio: ratio ?? this.ratio,
      focusDepth: focusDepth ?? this.focusDepth,
      autoFocus: autoFocus ?? this.autoFocus,
      flash: flash ?? this.flash,
      exposure: exposure ?? this.exposure,
      whiteBalance: whiteBalance ?? this.whiteBalance,
      playSoundOnCapture: playSoundOnCapture ?? this.playSoundOnCapture,
      useCamera2: useCamera2 ?? this.useCamera2,
    );
  }

  @override
  String toString() {
    return 'CameraValue{textureId: $textureId, cameraId: $cameraId, channelName: $channelName, orientation: $orientation, previewSize: $previewSize, supportedRatios: $supportedRatios, supportedPreviewFps: $supportedPreviewFps, zoom: $zoom, maxZoom: $maxZoom, ratio: $ratio, focusDepth: $focusDepth, autoFocus: $autoFocus, flash: $flash, exposure: $exposure, whiteBalance: $whiteBalance, playSoundOnCapture: $playSoundOnCapture, useCamera2: $useCamera2}';
  }

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is CameraValue &&
          runtimeType == other.runtimeType &&
          textureId == other.textureId &&
          cameraId == other.cameraId &&
          channelName == other.channelName &&
          orientation == other.orientation &&
          previewSize == other.previewSize &&
          ListEquality<AspectRatio>().equals(supportedRatios, other.supportedRatios) &&
          ListEquality<p.Range>().equals(supportedPreviewFps, other.supportedPreviewFps) &&
          zoom == other.zoom &&
          maxZoom == other.maxZoom &&
          ratio == other.ratio &&
          focusDepth == other.focusDepth &&
          autoFocus == other.autoFocus &&
          flash == other.flash &&
          exposure == other.exposure &&
          whiteBalance == other.whiteBalance &&
          playSoundOnCapture == other.playSoundOnCapture &&
          useCamera2 == other.useCamera2;

  @override
  int get hashCode =>
      textureId.hashCode ^
      cameraId.hashCode ^
      channelName.hashCode ^
      orientation.hashCode ^
      previewSize.hashCode ^
      ListEquality<AspectRatio>().hash(supportedRatios) ^
      ListEquality<p.Range>().hash(supportedPreviewFps) ^
      zoom.hashCode ^
      maxZoom.hashCode ^
      ratio.hashCode ^
      focusDepth.hashCode ^
      autoFocus.hashCode ^
      flash.hashCode ^
      exposure.hashCode ^
      whiteBalance.hashCode ^
      playSoundOnCapture.hashCode ^
      useCamera2.hashCode;
}

class CameraController extends ValueNotifier<CameraValue> {
  CameraController() : super(const CameraValue());

  StreamSubscription<dynamic> _sub;

  static int _nextId = 0;

  Future<p.InitializeCameraResponse> initialize({
    @required String cameraId,
    @required double zoom,
    @required double maxZoom,
    @required AspectRatio ratio,
    @required double focusDepth,
    @required bool autoFocus,
    @required p.CameraState_Flash flash,
    @required double exposure,
    @required p.CameraState_WhiteBalance whiteBalance,
    @required bool playSoundOnCapture,
    @required bool useCamera2,
  }) async {
    final int channelId = _nextId++;
    final String channelName = await NativeCamera.instance.createChannelName(channelId);
    _sub = EventChannel(channelName).receiveBroadcastStream().listen(_onEvent);

    final p.InitializeCameraResponse result = await NativeCamera.instance.initialize(
      channelName: channelName,
      cameraId: cameraId,
      zoom: zoom,
      maxZoom: maxZoom,
      ratio: p.AspectRatio()
        ..x = ratio.x
        ..y = ratio.y,
      focusDepth: focusDepth,
      autoFocus: autoFocus,
      flash: flash,
      exposure: exposure,
      whiteBalance: whiteBalance,
      playSoundOnCapture: playSoundOnCapture,
      useCamera2: useCamera2,
    );

    final int textureId = result.textureId.toInt();
    final p.CameraState state = result.state;
    value = value._internalCopyWith(
      textureId: textureId,
      cameraId: cameraId,
      channelName: channelName,
      orientation: result.orientation,
      previewSize: result.previewSize,
      supportedRatios: result.supportedRatio.map((e) => AspectRatio(e.x, e.y)).toList(),
      supportedPreviewFps: result.supportedPreviewFps,
      zoom: state.zoom,
      maxZoom: state.maxZoom,
      ratio: AspectRatio(state.ratio.x, state.ratio.y),
      focusDepth: state.focusDepth,
      autoFocus: state.autoFocus,
      flash: state.flash,
      exposure: state.exposure,
      whiteBalance: state.whiteBalance,
      playSoundOnCapture: state.playSoundOnCapture,
      useCamera2: state.useCamera2,
    );
    return result;
  }

  Future<void> update(CameraValue value) async {
    print('update: ');
    final p.CameraState newState = p.CameraState()
      ..zoom = value.zoom
      ..maxZoom = value.maxZoom
      ..ratio = (p.AspectRatio()
        ..x = value.ratio.x
        ..y = value.ratio.y)
      ..focusDepth = value.focusDepth
      ..autoFocus = value.autoFocus
      ..flash = value.flash
      ..exposure = value.exposure
      ..whiteBalance = value.whiteBalance
      ..playSoundOnCapture = value.playSoundOnCapture
      ..useCamera2 = value.useCamera2;

    final p.CameraState result = await NativeCamera.instance.update(newState);
    this.value = value._internalCopyWith(
      zoom: result.zoom,
      maxZoom: result.maxZoom,
      ratio: AspectRatio(result.ratio.x, result.ratio.y),
      focusDepth: result.focusDepth,
      cameraId: result.cameraId,
      autoFocus: result.autoFocus,
      flash: result.flash,
      exposure: result.exposure,
      whiteBalance: result.whiteBalance,
      playSoundOnCapture: result.playSoundOnCapture,
      useCamera2: result.useCamera2,
    );
  }

  void _onEvent(event) {
    print('_onEvent: $event');
  }

  void dispose() {
    _sub?.cancel();
    super.dispose();
  }
}

class NativeCamera {
  const NativeCamera._() : _channel = const MethodChannel('native_camera');

  static const NativeCamera instance = NativeCamera._();

  final MethodChannel _channel;

  /// Before calling this method you need to make sure you have camera permission.
  /// If not, this method will throw.
  Future<List<p.CameraInfo>> getAvailableCameras() async {
    final Uint8List data = await _channel.invokeMethod('ListCameras');
    return p.ListCamerasResponse.fromBuffer(data).cameras;
  }

  Future<String> createChannelName(int channelId) async {
    return _channel.invokeMethod("CreateEventChannel", channelId);
  }

  /// Before calling this method you need to make sure you have camera permission.
  /// If not, this method will throw.
  Future<p.InitializeCameraResponse> initialize({
    @required String channelName,
    @required String cameraId,
    @required double zoom,
    @required double maxZoom,
    @required p.AspectRatio ratio,
    @required double focusDepth,
    @required bool autoFocus,
    @required p.CameraState_Flash flash,
    @required double exposure,
    @required p.CameraState_WhiteBalance whiteBalance,
    @required bool playSoundOnCapture,
    @required bool useCamera2,
  }) async {
    assert(channelName != null);
    assert(zoom != null);
    assert(maxZoom != null);
    assert(ratio != null);
    assert(focusDepth != null);
    assert(autoFocus != null);
    assert(flash != null);
    assert(exposure != null);
    assert(whiteBalance != null);
    assert(playSoundOnCapture != null);
    assert(useCamera2 != null);

    p.InitializeCameraRequest request = p.InitializeCameraRequest()
      ..channelName = channelName
      ..state = (p.CameraState()
        ..cameraId = cameraId
        ..zoom = zoom
        ..maxZoom = maxZoom
        ..ratio = ratio
        ..focusDepth = focusDepth
        ..autoFocus = autoFocus
        ..flash = flash
        ..exposure = exposure
        ..whiteBalance = whiteBalance
        ..playSoundOnCapture = playSoundOnCapture
        ..useCamera2 = useCamera2);

    final Uint8List data = await _channel.invokeMethod('InitializeCamera', request.writeToBuffer());
    return p.InitializeCameraResponse.fromBuffer(data);
  }

  Future<p.CameraState> update(p.CameraState request) async {
    final Uint8List data = await _channel.invokeMethod('UpdateCamera', request.writeToBuffer());
    return p.CameraState.fromBuffer(data);
  }
}
