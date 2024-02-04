package com.journeyapps.barcodescanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.core.view.ViewCompat;
import com.google.zxing.client.android.R;
import com.journeyapps.barcodescanner.camera.CameraInstance;
import com.journeyapps.barcodescanner.camera.CameraParametersCallback;
import com.journeyapps.barcodescanner.camera.CameraSettings;
import com.journeyapps.barcodescanner.camera.CameraSurface;
import com.journeyapps.barcodescanner.camera.CenterCropStrategy;
import com.journeyapps.barcodescanner.camera.DisplayConfiguration;
import com.journeyapps.barcodescanner.camera.FitCenterStrategy;
import com.journeyapps.barcodescanner.camera.FitXYStrategy;
import com.journeyapps.barcodescanner.camera.PreviewScalingStrategy;
import java.util.ArrayList;
import java.util.List;

public class CameraPreview extends ViewGroup {
    private static final int ROTATION_LISTENER_DELAY_MS = 250;
    /* access modifiers changed from: private */
    public static final String TAG = "CameraPreview";
    private CameraInstance cameraInstance;
    private CameraSettings cameraSettings = new CameraSettings();
    private Size containerSize;
    /* access modifiers changed from: private */
    public Size currentSurfaceSize;
    private DisplayConfiguration displayConfiguration;
    /* access modifiers changed from: private */
    public final StateListener fireState = new StateListener() {
        public void previewSized() {
            for (StateListener previewSized : CameraPreview.this.stateListeners) {
                previewSized.previewSized();
            }
        }

        public void previewStarted() {
            for (StateListener previewStarted : CameraPreview.this.stateListeners) {
                previewStarted.previewStarted();
            }
        }

        public void previewStopped() {
            for (StateListener previewStopped : CameraPreview.this.stateListeners) {
                previewStopped.previewStopped();
            }
        }

        public void cameraError(Exception exc) {
            for (StateListener cameraError : CameraPreview.this.stateListeners) {
                cameraError.cameraError(exc);
            }
        }

        public void cameraClosed() {
            for (StateListener cameraClosed : CameraPreview.this.stateListeners) {
                cameraClosed.cameraClosed();
            }
        }
    };
    private Rect framingRect = null;
    private Size framingRectSize = null;
    private double marginFraction = 0.1d;
    private int openedOrientation = -1;
    private boolean previewActive = false;
    private Rect previewFramingRect = null;
    private PreviewScalingStrategy previewScalingStrategy = null;
    private Size previewSize;
    private RotationCallback rotationCallback = new RotationCallback() {
        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onRotationChanged$0$com-journeyapps-barcodescanner-CameraPreview$4  reason: not valid java name */
        public /* synthetic */ void m24lambda$onRotationChanged$0$comjourneyappsbarcodescannerCameraPreview$4() {
            CameraPreview.this.rotationChanged();
        }

        public void onRotationChanged(int i) {
            CameraPreview.this.stateHandler.postDelayed(new CameraPreview$4$$ExternalSyntheticLambda0(this), 250);
        }
    };
    private RotationListener rotationListener;
    private final Handler.Callback stateCallback = new Handler.Callback() {
        public boolean handleMessage(Message message) {
            if (message.what == R.id.zxing_prewiew_size_ready) {
                CameraPreview.this.previewSized((Size) message.obj);
                return true;
            } else if (message.what == R.id.zxing_camera_error) {
                Exception exc = (Exception) message.obj;
                if (!CameraPreview.this.isActive()) {
                    return false;
                }
                CameraPreview.this.pause();
                CameraPreview.this.fireState.cameraError(exc);
                return false;
            } else if (message.what != R.id.zxing_camera_closed) {
                return false;
            } else {
                CameraPreview.this.fireState.cameraClosed();
                return false;
            }
        }
    };
    /* access modifiers changed from: private */
    public Handler stateHandler;
    /* access modifiers changed from: private */
    public List<StateListener> stateListeners = new ArrayList();
    private final SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            Size unused = CameraPreview.this.currentSurfaceSize = null;
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (surfaceHolder == null) {
                Log.e(CameraPreview.TAG, "*** WARNING *** surfaceChanged() gave us a null surface!");
                return;
            }
            Size unused = CameraPreview.this.currentSurfaceSize = new Size(i2, i3);
            CameraPreview.this.startPreviewIfReady();
        }
    };
    private Rect surfaceRect;
    private SurfaceView surfaceView;
    private TextureView textureView;
    private boolean torchOn = false;
    private boolean useTextureView = false;
    private WindowManager windowManager;

    public interface StateListener {
        void cameraClosed();

        void cameraError(Exception exc);

        void previewSized();

        void previewStarted();

        void previewStopped();
    }

    /* access modifiers changed from: protected */
    public void previewStarted() {
    }

    private TextureView.SurfaceTextureListener surfaceTextureListener() {
        return new TextureView.SurfaceTextureListener() {
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }

            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }

            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                onSurfaceTextureSizeChanged(surfaceTexture, i, i2);
            }

            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                Size unused = CameraPreview.this.currentSurfaceSize = new Size(i, i2);
                CameraPreview.this.startPreviewIfReady();
            }
        };
    }

    public CameraPreview(Context context) {
        super(context);
        initialize(context, (AttributeSet) null, 0, 0);
    }

    public CameraPreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(context, attributeSet, 0, 0);
    }

    public CameraPreview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context, attributeSet, i, 0);
    }

    private void initialize(Context context, AttributeSet attributeSet, int i, int i2) {
        if (getBackground() == null) {
            setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
        initializeAttributes(attributeSet);
        this.windowManager = (WindowManager) context.getSystemService("window");
        this.stateHandler = new Handler(this.stateCallback);
        this.rotationListener = new RotationListener();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setupSurfaceView();
    }

    /* access modifiers changed from: protected */
    public void initializeAttributes(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.zxing_camera_preview);
        int dimension = (int) obtainStyledAttributes.getDimension(R.styleable.zxing_camera_preview_zxing_framing_rect_width, -1.0f);
        int dimension2 = (int) obtainStyledAttributes.getDimension(R.styleable.zxing_camera_preview_zxing_framing_rect_height, -1.0f);
        if (dimension > 0 && dimension2 > 0) {
            this.framingRectSize = new Size(dimension, dimension2);
        }
        this.useTextureView = obtainStyledAttributes.getBoolean(R.styleable.zxing_camera_preview_zxing_use_texture_view, true);
        int integer = obtainStyledAttributes.getInteger(R.styleable.zxing_camera_preview_zxing_preview_scaling_strategy, -1);
        if (integer == 1) {
            this.previewScalingStrategy = new CenterCropStrategy();
        } else if (integer == 2) {
            this.previewScalingStrategy = new FitCenterStrategy();
        } else if (integer == 3) {
            this.previewScalingStrategy = new FitXYStrategy();
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: private */
    public void rotationChanged() {
        if (isActive() && getDisplayRotation() != this.openedOrientation) {
            pause();
            resume();
        }
    }

    private void setupSurfaceView() {
        if (this.useTextureView) {
            TextureView textureView2 = new TextureView(getContext());
            this.textureView = textureView2;
            textureView2.setSurfaceTextureListener(surfaceTextureListener());
            addView(this.textureView);
            return;
        }
        SurfaceView surfaceView2 = new SurfaceView(getContext());
        this.surfaceView = surfaceView2;
        surfaceView2.getHolder().addCallback(this.surfaceCallback);
        addView(this.surfaceView);
    }

    public void addStateListener(StateListener stateListener) {
        this.stateListeners.add(stateListener);
    }

    private void calculateFrames() {
        Size size;
        if (this.containerSize == null || (size = this.previewSize) == null || this.displayConfiguration == null) {
            this.previewFramingRect = null;
            this.framingRect = null;
            this.surfaceRect = null;
            throw new IllegalStateException("containerSize or previewSize is not set yet");
        }
        int i = size.width;
        int i2 = this.previewSize.height;
        int i3 = this.containerSize.width;
        int i4 = this.containerSize.height;
        Rect scalePreview = this.displayConfiguration.scalePreview(this.previewSize);
        if (scalePreview.width() > 0 && scalePreview.height() > 0) {
            this.surfaceRect = scalePreview;
            this.framingRect = calculateFramingRect(new Rect(0, 0, i3, i4), this.surfaceRect);
            Rect rect = new Rect(this.framingRect);
            rect.offset(-this.surfaceRect.left, -this.surfaceRect.top);
            Rect rect2 = new Rect((rect.left * i) / this.surfaceRect.width(), (rect.top * i2) / this.surfaceRect.height(), (rect.right * i) / this.surfaceRect.width(), (rect.bottom * i2) / this.surfaceRect.height());
            this.previewFramingRect = rect2;
            if (rect2.width() <= 0 || this.previewFramingRect.height() <= 0) {
                this.previewFramingRect = null;
                this.framingRect = null;
                Log.w(TAG, "Preview frame is too small");
                return;
            }
            this.fireState.previewSized();
        }
    }

    public void setTorch(boolean z) {
        this.torchOn = z;
        CameraInstance cameraInstance2 = this.cameraInstance;
        if (cameraInstance2 != null) {
            cameraInstance2.setTorch(z);
        }
    }

    public void changeCameraParameters(CameraParametersCallback cameraParametersCallback) {
        CameraInstance cameraInstance2 = this.cameraInstance;
        if (cameraInstance2 != null) {
            cameraInstance2.changeCameraParameters(cameraParametersCallback);
        }
    }

    private void containerSized(Size size) {
        this.containerSize = size;
        CameraInstance cameraInstance2 = this.cameraInstance;
        if (cameraInstance2 != null && cameraInstance2.getDisplayConfiguration() == null) {
            DisplayConfiguration displayConfiguration2 = new DisplayConfiguration(getDisplayRotation(), size);
            this.displayConfiguration = displayConfiguration2;
            displayConfiguration2.setPreviewScalingStrategy(getPreviewScalingStrategy());
            this.cameraInstance.setDisplayConfiguration(this.displayConfiguration);
            this.cameraInstance.configureCamera();
            boolean z = this.torchOn;
            if (z) {
                this.cameraInstance.setTorch(z);
            }
        }
    }

    public void setPreviewScalingStrategy(PreviewScalingStrategy previewScalingStrategy2) {
        this.previewScalingStrategy = previewScalingStrategy2;
    }

    public PreviewScalingStrategy getPreviewScalingStrategy() {
        PreviewScalingStrategy previewScalingStrategy2 = this.previewScalingStrategy;
        if (previewScalingStrategy2 != null) {
            return previewScalingStrategy2;
        }
        if (this.textureView != null) {
            return new CenterCropStrategy();
        }
        return new FitCenterStrategy();
    }

    /* access modifiers changed from: private */
    public void previewSized(Size size) {
        this.previewSize = size;
        if (this.containerSize != null) {
            calculateFrames();
            requestLayout();
            startPreviewIfReady();
        }
    }

    /* access modifiers changed from: protected */
    public Matrix calculateTextureTransform(Size size, Size size2) {
        float f;
        float f2 = ((float) size.width) / ((float) size.height);
        float f3 = ((float) size2.width) / ((float) size2.height);
        float f4 = 1.0f;
        if (f2 < f3) {
            f4 = f3 / f2;
            f = 1.0f;
        } else {
            f = f2 / f3;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(f4, f);
        matrix.postTranslate((((float) size.width) - (((float) size.width) * f4)) / 2.0f, (((float) size.height) - (((float) size.height) * f)) / 2.0f);
        return matrix;
    }

    /* access modifiers changed from: private */
    public void startPreviewIfReady() {
        Rect rect;
        Size size = this.currentSurfaceSize;
        if (size != null && this.previewSize != null && (rect = this.surfaceRect) != null) {
            if (this.surfaceView == null || !size.equals(new Size(rect.width(), this.surfaceRect.height()))) {
                TextureView textureView2 = this.textureView;
                if (textureView2 != null && textureView2.getSurfaceTexture() != null) {
                    if (this.previewSize != null) {
                        this.textureView.setTransform(calculateTextureTransform(new Size(this.textureView.getWidth(), this.textureView.getHeight()), this.previewSize));
                    }
                    startCameraPreview(new CameraSurface(this.textureView.getSurfaceTexture()));
                    return;
                }
                return;
            }
            startCameraPreview(new CameraSurface(this.surfaceView.getHolder()));
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        containerSized(new Size(i3 - i, i4 - i2));
        SurfaceView surfaceView2 = this.surfaceView;
        if (surfaceView2 != null) {
            Rect rect = this.surfaceRect;
            if (rect == null) {
                surfaceView2.layout(0, 0, getWidth(), getHeight());
            } else {
                surfaceView2.layout(rect.left, this.surfaceRect.top, this.surfaceRect.right, this.surfaceRect.bottom);
            }
        } else {
            TextureView textureView2 = this.textureView;
            if (textureView2 != null) {
                textureView2.layout(0, 0, getWidth(), getHeight());
            }
        }
    }

    public Rect getFramingRect() {
        return this.framingRect;
    }

    public Rect getPreviewFramingRect() {
        return this.previewFramingRect;
    }

    public Size getPreviewSize() {
        return this.previewSize;
    }

    public CameraSettings getCameraSettings() {
        return this.cameraSettings;
    }

    public void setCameraSettings(CameraSettings cameraSettings2) {
        this.cameraSettings = cameraSettings2;
    }

    public void resume() {
        Util.validateMainThread();
        Log.d(TAG, "resume()");
        initCamera();
        if (this.currentSurfaceSize != null) {
            startPreviewIfReady();
        } else {
            SurfaceView surfaceView2 = this.surfaceView;
            if (surfaceView2 != null) {
                surfaceView2.getHolder().addCallback(this.surfaceCallback);
            } else {
                TextureView textureView2 = this.textureView;
                if (textureView2 != null) {
                    if (textureView2.isAvailable()) {
                        surfaceTextureListener().onSurfaceTextureAvailable(this.textureView.getSurfaceTexture(), this.textureView.getWidth(), this.textureView.getHeight());
                    } else {
                        this.textureView.setSurfaceTextureListener(surfaceTextureListener());
                    }
                }
            }
        }
        requestLayout();
        this.rotationListener.listen(getContext(), this.rotationCallback);
    }

    public void pause() {
        TextureView textureView2;
        SurfaceView surfaceView2;
        Util.validateMainThread();
        Log.d(TAG, "pause()");
        this.openedOrientation = -1;
        CameraInstance cameraInstance2 = this.cameraInstance;
        if (cameraInstance2 != null) {
            cameraInstance2.close();
            this.cameraInstance = null;
            this.previewActive = false;
        } else {
            this.stateHandler.sendEmptyMessage(R.id.zxing_camera_closed);
        }
        if (this.currentSurfaceSize == null && (surfaceView2 = this.surfaceView) != null) {
            surfaceView2.getHolder().removeCallback(this.surfaceCallback);
        }
        if (this.currentSurfaceSize == null && (textureView2 = this.textureView) != null) {
            textureView2.setSurfaceTextureListener((TextureView.SurfaceTextureListener) null);
        }
        this.containerSize = null;
        this.previewSize = null;
        this.previewFramingRect = null;
        this.rotationListener.stop();
        this.fireState.previewStopped();
    }

    public void pauseAndWait() {
        CameraInstance cameraInstance2 = getCameraInstance();
        pause();
        long nanoTime = System.nanoTime();
        while (cameraInstance2 != null && !cameraInstance2.isCameraClosed() && System.nanoTime() - nanoTime <= 2000000000) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException unused) {
                return;
            }
        }
    }

    public Size getFramingRectSize() {
        return this.framingRectSize;
    }

    public void setFramingRectSize(Size size) {
        this.framingRectSize = size;
    }

    public double getMarginFraction() {
        return this.marginFraction;
    }

    public void setMarginFraction(double d) {
        if (d < 0.5d) {
            this.marginFraction = d;
            return;
        }
        throw new IllegalArgumentException("The margin fraction must be less than 0.5");
    }

    public boolean isUseTextureView() {
        return this.useTextureView;
    }

    public void setUseTextureView(boolean z) {
        this.useTextureView = z;
    }

    /* access modifiers changed from: protected */
    public boolean isActive() {
        return this.cameraInstance != null;
    }

    private int getDisplayRotation() {
        return this.windowManager.getDefaultDisplay().getRotation();
    }

    private void initCamera() {
        if (this.cameraInstance != null) {
            Log.w(TAG, "initCamera called twice");
            return;
        }
        CameraInstance createCameraInstance = createCameraInstance();
        this.cameraInstance = createCameraInstance;
        createCameraInstance.setReadyHandler(this.stateHandler);
        this.cameraInstance.open();
        this.openedOrientation = getDisplayRotation();
    }

    /* access modifiers changed from: protected */
    public CameraInstance createCameraInstance() {
        CameraInstance cameraInstance2 = new CameraInstance(getContext());
        cameraInstance2.setCameraSettings(this.cameraSettings);
        return cameraInstance2;
    }

    private void startCameraPreview(CameraSurface cameraSurface) {
        if (!this.previewActive && this.cameraInstance != null) {
            Log.i(TAG, "Starting preview");
            this.cameraInstance.setSurface(cameraSurface);
            this.cameraInstance.startPreview();
            this.previewActive = true;
            previewStarted();
            this.fireState.previewStarted();
        }
    }

    public CameraInstance getCameraInstance() {
        return this.cameraInstance;
    }

    public boolean isPreviewActive() {
        return this.previewActive;
    }

    /* access modifiers changed from: protected */
    public Rect calculateFramingRect(Rect rect, Rect rect2) {
        Rect rect3 = new Rect(rect);
        rect3.intersect(rect2);
        if (this.framingRectSize != null) {
            rect3.inset(Math.max(0, (rect3.width() - this.framingRectSize.width) / 2), Math.max(0, (rect3.height() - this.framingRectSize.height) / 2));
            return rect3;
        }
        int min = (int) Math.min(((double) rect3.width()) * this.marginFraction, ((double) rect3.height()) * this.marginFraction);
        rect3.inset(min, min);
        if (rect3.height() > rect3.width()) {
            rect3.inset(0, (rect3.height() - rect3.width()) / 2);
        }
        return rect3;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable("super", onSaveInstanceState);
        bundle.putBoolean("torch", this.torchOn);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("super"));
        setTorch(bundle.getBoolean("torch"));
    }

    public boolean isCameraClosed() {
        CameraInstance cameraInstance2 = this.cameraInstance;
        return cameraInstance2 == null || cameraInstance2.isCameraClosed();
    }
}
