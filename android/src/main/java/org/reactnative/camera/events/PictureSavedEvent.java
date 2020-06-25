package org.reactnative.camera.events;

import androidx.core.util.Pools;

import java.util.Map;

import eu.long1.nativecamera.proto.TakePictureResponse;

public class PictureSavedEvent extends Event<PictureSavedEvent> {
  private static final Pools.SynchronizedPool<PictureSavedEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(5);

  private PictureSavedEvent(byte[] response) {
    this.mResponse = response;
  }

  private byte[] mResponse;

  public static PictureSavedEvent obtain(TakePictureResponse response) {
    PictureSavedEvent event = EVENTS_POOL.acquire();
    if (event == null) {
      event = new PictureSavedEvent(response.toByteArray());
    }
    return event;
  }

  @Override
  String getName() {
    return "onPictureSaved";
  }

  @Override
  void serializeEventData(Map<String, Object> data) {
    data.put("response", mResponse);
  }
}
