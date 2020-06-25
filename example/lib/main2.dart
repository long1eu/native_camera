// Copyright 2019 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// ignore_for_file: public_member_api_docs

import 'dart:io';

import 'package:flutter/material.dart';
import 'package:native_camera/native_camera2.dart';
import 'package:native_camera_example/permission_mixin.dart';

void main() => runApp(CameraExampleApp());

class CameraExampleApp extends StatefulWidget {
  const CameraExampleApp({Key key}) : super(key: key);

  @override
  _CameraExampleAppState createState() => _CameraExampleAppState();
}

class _CameraExampleAppState extends State<CameraExampleApp>
    with WidgetsBindingObserver, PermissionMixin<CameraExampleApp> {
  List<CameraInfo> cameras = <CameraInfo>[];
  CameraController controller;

  int cameraIndex;
  CameraState_Flash flash = CameraState_Flash.FLASH_AUTO;
  CameraAspectRatio ratio = CameraAspectRatio(16, 9);
  File lastImage;

  @override
  Future<void> onPermissionGranted() async {
    final List<CameraInfo> cameras = await NativeCamera.instance.getAvailableCameras();
    this.cameras = cameras;
    if (cameras.isNotEmpty) {
      cameraIndex = 0;
    }
  }

  void _onChanged() {
    print(controller.value.supportedRatios);
    setState(() {
      // changes to the values of the camera controller
    });
  }

  IconData getCameraIcon() {
    final CameraInfo camera = cameras[nextCamera];
    switch (camera.facing) {
      case CameraInfo_LensFacing.FRONT:
        return Icons.camera_front;
      case CameraInfo_LensFacing.BACK:
        return Icons.camera_rear;
      case CameraInfo_LensFacing.EXTERNAL:
        return Icons.insert_link;
      default:
        throw ArgumentError('Unknown camera source.');
    }
  }

  IconData getFlashIcon(CameraState_Flash flash) {
    switch (flash) {
      case CameraState_Flash.FLASH_ON:
        return Icons.flash_on;
      case CameraState_Flash.FLASH_AUTO:
        return Icons.flash_auto;
      case CameraState_Flash.FLASH_OFF:
        return Icons.flash_off;
      case CameraState_Flash.FLASH_TORCH:
        return Icons.lightbulb_outline;
      case CameraState_Flash.FLASH_RED_EYE:
        return Icons.remove_red_eye;
      default:
        throw ArgumentError('Unknown camera source.');
    }
  }

  int get nextCamera {
    final int maxIndex = cameras.length - 1;
    if (cameraIndex == maxIndex) {
      return 0;
    } else {
      return cameraIndex + 1;
    }
  }

  @override
  void dispose() {
    controller?.dispose();
    WidgetsBinding.instance.addObserver(this);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Builder(
        builder: (BuildContext context) {
          return Scaffold(
            body: SafeArea(
              child: Builder(
                builder: (BuildContext context) {
                  final Widget permissionMessage = getPermissionMessage();
                  if (permissionMessage != null) {
                    return permissionMessage;
                  }

                  final bool isPortrait = MediaQuery.of(context).orientation == Orientation.portrait;
                  return Container(
                    constraints: BoxConstraints.expand(),
                    child: Stack(
                      children: <Widget>[
                        if (cameraIndex != null)
                          Camera(
                            value: CameraValue(
                              cameraId: cameras[cameraIndex].id,
                              flash: flash,
                              ratio: ratio,
                              autoFocus: true,
                            ),
                            onCameraReady: (CameraController controller) {
                              setState(() {
                                this.controller = controller;
                                controller.addListener(_onChanged);
                              });
                            },
                          ),
                        if (controller != null) ...<Widget>[
                          Container(
                            alignment: AlignmentDirectional.topStart,
                            child: Row(
                              children: [
                                IconButton(
                                  icon: Icon(
                                    getCameraIcon(),
                                    color: Colors.white,
                                  ),
                                  onPressed: () {
                                    setState(() => cameraIndex = nextCamera);
                                  },
                                ),
                                ...CameraState_Flash.values
                                    // red eye is only available on Android
                                    .where((element) => Platform.isIOS || element != CameraState_Flash.FLASH_RED_EYE)
                                    .map((CameraState_Flash flash) {
                                  return IconButton(
                                    icon: Icon(
                                      getFlashIcon(flash),
                                      color: this.flash == flash ? Theme.of(context).primaryColor : Colors.white,
                                    ),
                                    onPressed: () {
                                      setState(() => this.flash = flash);
                                    },
                                  );
                                }),
                              ],
                            ),
                          ),
                          Container(
                            padding: EdgeInsetsDirectional.only(
                              bottom: isPortrait ? 64.0 : 0.0,
                              start: isPortrait ? 0.0 : 32.0,
                            ),
                            child: Flex(
                              direction: isPortrait ? Axis.horizontal : Axis.vertical,
                              crossAxisAlignment: CrossAxisAlignment.start,
                              mainAxisAlignment: MainAxisAlignment.spaceBetween,
                              children: [
                                const Spacer(),
                                Expanded(
                                  child: Container(
                                    alignment: isPortrait
                                        ? AlignmentDirectional.bottomCenter
                                        : AlignmentDirectional.centerStart,
                                    child: SizedBox(
                                      width: 64.0,
                                      height: 64.0,
                                      child: Material(
                                        elevation: 6.0,
                                        shape: const CircleBorder(),
                                        child: InkWell(
                                          customBorder: const CircleBorder(),
                                          onTap: () async {
                                            final TakePictureResponse result = await controller.takePicture();
                                            setState(() => lastImage = File.fromUri(Uri.parse(result.uri)));
                                          },
                                        ),
                                      ),
                                    ),
                                  ),
                                ),
                                Expanded(
                                  child: Container(
                                    alignment:
                                        isPortrait ? AlignmentDirectional.bottomEnd : AlignmentDirectional.centerEnd,
                                    padding: EdgeInsetsDirectional.only(end: 16.0),
                                    child: Container(
                                      width: 64.0,
                                      height: 64.0,
                                      color: Colors.grey.shade900,
                                      child: lastImage != null
                                          ? Image.file(
                                              lastImage,
                                              fit: BoxFit.cover,
                                            )
                                          : Icon(
                                              Icons.photo,
                                              color: Colors.white,
                                            ),
                                    ),
                                  ),
                                ),
                              ],
                            ),
                          ),
                          Container(
                            alignment: AlignmentDirectional.bottomStart,
                            child: Container(
                              height: 32.0,
                              child: ListView(
                                scrollDirection: Axis.horizontal,
                                children: controller.value.supportedRatios.map((CameraAspectRatio ratio) {
                                  return IconButton(
                                    icon: Text(
                                      '${ratio.x}:${ratio.y}',
                                      style: TextStyle(
                                        color: this.ratio == ratio ? Theme.of(context).primaryColor : Colors.white,
                                      ),
                                    ),
                                    onPressed: () {
                                      setState(() => this.ratio = ratio);
                                    },
                                  );
                                }).toList(),
                              ),
                            ),
                          ),
                        ],
                      ],
                    ),
                  );
                },
              ),
            ),
          );
        },
      ),
    );
  }
}
