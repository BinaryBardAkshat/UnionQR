package com.google.android.filament;

public class IndirectLight {
    long mNativeObject;

    /* access modifiers changed from: private */
    public static native long nBuilderBuild(long j, long j2);

    /* access modifiers changed from: private */
    public static native void nBuilderReflections(long j, long j2);

    /* access modifiers changed from: private */
    public static native long nCreateBuilder();

    /* access modifiers changed from: private */
    public static native void nDestroyBuilder(long j);

    private static native void nGetColorEstimate(long j, float[] fArr, float f, float f2, float f3);

    private static native void nGetDirectionEstimate(long j, float[] fArr);

    private static native float nGetIntensity(long j);

    private static native void nGetRotation(long j, float[] fArr);

    /* access modifiers changed from: private */
    public static native void nIntensity(long j, float f);

    /* access modifiers changed from: private */
    public static native void nIrradiance(long j, int i, float[] fArr);

    /* access modifiers changed from: private */
    public static native void nIrradianceAsTexture(long j, long j2);

    /* access modifiers changed from: private */
    public static native void nRadiance(long j, int i, float[] fArr);

    /* access modifiers changed from: private */
    public static native void nRotation(long j, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9);

    private static native void nSetIntensity(long j, float f);

    private static native void nSetRotation(long j, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9);

    IndirectLight(long j) {
        this.mNativeObject = j;
    }

    public static class Builder {
        private final BuilderFinalizer mFinalizer;
        private final long mNativeBuilder;

        public Builder() {
            long access$000 = IndirectLight.nCreateBuilder();
            this.mNativeBuilder = access$000;
            this.mFinalizer = new BuilderFinalizer(access$000);
        }

        public Builder reflections(Texture texture) {
            IndirectLight.nBuilderReflections(this.mNativeBuilder, texture.getNativeObject());
            return this;
        }

        public Builder irradiance(int i, float[] fArr) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        throw new IllegalArgumentException("bands must be 1, 2 or 3");
                    } else if (fArr.length < 27) {
                        throw new ArrayIndexOutOfBoundsException("3 bands SH, array must be at least 9 x float3");
                    }
                } else if (fArr.length < 12) {
                    throw new ArrayIndexOutOfBoundsException("2 bands SH, array must be at least 4 x float3");
                }
            } else if (fArr.length < 3) {
                throw new ArrayIndexOutOfBoundsException("1 band SH, array must be at least 1 x float3");
            }
            IndirectLight.nIrradiance(this.mNativeBuilder, i, fArr);
            return this;
        }

        public Builder radiance(int i, float[] fArr) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        throw new IllegalArgumentException("bands must be 1, 2 or 3");
                    } else if (fArr.length < 27) {
                        throw new ArrayIndexOutOfBoundsException("3 bands SH, array must be at least 9 x float3");
                    }
                } else if (fArr.length < 12) {
                    throw new ArrayIndexOutOfBoundsException("2 bands SH, array must be at least 4 x float3");
                }
            } else if (fArr.length < 3) {
                throw new ArrayIndexOutOfBoundsException("1 band SH, array must be at least 1 x float3");
            }
            IndirectLight.nRadiance(this.mNativeBuilder, i, fArr);
            return this;
        }

        public Builder irradiance(Texture texture) {
            IndirectLight.nIrradianceAsTexture(this.mNativeBuilder, texture.getNativeObject());
            return this;
        }

        public Builder intensity(float f) {
            IndirectLight.nIntensity(this.mNativeBuilder, f);
            return this;
        }

        public Builder rotation(float[] fArr) {
            IndirectLight.nRotation(this.mNativeBuilder, fArr[0], fArr[1], fArr[2], fArr[3], fArr[4], fArr[5], fArr[6], fArr[7], fArr[8]);
            return this;
        }

        public IndirectLight build(Engine engine) {
            long access$700 = IndirectLight.nBuilderBuild(this.mNativeBuilder, engine.getNativeObject());
            if (access$700 != 0) {
                return new IndirectLight(access$700);
            }
            throw new IllegalStateException("Couldn't create IndirectLight");
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
                IndirectLight.nDestroyBuilder(this.mNativeObject);
            }
        }
    }

    public void setIntensity(float f) {
        nSetIntensity(getNativeObject(), f);
    }

    public float getIntensity() {
        return nGetIntensity(getNativeObject());
    }

    public void setRotation(float[] fArr) {
        Asserts.assertMat3fIn(fArr);
        nSetRotation(getNativeObject(), fArr[0], fArr[1], fArr[2], fArr[3], fArr[4], fArr[5], fArr[6], fArr[7], fArr[8]);
    }

    public float[] getRotation(float[] fArr) {
        float[] assertMat3f = Asserts.assertMat3f(fArr);
        nGetRotation(getNativeObject(), assertMat3f);
        return assertMat3f;
    }

    public float[] getDirectionEstimate(float[] fArr) {
        float[] assertFloat3 = Asserts.assertFloat3(fArr);
        nGetDirectionEstimate(getNativeObject(), assertFloat3);
        return assertFloat3;
    }

    public float[] getColorEstimate(float[] fArr, float f, float f2, float f3) {
        float[] assertFloat4 = Asserts.assertFloat4(fArr);
        nGetColorEstimate(getNativeObject(), assertFloat4, f, f2, f3);
        return assertFloat4;
    }

    public long getNativeObject() {
        long j = this.mNativeObject;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("Calling method on destroyed IndirectLight");
    }

    /* access modifiers changed from: package-private */
    public void clearNativeObject() {
        this.mNativeObject = 0;
    }
}
