package com.google.android.filament;

public class Scene {
    private IndirectLight mIndirectLight;
    private long mNativeObject;
    private Skybox mSkybox;

    private static native void nAddEntities(long j, int[] iArr);

    private static native void nAddEntity(long j, int i);

    private static native int nGetLightCount(long j);

    private static native int nGetRenderableCount(long j);

    private static native void nRemove(long j, int i);

    private static native void nSetIndirectLight(long j, long j2);

    private static native void nSetSkybox(long j, long j2);

    Scene(long j) {
        this.mNativeObject = j;
    }

    public Skybox getSkybox() {
        return this.mSkybox;
    }

    public void setSkybox(Skybox skybox) {
        this.mSkybox = skybox;
        long nativeObject = getNativeObject();
        Skybox skybox2 = this.mSkybox;
        nSetSkybox(nativeObject, skybox2 != null ? skybox2.getNativeObject() : 0);
    }

    public IndirectLight getIndirectLight() {
        return this.mIndirectLight;
    }

    public void setIndirectLight(IndirectLight indirectLight) {
        this.mIndirectLight = indirectLight;
        long nativeObject = getNativeObject();
        IndirectLight indirectLight2 = this.mIndirectLight;
        nSetIndirectLight(nativeObject, indirectLight2 != null ? indirectLight2.getNativeObject() : 0);
    }

    public void addEntity(int i) {
        nAddEntity(getNativeObject(), i);
    }

    public void addEntities(int[] iArr) {
        nAddEntities(getNativeObject(), iArr);
    }

    public void removeEntity(int i) {
        nRemove(getNativeObject(), i);
    }

    public void remove(int i) {
        removeEntity(i);
    }

    public int getRenderableCount() {
        return nGetRenderableCount(getNativeObject());
    }

    public int getLightCount() {
        return nGetLightCount(getNativeObject());
    }

    public long getNativeObject() {
        long j = this.mNativeObject;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("Calling method on destroyed Scene");
    }

    /* access modifiers changed from: package-private */
    public void clearNativeObject() {
        this.mNativeObject = 0;
    }
}
