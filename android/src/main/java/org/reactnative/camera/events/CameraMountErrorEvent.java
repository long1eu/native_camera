package org.reactnative.camera.events;

import androidx.core.util.Pools;

import java.util.Map;

public class CameraMountErrorEvent extends Event<CameraMountErrorEvent> {
    private static final Pools.SynchronizedPool<CameraMountErrorEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private String mError;

    private CameraMountErrorEvent(String error) {
        this.mError = error;
    }

    public static CameraMountErrorEvent obtain(String error) {
        CameraMountErrorEvent event = EVENTS_POOL.acquire();
        if (event == null) {
            event = new CameraMountErrorEvent(error);
        }
        return event;
    }

    @Override
    String getName() {
        return "onMountError";
    }

    public void serializeEventData(Map<String, Object> data) {
        data.put("message", mError);
    }
}
