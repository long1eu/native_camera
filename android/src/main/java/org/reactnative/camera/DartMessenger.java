package org.reactnative.camera;

import androidx.annotation.Nullable;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;

public class DartMessenger implements EventChannel.StreamHandler {
  @Nullable
  private EventChannel.EventSink eventSink;
  private String name;

  public DartMessenger(BinaryMessenger messenger, long eventChannelId) {
    this.name = "native_camera/" + eventChannelId;
    new EventChannel(messenger, this.name).setStreamHandler(this);
  }

  @Override
  public void onListen(Object arguments, EventChannel.EventSink sink) {
    eventSink = sink;
  }

  @Override
  public void onCancel(Object arguments) {
    eventSink = null;
  }

  @Nullable
  public EventChannel.EventSink getEventSink() {
    return eventSink;
  }

  public String getName() {
    return name;
  }
}
