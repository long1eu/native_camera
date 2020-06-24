package eu.long1.nativecamera.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import eu.long1.nativecamera.proto.CameraInfo;
import eu.long1.nativecamera.proto.ListCamerasResponse;

public class CameraUtil {

  public static ListCamerasResponse getCameras(Context context) {
    final ListCamerasResponse.Builder response = ListCamerasResponse.newBuilder();

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      response.addAllCameras(getCamerasIdsCamera1());
    } else {
      response.addAllCameras(getCamerasCamera2(context));
    }

    return response.build();
  }

  private static List<CameraInfo> getCamerasIdsCamera1() {
    final List<CameraInfo> camerasInfo = new ArrayList<>();

    android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
    for (int i = 0, count = android.hardware.Camera.getNumberOfCameras(); i < count; i++) {
      android.hardware.Camera.getCameraInfo(i, info);
      final CameraInfo camera = CameraInfo.newBuilder()
          .setId(String.valueOf(i))
          .setFacingValue(info.facing)
          .build();

      camerasInfo.add(camera);
    }

    return camerasInfo;
  }

  @SuppressWarnings("ConstantConditions")
  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  private static List<CameraInfo> getCamerasCamera2(Context context) {
    try {
      final List<CameraInfo> cameras = new ArrayList<>();
      android.hardware.camera2.CameraManager mCameraManager = (android.hardware.camera2.CameraManager) context.getSystemService(Context.CAMERA_SERVICE);

      final String[] cameraIds = mCameraManager.getCameraIdList();
      for (final String id : cameraIds) {
        final android.hardware.camera2.CameraCharacteristics characteristics = mCameraManager.getCameraCharacteristics(id);
        final Integer facing = characteristics.get(android.hardware.camera2.CameraCharacteristics.LENS_FACING);

        final CameraInfo camera = CameraInfo.newBuilder()
            .setId(id)
            .setFacingValue(facing)
            .build();
        cameras.add(camera);
      }

      return cameras;
    } catch (@SuppressLint("NewApi") android.hardware.camera2.CameraAccessException e) {
      throw new RuntimeException("Failed to get a list of camera ids", e);
    }
  }
}
