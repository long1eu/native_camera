// File created by
// Lung Razvan <long1eu>
// on 25/06/2020

import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:permission_handler/permission_handler.dart' as p;

mixin PermissionMixin<S extends StatefulWidget> on State<S> implements WidgetsBindingObserver {
  p.PermissionStatus status = p.PermissionStatus.denied;

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
      await onPermissionGranted();
    }

    setState(() => this.status = status);
  }

  Future<void> _requestPermission() async {
    await p.Permission.camera.request();
    _checkPermission();
  }

  Widget getPermissionMessage() {
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

  FutureOr<void> onPermissionGranted();
}
