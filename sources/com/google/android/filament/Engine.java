package com.google.android.filament;

public class Engine {
    private final LightManager mLightManager;
    private long mNativeObject;
    private final RenderableManager mRenderableManager;
    private final TransformManager mTransformManager;

    public enum Backend {
        DEFAULT,
        OPENGL,
        VULKAN,
        NOOP
    }

    private static native long nCreateCamera(long j);

    private static native long nCreateCameraWithEntity(long j, int i);

    private static native long nCreateEngine(long j, long j2);

    private static native long nCreateFence(long j);

    private static native long nCreateRenderer(long j);

    private static native long nCreateScene(long j);

    private static native long nCreateSwapChain(long j, Object obj, long j2);

    private static native long nCreateSwapChainFromRawPointer(long j, long j2, long j3);

    private static native long nCreateSwapChainHeadless(long j, int i, int i2, long j2);

    private static native long nCreateView(long j);

    private static native void nDestroyCamera(long j, long j2);

    private static native void nDestroyEngine(long j);

    private static native void nDestroyEntity(long j, int i);

    private static native void nDestroyFence(long j, long j2);

    private static native void nDestroyIndexBuffer(long j, long j2);

    private static native void nDestroyIndirectLight(long j, long j2);

    private static native void nDestroyMaterial(long j, long j2);

    private static native void nDestroyMaterialInstance(long j, long j2);

    private static native void nDestroyRenderTarget(long j, long j2);

    private static native void nDestroyRenderer(long j, long j2);

    private static native void nDestroyScene(long j, long j2);

    private static native void nDestroySkybox(long j, long j2);

    private static native void nDestroyStream(long j, long j2);

    private static native void nDestroySwapChain(long j, long j2);

    private static native void nDestroyTexture(long j, long j2);

    private static native void nDestroyVertexBuffer(long j, long j2);

    private static native void nDestroyView(long j, long j2);

    private static native void nFlushAndWait(long j);

    private static native long nGetBackend(long j);

    private static native long nGetLightManager(long j);

    private static native long nGetRenderableManager(long j);

    private static native long nGetTransformManager(long j);

    private Engine(long j) {
        this.mNativeObject = j;
        this.mTransformManager = new TransformManager(nGetTransformManager(j));
        this.mLightManager = new LightManager(nGetLightManager(j));
        this.mRenderableManager = new RenderableManager(nGetRenderableManager(j));
    }

    public static Engine create() {
        long nCreateEngine = nCreateEngine(0, 0);
        if (nCreateEngine != 0) {
            return new Engine(nCreateEngine);
        }
        throw new IllegalStateException("Couldn't create Engine");
    }

    public static Engine create(Backend backend) {
        long nCreateEngine = nCreateEngine((long) backend.ordinal(), 0);
        if (nCreateEngine != 0) {
            return new Engine(nCreateEngine);
        }
        throw new IllegalStateException("Couldn't create Engine");
    }

    public static Engine create(Object obj) {
        if (Platform.get().validateSharedContext(obj)) {
            long nCreateEngine = nCreateEngine(0, Platform.get().getSharedContextNativeHandle(obj));
            if (nCreateEngine != 0) {
                return new Engine(nCreateEngine);
            }
            throw new IllegalStateException("Couldn't create Engine");
        }
        throw new IllegalArgumentException("Invalid shared context " + obj);
    }

    public boolean isValid() {
        return this.mNativeObject != 0;
    }

    public void destroy() {
        nDestroyEngine(getNativeObject());
        clearNativeObject();
    }

    public Backend getBackend() {
        return Backend.values()[(int) nGetBackend(getNativeObject())];
    }

    public SwapChain createSwapChain(Object obj) {
        return createSwapChain(obj, 0);
    }

    public SwapChain createSwapChain(Object obj, long j) {
        if (Platform.get().validateSurface(obj)) {
            long nCreateSwapChain = nCreateSwapChain(getNativeObject(), obj, j);
            if (nCreateSwapChain != 0) {
                return new SwapChain(nCreateSwapChain, obj);
            }
            throw new IllegalStateException("Couldn't create SwapChain");
        }
        throw new IllegalArgumentException("Invalid surface " + obj);
    }

    public SwapChain createSwapChain(int i, int i2, long j) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        long nCreateSwapChainHeadless = nCreateSwapChainHeadless(getNativeObject(), i, i2, j);
        if (nCreateSwapChainHeadless != 0) {
            return new SwapChain(nCreateSwapChainHeadless, (Object) null);
        }
        throw new IllegalStateException("Couldn't create SwapChain");
    }

    public SwapChain createSwapChainFromNativeSurface(NativeSurface nativeSurface, long j) {
        long nCreateSwapChainFromRawPointer = nCreateSwapChainFromRawPointer(getNativeObject(), nativeSurface.getNativeObject(), j);
        if (nCreateSwapChainFromRawPointer != 0) {
            return new SwapChain(nCreateSwapChainFromRawPointer, nativeSurface);
        }
        throw new IllegalStateException("Couldn't create SwapChain");
    }

    public void destroySwapChain(SwapChain swapChain) {
        nDestroySwapChain(getNativeObject(), swapChain.getNativeObject());
        swapChain.clearNativeObject();
    }

    public View createView() {
        long nCreateView = nCreateView(getNativeObject());
        if (nCreateView != 0) {
            return new View(nCreateView);
        }
        throw new IllegalStateException("Couldn't create View");
    }

    public void destroyView(View view) {
        nDestroyView(getNativeObject(), view.getNativeObject());
        view.clearNativeObject();
    }

    public Renderer createRenderer() {
        long nCreateRenderer = nCreateRenderer(getNativeObject());
        if (nCreateRenderer != 0) {
            return new Renderer(this, nCreateRenderer);
        }
        throw new IllegalStateException("Couldn't create Renderer");
    }

    public void destroyRenderer(Renderer renderer) {
        nDestroyRenderer(getNativeObject(), renderer.getNativeObject());
        renderer.clearNativeObject();
    }

    public Camera createCamera() {
        long nCreateCamera = nCreateCamera(getNativeObject());
        if (nCreateCamera != 0) {
            return new Camera(nCreateCamera);
        }
        throw new IllegalStateException("Couldn't create Camera");
    }

    public Camera createCamera(int i) {
        long nCreateCameraWithEntity = nCreateCameraWithEntity(getNativeObject(), i);
        if (nCreateCameraWithEntity != 0) {
            return new Camera(nCreateCameraWithEntity);
        }
        throw new IllegalStateException("Couldn't create Camera");
    }

    public void destroyCamera(Camera camera) {
        nDestroyCamera(getNativeObject(), camera.getNativeObject());
        camera.clearNativeObject();
    }

    public Scene createScene() {
        long nCreateScene = nCreateScene(getNativeObject());
        if (nCreateScene != 0) {
            return new Scene(nCreateScene);
        }
        throw new IllegalStateException("Couldn't create Scene");
    }

    public void destroyScene(Scene scene) {
        nDestroyScene(getNativeObject(), scene.getNativeObject());
        scene.clearNativeObject();
    }

    public void destroyStream(Stream stream) {
        nDestroyStream(getNativeObject(), stream.getNativeObject());
        stream.clearNativeObject();
    }

    public Fence createFence() {
        long nCreateFence = nCreateFence(getNativeObject());
        if (nCreateFence != 0) {
            return new Fence(nCreateFence);
        }
        throw new IllegalStateException("Couldn't create Fence");
    }

    public void destroyFence(Fence fence) {
        nDestroyFence(getNativeObject(), fence.getNativeObject());
        fence.clearNativeObject();
    }

    public void destroyIndexBuffer(IndexBuffer indexBuffer) {
        nDestroyIndexBuffer(getNativeObject(), indexBuffer.getNativeObject());
        indexBuffer.clearNativeObject();
    }

    public void destroyVertexBuffer(VertexBuffer vertexBuffer) {
        nDestroyVertexBuffer(getNativeObject(), vertexBuffer.getNativeObject());
        vertexBuffer.clearNativeObject();
    }

    public void destroyIndirectLight(IndirectLight indirectLight) {
        nDestroyIndirectLight(getNativeObject(), indirectLight.getNativeObject());
        indirectLight.clearNativeObject();
    }

    public void destroyMaterial(Material material) {
        nDestroyMaterial(getNativeObject(), material.getNativeObject());
        material.clearNativeObject();
    }

    public void destroyMaterialInstance(MaterialInstance materialInstance) {
        nDestroyMaterialInstance(getNativeObject(), materialInstance.getNativeObject());
        materialInstance.clearNativeObject();
    }

    public void destroySkybox(Skybox skybox) {
        nDestroySkybox(getNativeObject(), skybox.getNativeObject());
        skybox.clearNativeObject();
    }

    public void destroyTexture(Texture texture) {
        nDestroyTexture(getNativeObject(), texture.getNativeObject());
        texture.clearNativeObject();
    }

    public void destroyRenderTarget(RenderTarget renderTarget) {
        nDestroyRenderTarget(getNativeObject(), renderTarget.getNativeObject());
        renderTarget.clearNativeObject();
    }

    public void destroyEntity(int i) {
        nDestroyEntity(getNativeObject(), i);
    }

    public TransformManager getTransformManager() {
        return this.mTransformManager;
    }

    public LightManager getLightManager() {
        return this.mLightManager;
    }

    public RenderableManager getRenderableManager() {
        return this.mRenderableManager;
    }

    public void flushAndWait() {
        nFlushAndWait(getNativeObject());
    }

    public long getNativeObject() {
        long j = this.mNativeObject;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("Calling method on destroyed Engine");
    }

    private void clearNativeObject() {
        this.mNativeObject = 0;
    }
}
