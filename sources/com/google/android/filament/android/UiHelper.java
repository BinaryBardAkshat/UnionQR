package com.google.android.filament.android;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;

public class UiHelper {
    private static final boolean LOGGING = false;
    private static final String LOG_TAG = "UiHelper";
    /* access modifiers changed from: private */
    public int mDesiredHeight;
    /* access modifiers changed from: private */
    public int mDesiredWidth;
    private boolean mHasSwapChain;
    private Object mNativeWindow;
    private boolean mOpaque;
    /* access modifiers changed from: private */
    public RendererCallback mRenderCallback;
    /* access modifiers changed from: private */
    public RenderSurface mRenderSurface;

    public enum ContextErrorPolicy {
        CHECK,
        DONT_CHECK
    }

    private interface RenderSurface {
        void detach();

        void resize(int i, int i2);
    }

    public interface RendererCallback {
        void onDetachedFromSurface();

        void onNativeWindowChanged(Surface surface);

        void onResized(int i, int i2);
    }

    private class SurfaceViewHandler implements RenderSurface {
        private SurfaceView mSurfaceView;

        public void detach() {
        }

        SurfaceViewHandler(SurfaceView surfaceView) {
            this.mSurfaceView = surfaceView;
        }

        public void resize(int i, int i2) {
            this.mSurfaceView.getHolder().setFixedSize(i, i2);
        }
    }

    private class TextureViewHandler implements RenderSurface {
        private Surface mSurface;
        private TextureView mTextureView;

        TextureViewHandler(TextureView textureView) {
            this.mTextureView = textureView;
        }

        public void resize(int i, int i2) {
            this.mTextureView.getSurfaceTexture().setDefaultBufferSize(i, i2);
            UiHelper.this.mRenderCallback.onResized(i, i2);
        }

        public void detach() {
            setSurface((Surface) null);
        }

        /* access modifiers changed from: package-private */
        public void setSurface(Surface surface) {
            Surface surface2;
            if (surface == null && (surface2 = this.mSurface) != null) {
                surface2.release();
            }
            this.mSurface = surface;
        }
    }

    public UiHelper() {
        this(ContextErrorPolicy.CHECK);
    }

    public UiHelper(ContextErrorPolicy contextErrorPolicy) {
        this.mOpaque = true;
    }

    public void setRenderCallback(RendererCallback rendererCallback) {
        this.mRenderCallback = rendererCallback;
    }

    public RendererCallback getRenderCallback() {
        return this.mRenderCallback;
    }

    public void detach() {
        destroySwapChain();
        this.mNativeWindow = null;
        this.mRenderSurface = null;
    }

    public boolean isReadyToRender() {
        return this.mHasSwapChain;
    }

    public void setDesiredSize(int i, int i2) {
        this.mDesiredWidth = i;
        this.mDesiredHeight = i2;
        RenderSurface renderSurface = this.mRenderSurface;
        if (renderSurface != null) {
            renderSurface.resize(i, i2);
        }
    }

    public int getDesiredWidth() {
        return this.mDesiredWidth;
    }

    public int getDesiredHeight() {
        return this.mDesiredHeight;
    }

    public boolean isOpaque() {
        return this.mOpaque;
    }

    public void setOpaque(boolean z) {
        this.mOpaque = z;
    }

    public long getSwapChainFlags() {
        return isOpaque() ? 0 : 1;
    }

    public void attachTo(SurfaceView surfaceView) {
        if (attach(surfaceView)) {
            int i = -3;
            if (!isOpaque()) {
                surfaceView.setZOrderOnTop(true);
                surfaceView.getHolder().setFormat(-3);
            }
            this.mRenderSurface = new SurfaceViewHandler(surfaceView);
            AnonymousClass1 r0 = new SurfaceHolder.Callback() {
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    UiHelper.this.createSwapChain(surfaceHolder.getSurface());
                }

                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                    UiHelper.this.mRenderCallback.onResized(i2, i3);
                }

                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    UiHelper.this.destroySwapChain();
                }
            };
            SurfaceHolder holder = surfaceView.getHolder();
            holder.addCallback(r0);
            holder.setFixedSize(this.mDesiredWidth, this.mDesiredHeight);
            Surface surface = holder.getSurface();
            if (surface != null && surface.isValid()) {
                r0.surfaceCreated(holder);
                if (isOpaque()) {
                    i = -1;
                }
                r0.surfaceChanged(holder, i, holder.getSurfaceFrame().width(), holder.getSurfaceFrame().height());
            }
        }
    }

    public void attachTo(TextureView textureView) {
        if (attach(textureView)) {
            textureView.setOpaque(isOpaque());
            this.mRenderSurface = new TextureViewHandler(textureView);
            AnonymousClass2 r0 = new TextureView.SurfaceTextureListener() {
                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                }

                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                    if (UiHelper.this.mDesiredWidth > 0 && UiHelper.this.mDesiredHeight > 0) {
                        surfaceTexture.setDefaultBufferSize(UiHelper.this.mDesiredWidth, UiHelper.this.mDesiredHeight);
                    }
                    Surface surface = new Surface(surfaceTexture);
                    ((TextureViewHandler) UiHelper.this.mRenderSurface).setSurface(surface);
                    UiHelper.this.createSwapChain(surface);
                    UiHelper.this.mRenderCallback.onResized(i, i2);
                }

                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                    UiHelper.this.mRenderCallback.onResized(i, i2);
                }

                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    UiHelper.this.destroySwapChain();
                    return true;
                }
            };
            textureView.setSurfaceTextureListener(r0);
            if (textureView.isAvailable()) {
                r0.onSurfaceTextureAvailable(textureView.getSurfaceTexture(), this.mDesiredWidth, this.mDesiredHeight);
            }
        }
    }

    private boolean attach(Object obj) {
        Object obj2 = this.mNativeWindow;
        if (obj2 != null) {
            if (obj2 == obj) {
                return false;
            }
            destroySwapChain();
        }
        this.mNativeWindow = obj;
        return true;
    }

    /* access modifiers changed from: private */
    public void createSwapChain(Surface surface) {
        this.mRenderCallback.onNativeWindowChanged(surface);
        this.mHasSwapChain = true;
    }

    /* access modifiers changed from: private */
    public void destroySwapChain() {
        RenderSurface renderSurface = this.mRenderSurface;
        if (renderSurface != null) {
            renderSurface.detach();
        }
        this.mRenderCallback.onDetachedFromSurface();
        this.mHasSwapChain = false;
    }
}
