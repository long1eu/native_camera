// Copyright 2019 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// ignore_for_file: public_member_api_docs

import 'package:flutter/material.dart';
import 'package:native_camera/native_camera2.dart';
import 'package:permission_handler/permission_handler.dart' as p;

void main() => runApp(CameraExampleApp());

class CameraExampleApp extends StatefulWidget {
  const CameraExampleApp({Key key}) : super(key: key);

  @override
  _CameraExampleAppState createState() => _CameraExampleAppState();
}

class _CameraExampleAppState extends State<CameraExampleApp> with WidgetsBindingObserver {
  List<CameraInfo> cameras = <CameraInfo>[];
  p.PermissionStatus status = p.PermissionStatus.denied;
  CameraInfo camera;
  CameraValue value;

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addObserver(this);
    _checkPermission();
  }

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    if (state == AppLifecycleState.resumed) {
      _checkPermission();
    }
  }

  Future<void> _checkPermission() async {
    final p.PermissionStatus status = await p.Permission.camera.status;
    if (status == p.PermissionStatus.granted) {
      final List<CameraInfo> cameras = await NativeCamera.instance.getAvailableCameras();
      this.cameras = cameras;
      if (cameras.isNotEmpty) {
        camera = cameras[0];
      }
    }

    setState(() => this.status = status);
  }

  Future<void> _requestPermission() async {
    await p.Permission.camera.request();
    _checkPermission();
  }

  Widget _getPermissionMessage() {
    if (status == p.PermissionStatus.undetermined) {
      return Center(
        child: RaisedButton(
          child: Text('Start camera'),
          onPressed: _requestPermission,
        ),
      );
    } else if (status == p.PermissionStatus.denied) {
      return Center(
        child: RaisedButton(
          child: Text('Grant camera permission'),
          onPressed: _requestPermission,
        ),
      );
    } else if (status == p.PermissionStatus.restricted) {
      return Center(
        child: Text('You are not allowed to use the camera'),
      );
    } else if (status == p.PermissionStatus.permanentlyDenied) {
      return Center(
        child: RaisedButton(
          child: Text('Open settings to grant camera permission'),
          onPressed: () async {
            await p.openAppSettings();
          },
        ),
      );
    }

    return null;
  }

  String _getFacing(CameraInfo_LensFacing facing) {
    switch (facing) {
      case CameraInfo_LensFacing.FRONT:
        return 'front';
      case CameraInfo_LensFacing.BACK:
        return 'back';
      case CameraInfo_LensFacing.EXTERNAL:
        return 'external';
      default:
        throw ArgumentError('Unknown camera type.');
    }
  }

  @override
  void dispose() {
    WidgetsBinding.instance.addObserver(this);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Builder(
        builder: (BuildContext context) {
          print(value);


          return Scaffold(
            appBar: AppBar(),
            drawer: this.value != null
                ? Drawer(
                    child: ListView(
                      children: <Widget>[
                        ExpansionTile(
                          title: Text('Ratio'),
                          children: value.supportedRatios.map((ratio) {
                            return ListTile(
                              title: Text('${ratio.x}:${ratio.y}'),
                              onTap: () {
                                Navigator.pop(context);
                                setState(() => value = value.copyWith(ratio: ratio));
                              },
                            );
                          }).toList(),
                        ),
                      ],
                    ),
                  )
                : null,
            body: Builder(
              builder: (BuildContext context) {
                final Widget permissionMessage = _getPermissionMessage();
                if (permissionMessage != null) {
                  return permissionMessage;
                }

                return Container(
                  constraints: BoxConstraints.expand(),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: <Widget>[
                      Row(
                        mainAxisSize: MainAxisSize.min,
                        children: cameras.map((camera) {
                          return Container(
                            margin: const EdgeInsets.symmetric(horizontal: 2.0),
                            child: ChoiceChip(
                              label: Text(_getFacing(camera.facing)),
                              selected: this.camera == camera,
                              onSelected: (bool selected) {
                                if (selected) {
                                  setState(() => this.camera = camera);
                                }
                              },
                            ),
                          );
                        }).toList(),
                      ),
                      if (camera != null)
                        Expanded(
                          child: Camera(
                            camera: camera,
                            onChange: (CameraValue value) {
                              setState(() => this.value = value);
                            },
                          ),
                        ),
                    ],
                  ),
                );
              },
            ),
          );
        },
      ),
    );
  }
}
