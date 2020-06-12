package org.reactnative.camera.events;

import androidx.core.util.Pools;

public class CameraReadyEvent extends Event<CameraReadyEvent> {
    private static final Pools.SynchronizedPool<CameraReadyEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);

    private CameraReadyEvent() {
    }

    public static CameraReadyEvent obtain() {
        CameraReadyEvent event = EVENTS_POOL.acquire();
        if (event == null) {
            event = new CameraReadyEvent();
        }
        return event;
    }

    @Override
    String getName() {
        return "onCameraReady";
    }
}
