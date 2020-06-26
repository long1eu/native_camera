// File created by
// Lung Razvan <long1eu>
// on 20/06/2020

library native_camera2;

import 'dart:async';
import 'dart:typed_data';
import 'dart:ui';

import 'package:collection/collection.dart';
import 'package:flutter/material.dart';
import 'package:flutter/material.dart' as flutter;
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';

import 'src/protos.dart' as p;

export 'src/protos.dart';

part 'src/camera_aspect_ratio.dart';

class Camera extends StatefulWidget {
  const Camera({
    Key key,
    @required this.value,
    this.onCameraReady,
  }) : super(key: key);

  final CameraValue value;
  final ValueChanged<CameraController> onCameraReady;

  @override
  _CameraState createState() => _CameraState();
}

class _CameraState extends State<Camera> with WidgetsBindingObserver {
  CameraController _controller;

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addObserver(this);
    _initialize();
  }

  @override
  void didChangeMetrics() {
    super.didChangeMetrics();

    print('didChangeMetrics');
    setState(() {});
  }

  Future<void> _initialize() async {
    _controller = CameraController(widget.value);
    final CameraValue value = _controller.value;

    await _controller.initialize(
      cameraId: value.cameraId,
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

    widget.onCameraReady?.call(_controller);
    setState(() {});
  }

  @override
  void didUpdateWidget(Camera oldWidget) {
    super.didUpdateWidget(oldWidget);

    if (_controller.isInitialized && widget.value != oldWidget.value) {
      _controller.update(widget.value);
    }
  }

  @override
  void dispose() {
    _controller.dispose();
    WidgetsBinding.instance.removeObserver(this);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    if (!_controller.isInitialized) {
      return Container();
    }

    final bool isPortrait = MediaQuery.of(context).orientation == Orientation.portrait;

    double width = _controller.value.previewSize.width.toDouble();
    double height = _controller.value.previewSize.height.toDouble();
    if (isPortrait) {
      double tmp = width;
      width = height;
      height = tmp;
    }

    final double ratio = _controller.value.ratio.getValue(isPortrait);
    return Container(
      color: Colors.red,
      child: Container(
        // width: width,
        // height: height,
        color: Colors.green[800],
        child: Center(
          child: flutter.AspectRatio(
            aspectRatio: ratio,
            child: RotatedBox(
              quarterTurns: isPortrait ? 0 : 1,
              child: GestureDetector(
                onTapUp: (TapUpDetails details) {
                  _controller.autoFocusPointOfInterest(details.globalPosition);
                },
                child: Texture(
                  textureId: _controller._textureId,
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}

class CameraValue {
  const CameraValue({
    @required this.cameraId,
    this.zoom = 0.0,
    this.maxZoom = 0.0,
    this.ratio = const CameraAspectRatio(4, 3),
    this.focusDepth = 0.0,
    this.autoFocus = true,
    this.flash = p.CameraState_Flash.FLASH_AUTO,
    this.exposure = 0.0,
    this.whiteBalance = p.CameraState_WhiteBalance.WHITE_BALANCE_AUTO,
    this.playSoundOnCapture = true,
    this.useCamera2 = true,
  })  : orientation = null,
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
    this.cameraId,
    this.orientation,
    this.previewSize,
    this.supportedRatios,
    this.supportedPreviewFps,
    this.zoom = 0.0,
    this.maxZoom = 0.0,
    this.ratio = const CameraAspectRatio(4, 3),
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

  final String cameraId;
  final p.CameraState_Orientation orientation;
  final p.Size previewSize;
  final List<CameraAspectRatio> supportedRatios;
  final List<p.Range> supportedPreviewFps;
  final double zoom;
  final double maxZoom;
  final CameraAspectRatio ratio;
  final double focusDepth;
  final bool autoFocus;
  final p.CameraState_Flash flash;
  final double exposure;
  final p.CameraState_WhiteBalance whiteBalance;
  final bool playSoundOnCapture;
  final bool useCamera2;

  bool get isPortrait {
    return orientation == p.CameraState_Orientation.ORIENTATION_UP ||
        orientation == p.CameraState_Orientation.ORIENTATION_DOWN;
  }

  bool get isLandscape => !isPortrait;

  CameraValue copyWith({
    String cameraId,
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
      cameraId: cameraId ?? this.cameraId,
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

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is CameraValue &&
          runtimeType == other.runtimeType &&
          cameraId == other.cameraId &&
          orientation == other.orientation &&
          previewSize == other.previewSize &&
          ListEquality<CameraAspectRatio>().equals(supportedRatios, other.supportedRatios) &&
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
      cameraId.hashCode ^
      orientation.hashCode ^
      previewSize.hashCode ^
      ListEquality<CameraAspectRatio>().hash(supportedRatios) ^
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
  CameraController(CameraValue value) : super(value);

  StreamSubscription<dynamic> _sub;

  static int _nextId = 0;

  int _textureId;
  String _channelName;

  bool get isInitialized => _textureId != null;

  Future<p.InitializeCameraResponse> initialize({
    @required String cameraId,
    @required double zoom,
    @required double maxZoom,
    @required CameraAspectRatio ratio,
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
      state: p.CameraState()
        ..cameraId = cameraId
        ..zoom = zoom
        ..maxZoom = maxZoom
        ..ratio = (p.CameraAspectRatio()
          ..x = ratio.x
          ..y = ratio.y)
        ..focusDepth = focusDepth
        ..autoFocus = autoFocus
        ..flash = flash
        ..exposure = exposure
        ..whiteBalance = whiteBalance
        ..playSoundOnCapture = playSoundOnCapture
        ..useCamera2 = useCamera2,
    );

    final int textureId = result.textureId.toInt();
    final p.CameraState state = result.state;
    _textureId = textureId;
    _channelName = channelName;

    value = CameraValue._(
      cameraId: cameraId,
      orientation: state.orientation,
      previewSize: state.previewSize,
      supportedRatios: state.supportedRatio.map((e) => CameraAspectRatio(e.x, e.y)).toList(),
      supportedPreviewFps: state.supportedPreviewFps,
      zoom: state.zoom,
      maxZoom: state.maxZoom,
      ratio: CameraAspectRatio(state.ratio.x, state.ratio.y),
      focusDepth: state.focusDepth,
      autoFocus: state.autoFocus,
      flash: state.flash,
      exposure: state.exposure,
      whiteBalance: state.whiteBalance,
      playSoundOnCapture: state.playSoundOnCapture,
      useCamera2: state.useCamera2,
    );
    print('this.value.initialize: ${this.value.supportedRatios}');
    return result;
  }

  Future<void> update(CameraValue value) async {
    final p.CameraState newState = p.CameraState()
      ..cameraId = value.cameraId
      ..zoom = value.zoom
      ..maxZoom = value.maxZoom
      ..ratio = (p.CameraAspectRatio()
        ..x = value.ratio.x
        ..y = value.ratio.y)
      ..focusDepth = value.focusDepth
      ..autoFocus = value.autoFocus
      ..flash = value.flash
      ..exposure = value.exposure
      ..whiteBalance = value.whiteBalance
      ..playSoundOnCapture = value.playSoundOnCapture
      ..useCamera2 = value.useCamera2;

    p.CameraState result;
    bool cameraChange = this.value.cameraId != value.cameraId;
    bool implementationChange = this.value.useCamera2 != value.useCamera2;

    if (cameraChange) {
      await NativeCamera.instance.setCamera(value.cameraId);
    }

    if (implementationChange) {
      await NativeCamera.instance.setCamera(value.cameraId);
    }

    result = await NativeCamera.instance.update(newState);
    this.value = CameraValue._(
      cameraId: result.cameraId,
      orientation: result.orientation,
      previewSize: result.previewSize,
      supportedRatios: result.supportedRatio.map((e) => CameraAspectRatio(e.x, e.y)).toList(),
      supportedPreviewFps: result.supportedPreviewFps,
      zoom: result.zoom,
      maxZoom: result.maxZoom,
      ratio: CameraAspectRatio(result.ratio.x, result.ratio.y),
      focusDepth: result.focusDepth,
      autoFocus: result.autoFocus,
      flash: result.flash,
      exposure: result.exposure,
      whiteBalance: result.whiteBalance,
      playSoundOnCapture: result.playSoundOnCapture,
      useCamera2: result.useCamera2,
    );
  }

  Future<void> autoFocusPointOfInterest(Offset offset) async {
    final Size physicalSize = window.physicalSize;
    final Size logicalSize = window.physicalSize / window.devicePixelRatio;

    final double x = physicalSize.width * offset.dx / logicalSize.width;
    final double y = physicalSize.height * offset.dy / logicalSize.height;

    return NativeCamera.instance.autoFocusPointOfInterest(x, y);
  }

  Future<p.TakePictureResponse> takePicture({
    String path,
    Map<String, String> exif = const <String, String>{},
    bool writeExif = true,
    double quality = .85,
    bool pauseAfterCapture = false,
    // only used on Android
    bool fixOrientation = true,
    // only used on iOS
    bool forceUpOrientation = true,
    int width,
    bool mirrorImage = false,
    bool doNotSave = false,
    bool returnBytes = false,
    p.CameraState_Orientation orientation = p.CameraState_Orientation.ORIENTATION_AUTO,
  }) async {
    return NativeCamera.instance.takePicture(
      path: path,
      exif: exif,
      writeExif: writeExif,
      quality: quality,
      pauseAfterCapture: pauseAfterCapture,
      fixOrientation: fixOrientation,
      forceUpOrientation: forceUpOrientation,
      width: width,
      mirrorImage: mirrorImage,
      doNotSave: doNotSave,
      returnBytes: returnBytes,
      orientation: orientation,
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
    @required p.CameraState state,
  }) async {
    p.InitializeCameraRequest request = p.InitializeCameraRequest()
      ..channelName = channelName
      ..state = state;

    final Uint8List data = await _channel.invokeMethod('InitializeCamera', request.writeToBuffer());
    return p.InitializeCameraResponse.fromBuffer(data);
  }

  Future<p.CameraState> setCamera(String cameraId) async {
    final Uint8List data = await _channel.invokeMethod('SetCamera', cameraId);
    return p.CameraState.fromBuffer(data);
  }

  Future<p.CameraState> setUsingCamera2Api(bool useCamera2Api) async {
    final Uint8List data = await _channel.invokeMethod('SetUsingCamera2Api', useCamera2Api);
    return p.CameraState.fromBuffer(data);
  }

  Future<p.CameraState> update(p.CameraState request) async {
    final Uint8List data = await _channel.invokeMethod('UpdateCamera', request.writeToBuffer());
    return p.CameraState.fromBuffer(data);
  }

  Future<void> autoFocusPointOfInterest(x, y) {
    p.Point request = p.Point()
      ..x = x
      ..y = y;
    return _channel.invokeMethod('AutoFocusPointOfInterest', request.writeToBuffer());
  }

  Future<p.TakePictureResponse> takePicture({
    String path,
    Map<String, String> exif = const <String, String>{},
    bool writeExif = true,
    double quality = .85,
    bool pauseAfterCapture = false,
    // only used on Android
    bool fixOrientation = true,
    // only used on iOS
    bool forceUpOrientation = true,
    int width,
    bool mirrorImage = false,
    bool doNotSave = false,
    bool returnBytes = false,
    p.CameraState_Orientation orientation = p.CameraState_Orientation.ORIENTATION_AUTO,
  }) async {
    assert(exif != null);
    assert(writeExif != null);
    assert(quality != null && quality > 0 && quality <= 1.0);
    assert(pauseAfterCapture != null);
    assert(fixOrientation != null);
    assert(forceUpOrientation != null);
    assert(mirrorImage != null);
    assert(doNotSave != null);
    assert(returnBytes != null);
    assert(orientation != null);

    p.TakePictureRequest request = p.TakePictureRequest()
      ..exif.addAll(exif)
      ..writeExif = writeExif
      ..quality = quality
      ..pauseAfterCapture = pauseAfterCapture
      ..fixOrientation = fixOrientation
      ..forceUpOrientation = forceUpOrientation
      ..mirrorImage = mirrorImage
      ..doNotSave = doNotSave
      ..returnBytes = returnBytes
      ..orientation = orientation;

    if (path != null) {
      request.path = path;
    }
    if (width != null) {
      request.width = width;
    }

    final Uint8List data = await _channel.invokeMethod('TakePicture', request.writeToBuffer());
    return p.TakePictureResponse.fromBuffer(data);
  }
}
