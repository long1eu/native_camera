package org.reactnative.camera.events;

import androidx.core.util.Pools;

public class PictureTakenEvent extends Event<PictureTakenEvent> {
    private static final Pools.SynchronizedPool<PictureTakenEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);

    private PictureTakenEvent() {
    }

    public static PictureTakenEvent obtain() {
        PictureTakenEvent event = EVENTS_POOL.acquire();
        if (event == null) {
            event = new PictureTakenEvent();
        }
        return event;
    }

    @Override
    String getName() {
        return "onPictureTaken";
    }
}
