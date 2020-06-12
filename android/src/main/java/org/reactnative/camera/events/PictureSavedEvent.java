package org.reactnative.camera.events;

import androidx.core.util.Pools;

import java.util.Map;

public class PictureSavedEvent extends Event<PictureSavedEvent> {
    private static final Pools.SynchronizedPool<PictureSavedEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(5);

    private PictureSavedEvent(Map<String, Object> response) {
        this.mResponse = response;
    }

    private Map<String, Object> mResponse;

    public static PictureSavedEvent obtain(Map<String, Object> response) {
        PictureSavedEvent event = EVENTS_POOL.acquire();
        if (event == null) {
            event = new PictureSavedEvent(response);
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
