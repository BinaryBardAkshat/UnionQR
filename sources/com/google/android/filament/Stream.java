package com.google.android.filament;

import com.google.android.filament.Texture;
import java.nio.Buffer;
import java.nio.BufferOverflowException;
import java.nio.ReadOnlyBufferException;

public class Stream {
    private long mNativeEngine;
    private long mNativeObject;

    public enum StreamType {
        NATIVE,
        TEXTURE_ID,
        ACQUIRED
    }

    /* access modifiers changed from: private */
    public static native long nBuilderBuild(long j, long j2);

    /* access modifiers changed from: private */
    public static native void nBuilderHeight(long j, int i);

    /* access modifiers changed from: private */
    public static native void nBuilderStream(long j, long j2);

    /* access modifiers changed from: private */
    public static native void nBuilderStreamSource(long j, Object obj);

    /* access modifiers changed from: private */
    public static native void nBuilderWidth(long j, int i);

    /* access modifiers changed from: private */
    public static native long nCreateBuilder();

    /* access modifiers changed from: private */
    public static native void nDestroyBuilder(long j);

    private static native int nGetStreamType(long j);

    private static native long nGetTimestamp(long j);

    private static native int nReadPixels(long j, long j2, int i, int i2, int i3, int i4, Buffer buffer, int i5, int i6, int i7, int i8, int i9, int i10, int i11, Object obj, Runnable runnable);

    private static native void nSetAcquiredImage(long j, long j2, Object obj, Object obj2, Runnable runnable);

    private static native void nSetDimensions(long j, int i, int i2);

    Stream(long j, Engine engine) {
        this.mNativeObject = j;
        this.mNativeEngine = engine.getNativeObject();
    }

    public static class Builder {
        private final BuilderFinalizer mFinalizer;
        private final long mNativeBuilder;

        public Builder() {
            long access$000 = Stream.nCreateBuilder();
            this.mNativeBuilder = access$000;
            this.mFinalizer = new BuilderFinalizer(access$000);
        }

        public Builder stream(Object obj) {
            if (Platform.get().validateStreamSource(obj)) {
                Stream.nBuilderStreamSource(this.mNativeBuilder, obj);
                return this;
            }
            throw new IllegalArgumentException("Invalid stream source: " + obj);
        }

        public Builder stream(long j) {
            Stream.nBuilderStream(this.mNativeBuilder, j);
            return this;
        }

        public Builder width(int i) {
            Stream.nBuilderWidth(this.mNativeBuilder, i);
            return this;
        }

        public Builder height(int i) {
            Stream.nBuilderHeight(this.mNativeBuilder, i);
            return this;
        }

        public Stream build(Engine engine) {
            long access$500 = Stream.nBuilderBuild(this.mNativeBuilder, engine.getNativeObject());
            if (access$500 != 0) {
                return new Stream(access$500, engine);
            }
            throw new IllegalStateException("Couldn't create Stream");
        }

        private static class BuilderFinalizer {
            private final long mNativeObject;

            BuilderFinalizer(long j) {
                this.mNativeObject = j;
            }

            public void finalize() {
                try {
                    super.finalize();
                } catch (Throwable unused) {
                }
                Stream.nDestroyBuilder(this.mNativeObject);
            }
        }
    }

    public StreamType getStreamType() {
        return StreamType.values()[nGetStreamType(getNativeObject())];
    }

    public void setAcquiredImage(Object obj, Object obj2, Runnable runnable) {
        nSetAcquiredImage(getNativeObject(), this.mNativeEngine, obj, obj2, runnable);
    }

    public void setDimensions(int i, int i2) {
        nSetDimensions(getNativeObject(), i, i2);
    }

    public void readPixels(int i, int i2, int i3, int i4, Texture.PixelBufferDescriptor pixelBufferDescriptor) {
        Texture.PixelBufferDescriptor pixelBufferDescriptor2 = pixelBufferDescriptor;
        if (!pixelBufferDescriptor2.storage.isReadOnly()) {
            if (nReadPixels(getNativeObject(), this.mNativeEngine, i, i2, i3, i4, pixelBufferDescriptor2.storage, pixelBufferDescriptor2.storage.remaining(), pixelBufferDescriptor2.left, pixelBufferDescriptor2.top, pixelBufferDescriptor2.type.ordinal(), pixelBufferDescriptor2.alignment, pixelBufferDescriptor2.stride, pixelBufferDescriptor2.format.ordinal(), pixelBufferDescriptor2.handler, pixelBufferDescriptor2.callback) < 0) {
                throw new BufferOverflowException();
            }
            return;
        }
        throw new ReadOnlyBufferException();
    }

    public long getTimestamp() {
        return nGetTimestamp(getNativeObject());
    }

    public long getNativeObject() {
        long j = this.mNativeObject;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("Calling method on destroyed Stream");
    }

    /* access modifiers changed from: package-private */
    public void clearNativeObject() {
        this.mNativeObject = 0;
    }
}
