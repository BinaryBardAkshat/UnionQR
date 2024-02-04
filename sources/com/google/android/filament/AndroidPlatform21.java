package com.google.android.filament;

import android.opengl.EGLContext;

final class AndroidPlatform21 {
    AndroidPlatform21() {
    }

    static long getSharedContextNativeHandle(Object obj) {
        return ((EGLContext) obj).getNativeHandle();
    }
}
