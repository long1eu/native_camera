package org.reactnative.camera.tasks;

import eu.long1.nativecamera.proto.TakePictureResponse;

public interface PictureSavedDelegate {
  void onPictureSaved(TakePictureResponse response);
}
