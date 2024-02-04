package com.google.android.filament;

public class NativeSurface {
    private final int mHeight;
    private final long mNativeObject;
    private final int mWidth;

    private static native long nCreateSurface(int i, int i2);

    private static native void nDestroySurface(long j);

    public NativeSurface(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
        this.mNativeObject = nCreateSurface(i, i2);
    }

    public void dispose() {
        nDestroySurface(this.mNativeObject);
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public long getNativeObject() {
        return this.mNativeObject;
    }
}
