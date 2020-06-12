package org.reactnative.camera;

import androidx.annotation.Nullable;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;

public class DartMessenger {
    @Nullable
    private EventChannel.EventSink eventSink;


    public DartMessenger(BinaryMessenger messenger, long eventChannelId) {
        new EventChannel(messenger, "native_camera/" + eventChannelId)
                .setStreamHandler(
                        new EventChannel.StreamHandler() {
                            @Override
                            public void onListen(Object arguments, EventChannel.EventSink sink) {
                                eventSink = sink;
                            }

                            @Override
                            public void onCancel(Object arguments) {
                                eventSink = null;
                            }
                        });
    }

    @Nullable
    public EventChannel.EventSink getEventSink() {
        return eventSink;
    }
}
