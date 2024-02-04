package com.google.android.filament;

import java.nio.Buffer;
import java.nio.BufferOverflowException;

public class VertexBuffer {
    private long mNativeObject;

    public enum AttributeType {
        BYTE,
        BYTE2,
        BYTE3,
        BYTE4,
        UBYTE,
        UBYTE2,
        UBYTE3,
        UBYTE4,
        SHORT,
        SHORT2,
        SHORT3,
        SHORT4,
        USHORT,
        USHORT2,
        USHORT3,
        USHORT4,
        INT,
        UINT,
        FLOAT,
        FLOAT2,
        FLOAT3,
        FLOAT4,
        HALF,
        HALF2,
        HALF3,
        HALF4
    }

    public static class QuatTangentContext {
        public Buffer normals;
        public int normalsStride;
        public Buffer outBuffer;
        public int outStride;
        public int quatCount;
        public QuatType quatType;
        public Buffer tangents;
        public int tangentsStride;
    }

    public enum QuatType {
        HALF4,
        SHORT4,
        FLOAT4
    }

    public enum VertexAttribute {
        POSITION,
        TANGENTS,
        COLOR,
        UV0,
        UV1,
        BONE_INDICES,
        BONE_WEIGHTS,
        UNUSED,
        CUSTOM0,
        CUSTOM1,
        CUSTOM2,
        CUSTOM3,
        CUSTOM4,
        CUSTOM5,
        CUSTOM6,
        CUSTOM7
    }

    /* access modifiers changed from: private */
    public static native void nBuilderAttribute(long j, int i, int i2, int i3, int i4, int i5);

    /* access modifiers changed from: private */
    public static native void nBuilderBufferCount(long j, int i);

    /* access modifiers changed from: private */
    public static native long nBuilderBuild(long j, long j2);

    /* access modifiers changed from: private */
    public static native void nBuilderNormalized(long j, int i, boolean z);

    /* access modifiers changed from: private */
    public static native void nBuilderVertexCount(long j, int i);

    /* access modifiers changed from: private */
    public static native long nCreateBuilder();

    /* access modifiers changed from: private */
    public static native void nDestroyBuilder(long j);

    private static native int nGetVertexCount(long j);

    private static native void nPopulateTangentQuaternions(int i, int i2, Buffer buffer, int i3, int i4, Buffer buffer2, int i5, int i6, Buffer buffer3, int i7, int i8);

    private static native int nSetBufferAt(long j, long j2, int i, Buffer buffer, int i2, int i3, int i4, Object obj, Runnable runnable);

    private VertexBuffer(long j) {
        this.mNativeObject = j;
    }

    public static class Builder {
        private final BuilderFinalizer mFinalizer;
        private final long mNativeBuilder;

        public Builder() {
            long access$000 = VertexBuffer.nCreateBuilder();
            this.mNativeBuilder = access$000;
            this.mFinalizer = new BuilderFinalizer(access$000);
        }

        public Builder vertexCount(int i) {
            VertexBuffer.nBuilderVertexCount(this.mNativeBuilder, i);
            return this;
        }

        public Builder bufferCount(int i) {
            VertexBuffer.nBuilderBufferCount(this.mNativeBuilder, i);
            return this;
        }

        public Builder attribute(VertexAttribute vertexAttribute, int i, AttributeType attributeType, int i2, int i3) {
            VertexBuffer.nBuilderAttribute(this.mNativeBuilder, vertexAttribute.ordinal(), i, attributeType.ordinal(), i2, i3);
            return this;
        }

        public Builder attribute(VertexAttribute vertexAttribute, int i, AttributeType attributeType) {
            return attribute(vertexAttribute, i, attributeType, 0, 0);
        }

        public Builder normalized(VertexAttribute vertexAttribute) {
            VertexBuffer.nBuilderNormalized(this.mNativeBuilder, vertexAttribute.ordinal(), true);
            return this;
        }

        public Builder normalized(VertexAttribute vertexAttribute, boolean z) {
            VertexBuffer.nBuilderNormalized(this.mNativeBuilder, vertexAttribute.ordinal(), z);
            return this;
        }

        public VertexBuffer build(Engine engine) {
            long access$500 = VertexBuffer.nBuilderBuild(this.mNativeBuilder, engine.getNativeObject());
            if (access$500 != 0) {
                return new VertexBuffer(access$500);
            }
            throw new IllegalStateException("Couldn't create VertexBuffer");
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
                VertexBuffer.nDestroyBuilder(this.mNativeObject);
            }
        }
    }

    public int getVertexCount() {
        return nGetVertexCount(getNativeObject());
    }

    public void setBufferAt(Engine engine, int i, Buffer buffer) {
        setBufferAt(engine, i, buffer, 0, 0, (Object) null, (Runnable) null);
    }

    public void setBufferAt(Engine engine, int i, Buffer buffer, int i2, int i3) {
        setBufferAt(engine, i, buffer, i2, i3, (Object) null, (Runnable) null);
    }

    public void setBufferAt(Engine engine, int i, Buffer buffer, int i2, int i3, Object obj, Runnable runnable) {
        if (nSetBufferAt(getNativeObject(), engine.getNativeObject(), i, buffer, buffer.remaining(), i2, i3 == 0 ? buffer.remaining() : i3, obj, runnable) < 0) {
            throw new BufferOverflowException();
        }
    }

    public static void populateTangentQuaternions(QuatTangentContext quatTangentContext) {
        nPopulateTangentQuaternions(quatTangentContext.quatType.ordinal(), quatTangentContext.quatCount, quatTangentContext.outBuffer, quatTangentContext.outBuffer.remaining(), quatTangentContext.outStride, quatTangentContext.normals, quatTangentContext.normals.remaining(), quatTangentContext.normalsStride, quatTangentContext.tangents, quatTangentContext.tangents != null ? quatTangentContext.tangents.remaining() : 0, quatTangentContext.tangentsStride);
    }

    public long getNativeObject() {
        long j = this.mNativeObject;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("Calling method on destroyed VertexBuffer");
    }

    /* access modifiers changed from: package-private */
    public void clearNativeObject() {
        this.mNativeObject = 0;
    }
}
