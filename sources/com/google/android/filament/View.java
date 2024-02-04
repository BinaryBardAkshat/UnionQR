package com.google.android.filament;

public class View {
    private AmbientOcclusionOptions mAmbientOcclusionOptions;
    private Camera mCamera;
    private DepthPrepass mDepthPrepass = DepthPrepass.DEFAULT;
    private DynamicResolutionOptions mDynamicResolution;
    private String mName;
    private long mNativeObject;
    private RenderQuality mRenderQuality;
    private RenderTarget mRenderTarget;
    private Scene mScene;
    private Viewport mViewport = new Viewport(0, 0, 0, 0);

    public enum AmbientOcclusion {
        NONE,
        SSAO
    }

    public static class AmbientOcclusionOptions {
        public float bias = 0.005f;
        public float intensity = 1.0f;
        public float power = 0.0f;
        public float radius = 0.3f;
        public float resolution = 0.5f;
    }

    public enum AntiAliasing {
        NONE,
        FXAA
    }

    public enum Dithering {
        NONE,
        TEMPORAL
    }

    public static class DynamicResolutionOptions {
        public boolean enabled = false;
        public float headRoomRatio = 0.0f;
        public int history = 9;
        public boolean homogeneousScaling = false;
        public float maxScale = 1.0f;
        public float minScale = 0.5f;
        public float scaleRate = 0.125f;
        public float targetFrameTimeMilli = 16.666666f;
    }

    public enum QualityLevel {
        LOW,
        MEDIUM,
        HIGH,
        ULTRA
    }

    public static class RenderQuality {
        public QualityLevel hdrColorBuffer = QualityLevel.HIGH;
    }

    public enum ToneMapping {
        LINEAR,
        ACES
    }

    private static native int nGetAmbientOcclusion(long j);

    private static native int nGetAntiAliasing(long j);

    private static native void nGetClearColor(long j, float[] fArr);

    private static native int nGetDithering(long j);

    private static native int nGetSampleCount(long j);

    private static native int nGetToneMapping(long j);

    private static native boolean nIsFrontFaceWindingInverted(long j);

    private static native boolean nIsPostProcessingEnabled(long j);

    private static native void nSetAmbientOcclusion(long j, int i);

    private static native void nSetAmbientOcclusionOptions(long j, float f, float f2, float f3, float f4, float f5);

    private static native void nSetAntiAliasing(long j, int i);

    private static native void nSetCamera(long j, long j2);

    private static native void nSetClearColor(long j, float f, float f2, float f3, float f4);

    private static native void nSetClearTargets(long j, boolean z, boolean z2, boolean z3);

    private static native void nSetDepthPrepass(long j, int i);

    private static native void nSetDithering(long j, int i);

    private static native void nSetDynamicLightingOptions(long j, float f, float f2);

    private static native void nSetDynamicResolutionOptions(long j, boolean z, boolean z2, float f, float f2, float f3, float f4, float f5, int i);

    private static native void nSetFrontFaceWindingInverted(long j, boolean z);

    private static native void nSetName(long j, String str);

    private static native void nSetPostProcessingEnabled(long j, boolean z);

    private static native void nSetRenderQuality(long j, int i);

    private static native void nSetRenderTarget(long j, long j2);

    private static native void nSetSampleCount(long j, int i);

    private static native void nSetScene(long j, long j2);

    private static native void nSetShadowsEnabled(long j, boolean z);

    private static native void nSetToneMapping(long j, int i);

    private static native void nSetViewport(long j, int i, int i2, int i3, int i4);

    private static native void nSetVisibleLayers(long j, int i, int i2);

    public enum DepthPrepass {
        DEFAULT(-1),
        DISABLED(0),
        ENABLED(1);
        
        final int value;

        private DepthPrepass(int i) {
            this.value = i;
        }
    }

    View(long j) {
        this.mNativeObject = j;
    }

    public void setName(String str) {
        this.mName = str;
        nSetName(getNativeObject(), str);
    }

    public String getName() {
        return this.mName;
    }

    public void setScene(Scene scene) {
        this.mScene = scene;
        nSetScene(getNativeObject(), scene == null ? 0 : scene.getNativeObject());
    }

    public Scene getScene() {
        return this.mScene;
    }

    public void setCamera(Camera camera) {
        this.mCamera = camera;
        nSetCamera(getNativeObject(), camera == null ? 0 : camera.getNativeObject());
    }

    public Camera getCamera() {
        return this.mCamera;
    }

    public void setViewport(Viewport viewport) {
        this.mViewport = viewport;
        nSetViewport(getNativeObject(), this.mViewport.left, this.mViewport.bottom, this.mViewport.width, this.mViewport.height);
    }

    public Viewport getViewport() {
        return this.mViewport;
    }

    public void setClearColor(float f, float f2, float f3, float f4) {
        nSetClearColor(getNativeObject(), f, f2, f3, f4);
    }

    public float[] getClearColor(float[] fArr) {
        float[] assertFloat4 = Asserts.assertFloat4(fArr);
        nGetClearColor(getNativeObject(), assertFloat4);
        return assertFloat4;
    }

    public void setClearTargets(boolean z, boolean z2, boolean z3) {
        nSetClearTargets(getNativeObject(), z, z2, z3);
    }

    public void setVisibleLayers(int i, int i2) {
        nSetVisibleLayers(getNativeObject(), i & 255, i2 & 255);
    }

    public void setShadowsEnabled(boolean z) {
        nSetShadowsEnabled(getNativeObject(), z);
    }

    public void setRenderTarget(RenderTarget renderTarget) {
        this.mRenderTarget = renderTarget;
        nSetRenderTarget(getNativeObject(), renderTarget != null ? renderTarget.getNativeObject() : 0);
    }

    public RenderTarget getRenderTarget() {
        return this.mRenderTarget;
    }

    public void setSampleCount(int i) {
        nSetSampleCount(getNativeObject(), i);
    }

    public int getSampleCount() {
        return nGetSampleCount(getNativeObject());
    }

    public void setAntiAliasing(AntiAliasing antiAliasing) {
        nSetAntiAliasing(getNativeObject(), antiAliasing.ordinal());
    }

    public AntiAliasing getAntiAliasing() {
        return AntiAliasing.values()[nGetAntiAliasing(getNativeObject())];
    }

    public void setToneMapping(ToneMapping toneMapping) {
        nSetToneMapping(getNativeObject(), toneMapping.ordinal());
    }

    public ToneMapping getToneMapping() {
        return ToneMapping.values()[nGetToneMapping(getNativeObject())];
    }

    public void setDithering(Dithering dithering) {
        nSetDithering(getNativeObject(), dithering.ordinal());
    }

    public Dithering getDithering() {
        return Dithering.values()[nGetDithering(getNativeObject())];
    }

    public void setDynamicResolutionOptions(DynamicResolutionOptions dynamicResolutionOptions) {
        this.mDynamicResolution = dynamicResolutionOptions;
        nSetDynamicResolutionOptions(getNativeObject(), dynamicResolutionOptions.enabled, dynamicResolutionOptions.homogeneousScaling, dynamicResolutionOptions.targetFrameTimeMilli, dynamicResolutionOptions.headRoomRatio, dynamicResolutionOptions.scaleRate, dynamicResolutionOptions.minScale, dynamicResolutionOptions.maxScale, dynamicResolutionOptions.history);
    }

    public DynamicResolutionOptions getDynamicResolutionOptions() {
        if (this.mDynamicResolution == null) {
            this.mDynamicResolution = new DynamicResolutionOptions();
        }
        return this.mDynamicResolution;
    }

    public void setRenderQuality(RenderQuality renderQuality) {
        this.mRenderQuality = renderQuality;
        nSetRenderQuality(getNativeObject(), renderQuality.hdrColorBuffer.ordinal());
    }

    public RenderQuality getRenderQuality() {
        if (this.mRenderQuality == null) {
            this.mRenderQuality = new RenderQuality();
        }
        return this.mRenderQuality;
    }

    public DepthPrepass getDepthPrepass() {
        return this.mDepthPrepass;
    }

    public void setDepthPrepass(DepthPrepass depthPrepass) {
        this.mDepthPrepass = depthPrepass;
        nSetDepthPrepass(getNativeObject(), depthPrepass.value);
    }

    public boolean isPostProcessingEnabled() {
        return nIsPostProcessingEnabled(getNativeObject());
    }

    public void setPostProcessingEnabled(boolean z) {
        nSetPostProcessingEnabled(getNativeObject(), z);
    }

    public boolean isFrontFaceWindingInverted() {
        return nIsFrontFaceWindingInverted(getNativeObject());
    }

    public void setFrontFaceWindingInverted(boolean z) {
        nSetFrontFaceWindingInverted(getNativeObject(), z);
    }

    public void setDynamicLightingOptions(float f, float f2) {
        nSetDynamicLightingOptions(getNativeObject(), f, f2);
    }

    public void setAmbientOcclusion(AmbientOcclusion ambientOcclusion) {
        nSetAmbientOcclusion(getNativeObject(), ambientOcclusion.ordinal());
    }

    public AmbientOcclusion getAmbientOcclusion() {
        return AmbientOcclusion.values()[nGetAmbientOcclusion(getNativeObject())];
    }

    public void setAmbientOcclusionOptions(AmbientOcclusionOptions ambientOcclusionOptions) {
        this.mAmbientOcclusionOptions = ambientOcclusionOptions;
        nSetAmbientOcclusionOptions(getNativeObject(), ambientOcclusionOptions.radius, ambientOcclusionOptions.bias, ambientOcclusionOptions.power, ambientOcclusionOptions.resolution, ambientOcclusionOptions.intensity);
    }

    public AmbientOcclusionOptions getAmbientOcclusionOptions() {
        if (this.mAmbientOcclusionOptions == null) {
            this.mAmbientOcclusionOptions = new AmbientOcclusionOptions();
        }
        return this.mAmbientOcclusionOptions;
    }

    public long getNativeObject() {
        long j = this.mNativeObject;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("Calling method on destroyed View");
    }

    /* access modifiers changed from: package-private */
    public void clearNativeObject() {
        this.mNativeObject = 0;
    }
}
