package com.google.android.filament;

public class Skybox {
    private long mNativeObject;

    /* access modifiers changed from: private */
    public static native long nBuilderBuild(long j, long j2);

    /* access modifiers changed from: private */
    public static native void nBuilderEnvironment(long j, long j2);

    /* access modifiers changed from: private */
    public static native void nBuilderIntensity(long j, float f);

    /* access modifiers changed from: private */
    public static native void nBuilderShowSun(long j, boolean z);

    /* access modifiers changed from: private */
    public static native long nCreateBuilder();

    /* access modifiers changed from: private */
    public static native void nDestroyBuilder(long j);

    private static native float nGetIntensity(long j);

    private static native int nGetLayerMask(long j);

    private static native void nSetLayerMask(long j, int i, int i2);

    Skybox(long j) {
        this.mNativeObject = j;
    }

    public static class Builder {
        private final BuilderFinalizer mFinalizer;
        private final long mNativeBuilder;

        public Builder() {
            long access$000 = Skybox.nCreateBuilder();
            this.mNativeBuilder = access$000;
            this.mFinalizer = new BuilderFinalizer(access$000);
        }

        public Builder environment(Texture texture) {
            Skybox.nBuilderEnvironment(this.mNativeBuilder, texture.getNativeObject());
            return this;
        }

        public Builder showSun(boolean z) {
            Skybox.nBuilderShowSun(this.mNativeBuilder, z);
            return this;
        }

        public Builder intensity(float f) {
            Skybox.nBuilderIntensity(this.mNativeBuilder, f);
            return this;
        }

        public Skybox build(Engine engine) {
            long access$400 = Skybox.nBuilderBuild(this.mNativeBuilder, engine.getNativeObject());
            if (access$400 != 0) {
                return new Skybox(access$400);
            }
            throw new IllegalStateException("Couldn't create Skybox");
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
                Skybox.nDestroyBuilder(this.mNativeObject);
            }
        }
    }

    public void setLayerMask(int i, int i2) {
        nSetLayerMask(getNativeObject(), i & 255, i2 & 255);
    }

    public int getLayerMask() {
        return nGetLayerMask(getNativeObject());
    }

    public float getIntensity() {
        return nGetIntensity(getNativeObject());
    }

    public long getNativeObject() {
        long j = this.mNativeObject;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("Calling method on destroyed Skybox");
    }

    /* access modifiers changed from: package-private */
    public void clearNativeObject() {
        this.mNativeObject = 0;
    }
}
