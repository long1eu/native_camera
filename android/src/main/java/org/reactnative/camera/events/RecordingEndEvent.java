package org.reactnative.camera.events;

import androidx.core.util.Pools;

public class RecordingEndEvent extends Event<RecordingEndEvent> {
    private static final Pools.SynchronizedPool<RecordingEndEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);

    private RecordingEndEvent() {
    }

    public static RecordingEndEvent obtain() {
        RecordingEndEvent event = EVENTS_POOL.acquire();
        if (event == null) {
            event = new RecordingEndEvent();
        }
        return event;
    }


    @Override
    String getName() {
        return "onRecordingEnd";
    }
}
