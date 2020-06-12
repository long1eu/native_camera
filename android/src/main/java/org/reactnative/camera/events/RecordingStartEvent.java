package org.reactnative.camera.events;

import androidx.core.util.Pools;

import java.util.Map;

public class RecordingStartEvent extends Event<RecordingStartEvent> {
    private static final Pools.SynchronizedPool<RecordingStartEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);

    private RecordingStartEvent(Map<String, Object> response) {
        this.mResponse = response;
    }

    private Map<String, Object> mResponse;

    public static RecordingStartEvent obtain(Map<String, Object> response) {
        RecordingStartEvent event = EVENTS_POOL.acquire();
        if (event == null) {
            event = new RecordingStartEvent(response);
        }
        return event;
    }

    @Override
    String getName() {
        return "onRecordingStart";
    }

    @Override
    void serializeEventData(Map<String, Object> data) {
        data.put("response", mResponse);
    }
}
