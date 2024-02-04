package com.google.android.filament;

import java.nio.Buffer;
import java.nio.BufferOverflowException;

public class IndexBuffer {
    private long mNativeObject;

    /* access modifiers changed from: private */
    public static native void nBuilderBufferType(long j, int i);

    /* access modifiers changed from: private */
    public static native long nBuilderBuild(long j, long j2);

    /* access modifiers changed from: private */
    public static native void nBuilderIndexCount(long j, int i);

    /* access modifiers changed from: private */
    public static native long nCreateBuilder();

    /* access modifiers changed from: private */
    public static native void nDestroyBuilder(long j);

    private static native int nGetIndexCount(long j);

    private static native int nSetBuffer(long j, long j2, Buffer buffer, int i, int i2, int i3, Object obj, Runnable runnable);

    private IndexBuffer(long j) {
        this.mNativeObject = j;
    }

    public static class Builder {
        private final BuilderFinalizer mFinalizer;
        private final long mNativeBuilder;

        public enum IndexType {
            USHORT,
            UINT
        }

        public Builder() {
            long access$000 = IndexBuffer.nCreateBuilder();
            this.mNativeBuilder = access$000;
            this.mFinalizer = new BuilderFinalizer(access$000);
        }

        public Builder indexCount(int i) {
            IndexBuffer.nBuilderIndexCount(this.mNativeBuilder, i);
            return this;
        }

        public Builder bufferType(IndexType indexType) {
            IndexBuffer.nBuilderBufferType(this.mNativeBuilder, indexType.ordinal());
            return this;
        }

        public IndexBuffer build(Engine engine) {
            long access$300 = IndexBuffer.nBuilderBuild(this.mNativeBuilder, engine.getNativeObject());
            if (access$300 != 0) {
                return new IndexBuffer(access$300);
            }
            throw new IllegalStateException("Couldn't create IndexBuffer");
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
                IndexBuffer.nDestroyBuilder(this.mNativeObject);
            }
        }
    }

    public int getIndexCount() {
        return nGetIndexCount(getNativeObject());
    }

    public void setBuffer(Engine engine, Buffer buffer) {
        setBuffer(engine, buffer, 0, 0, (Object) null, (Runnable) null);
    }

    public void setBuffer(Engine engine, Buffer buffer, int i, int i2) {
        setBuffer(engine, buffer, i, i2, (Object) null, (Runnable) null);
    }

    public void setBuffer(Engine engine, Buffer buffer, int i, int i2, Object obj, Runnable runnable) {
        if (nSetBuffer(getNativeObject(), engine.getNativeObject(), buffer, buffer.remaining(), i, i2 == 0 ? buffer.remaining() : i2, obj, runnable) < 0) {
            throw new BufferOverflowException();
        }
    }

    public long getNativeObject() {
        long j = this.mNativeObject;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("Calling method on destroyed IndexBuffer");
    }

    /* access modifiers changed from: package-private */
    public void clearNativeObject() {
        this.mNativeObject = 0;
    }
}
