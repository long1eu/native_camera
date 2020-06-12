/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.cameraview;


import android.graphics.SurfaceTexture;
import android.view.Surface;

class TextureViewPreview extends PreviewImpl {

    private final SurfaceTexture mSurfaceTexture;

    TextureViewPreview(SurfaceTexture mSurfaceTexture) {
        this.mSurfaceTexture = mSurfaceTexture;
    }

    // This method is called only from Camera2.
    @Override
    void setBufferSize(int width, int height) {
        mSurfaceTexture.setDefaultBufferSize(width, height);
    }

    @Override
    Surface getSurface() {
        return new Surface(mSurfaceTexture);
    }

    @Override
    SurfaceTexture getmSurfaceTexture() {
        return mSurfaceTexture;
    }

    @Override
    Class getOutputClass() {
        return SurfaceTexture.class;
    }

    @Override
    boolean isReady() {
        return mSurfaceTexture != null;
    }
}
