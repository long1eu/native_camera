package org.reactnative.camera.events;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.EventChannel;

abstract class Event<T> {
    public void dispatch(EventChannel.EventSink sink) {
        final Map<String, Object> data = new HashMap<>();
        data.put("event", getName());
        serializeEventData(data);
        sink.success(data);
    }

    abstract String getName();

    void serializeEventData(Map<String, Object> data) {
    }
}
