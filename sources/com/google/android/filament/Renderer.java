package com.google.android.filament;

import com.google.android.filament.Texture;
import java.nio.Buffer;
import java.nio.BufferOverflowException;
import java.nio.ReadOnlyBufferException;

public class Renderer {
    public static final int MIRROR_FRAME_FLAG_CLEAR = 4;
    public static final int MIRROR_FRAME_FLAG_COMMIT = 1;
    public static final int MIRROR_FRAME_FLAG_SET_PRESENTATION_TIME = 2;
    private final Engine mEngine;
    private long mNativeObject;

    private static native boolean nBeginFrame(long j, long j2);

    private static native void nCopyFrame(long j, long j2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9);

    private static native void nEndFrame(long j);

    private static native double nGetUserTime(long j);

    private static native int nReadPixels(long j, long j2, int i, int i2, int i3, int i4, Buffer buffer, int i5, int i6, int i7, int i8, int i9, int i10, int i11, Object obj, Runnable runnable);

    private static native int nReadPixelsEx(long j, long j2, long j3, int i, int i2, int i3, int i4, Buffer buffer, int i5, int i6, int i7, int i8, int i9, int i10, int i11, Object obj, Runnable runnable);

    private static native void nRender(long j, long j2);

    private static native void nResetUserTime(long j);

    Renderer(Engine engine, long j) {
        this.mEngine = engine;
        this.mNativeObject = j;
    }

    public Engine getEngine() {
        return this.mEngine;
    }

    public boolean beginFrame(SwapChain swapChain) {
        return nBeginFrame(getNativeObject(), swapChain.getNativeObject());
    }

    public void endFrame() {
        nEndFrame(getNativeObject());
    }

    public void render(View view) {
        nRender(getNativeObject(), view.getNativeObject());
    }

    public void copyFrame(SwapChain swapChain, Viewport viewport, Viewport viewport2, int i) {
        Viewport viewport3 = viewport;
        Viewport viewport4 = viewport2;
        nCopyFrame(getNativeObject(), swapChain.getNativeObject(), viewport3.left, viewport3.bottom, viewport3.width, viewport3.height, viewport4.left, viewport4.bottom, viewport4.width, viewport4.height, i);
    }

    @Deprecated
    public void mirrorFrame(SwapChain swapChain, Viewport viewport, Viewport viewport2, int i) {
        copyFrame(swapChain, viewport, viewport2, i);
    }

    public void readPixels(int i, int i2, int i3, int i4, Texture.PixelBufferDescriptor pixelBufferDescriptor) {
        Texture.PixelBufferDescriptor pixelBufferDescriptor2 = pixelBufferDescriptor;
        if (!pixelBufferDescriptor2.storage.isReadOnly()) {
            if (nReadPixels(getNativeObject(), this.mEngine.getNativeObject(), i, i2, i3, i4, pixelBufferDescriptor2.storage, pixelBufferDescriptor2.storage.remaining(), pixelBufferDescriptor2.left, pixelBufferDescriptor2.top, pixelBufferDescriptor2.type.ordinal(), pixelBufferDescriptor2.alignment, pixelBufferDescriptor2.stride, pixelBufferDescriptor2.format.ordinal(), pixelBufferDescriptor2.handler, pixelBufferDescriptor2.callback) < 0) {
                throw new BufferOverflowException();
            }
            return;
        }
        throw new ReadOnlyBufferException();
    }

    public void readPixels(RenderTarget renderTarget, int i, int i2, int i3, int i4, Texture.PixelBufferDescriptor pixelBufferDescriptor) {
        Texture.PixelBufferDescriptor pixelBufferDescriptor2 = pixelBufferDescriptor;
        if (!pixelBufferDescriptor2.storage.isReadOnly()) {
            if (nReadPixelsEx(getNativeObject(), this.mEngine.getNativeObject(), renderTarget.getNativeObject(), i, i2, i3, i4, pixelBufferDescriptor2.storage, pixelBufferDescriptor2.storage.remaining(), pixelBufferDescriptor2.left, pixelBufferDescriptor2.top, pixelBufferDescriptor2.type.ordinal(), pixelBufferDescriptor2.alignment, pixelBufferDescriptor2.stride, pixelBufferDescriptor2.format.ordinal(), pixelBufferDescriptor2.handler, pixelBufferDescriptor2.callback) < 0) {
                throw new BufferOverflowException();
            }
            return;
        }
        throw new ReadOnlyBufferException();
    }

    public double getUserTime() {
        return nGetUserTime(getNativeObject());
    }

    public void resetUserTime() {
        nResetUserTime(getNativeObject());
    }

    public long getNativeObject() {
        long j = this.mNativeObject;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("Calling method on destroyed Renderer");
    }

    /* access modifiers changed from: package-private */
    public void clearNativeObject() {
        this.mNativeObject = 0;
    }
}
