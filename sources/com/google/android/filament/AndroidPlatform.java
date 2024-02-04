package com.google.android.filament;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.util.Log;
import android.view.Surface;

final class AndroidPlatform extends Platform {
    private static final String LOG_TAG = "Filament";

    static {
        EGL14.eglGetDisplay(0);
    }

    AndroidPlatform() {
    }

    /* access modifiers changed from: package-private */
    public void log(String str) {
        Log.d(LOG_TAG, str);
    }

    /* access modifiers changed from: package-private */
    public void warn(String str) {
        Log.w(LOG_TAG, str);
    }

    /* access modifiers changed from: package-private */
    public boolean validateStreamSource(Object obj) {
        return obj instanceof SurfaceTexture;
    }

    /* access modifiers changed from: package-private */
    public boolean validateSurface(Object obj) {
        return obj instanceof Surface;
    }

    /* access modifiers changed from: package-private */
    public boolean validateSharedContext(Object obj) {
        return obj instanceof EGLContext;
    }

    /* access modifiers changed from: package-private */
    public long getSharedContextNativeHandle(Object obj) {
        return AndroidPlatform21.getSharedContextNativeHandle(obj);
    }
}
