package com.journeyapps.barcodescanner.camera;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.zxing.client.android.R;
import com.journeyapps.barcodescanner.Size;
import com.journeyapps.barcodescanner.Util;

public class CameraInstance {
    /* access modifiers changed from: private */
    public static final String TAG = "CameraInstance";
    /* access modifiers changed from: private */
    public boolean cameraClosed = true;
    /* access modifiers changed from: private */
    public CameraManager cameraManager;
    private CameraSettings cameraSettings = new CameraSettings();
    /* access modifiers changed from: private */
    public CameraThread cameraThread;
    private Runnable closer = new Runnable() {
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Closing camera");
                CameraInstance.this.cameraManager.stopPreview();
                CameraInstance.this.cameraManager.close();
            } catch (Exception e) {
                Log.e(CameraInstance.TAG, "Failed to close camera", e);
            }
            boolean unused = CameraInstance.this.cameraClosed = true;
            CameraInstance.this.readyHandler.sendEmptyMessage(R.id.zxing_camera_closed);
            CameraInstance.this.cameraThread.decrementInstances();
        }
    };
    private Runnable configure = new Runnable() {
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Configuring camera");
                CameraInstance.this.cameraManager.configure();
                if (CameraInstance.this.readyHandler != null) {
                    CameraInstance.this.readyHandler.obtainMessage(R.id.zxing_prewiew_size_ready, CameraInstance.this.getPreviewSize()).sendToTarget();
                }
            } catch (Exception e) {
                CameraInstance.this.notifyError(e);
                Log.e(CameraInstance.TAG, "Failed to configure camera", e);
            }
        }
    };
    private DisplayConfiguration displayConfiguration;
    private Handler mainHandler;
    private boolean open = false;
    private Runnable opener = new Runnable() {
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Opening camera");
                CameraInstance.this.cameraManager.open();
            } catch (Exception e) {
                CameraInstance.this.notifyError(e);
                Log.e(CameraInstance.TAG, "Failed to open camera", e);
            }
        }
    };
    private Runnable previewStarter = new Runnable() {
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Starting preview");
                CameraInstance.this.cameraManager.setPreviewDisplay(CameraInstance.this.surface);
                CameraInstance.this.cameraManager.startPreview();
            } catch (Exception e) {
                CameraInstance.this.notifyError(e);
                Log.e(CameraInstance.TAG, "Failed to start preview", e);
            }
        }
    };
    /* access modifiers changed from: private */
    public Handler readyHandler;
    /* access modifiers changed from: private */
    public CameraSurface surface;

    public CameraInstance(Context context) {
        Util.validateMainThread();
        this.cameraThread = CameraThread.getInstance();
        CameraManager cameraManager2 = new CameraManager(context);
        this.cameraManager = cameraManager2;
        cameraManager2.setCameraSettings(this.cameraSettings);
        this.mainHandler = new Handler();
    }

    public CameraInstance(CameraManager cameraManager2) {
        Util.validateMainThread();
        this.cameraManager = cameraManager2;
    }

    public void setDisplayConfiguration(DisplayConfiguration displayConfiguration2) {
        this.displayConfiguration = displayConfiguration2;
        this.cameraManager.setDisplayConfiguration(displayConfiguration2);
    }

    public DisplayConfiguration getDisplayConfiguration() {
        return this.displayConfiguration;
    }

    public void setReadyHandler(Handler handler) {
        this.readyHandler = handler;
    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        setSurface(new CameraSurface(surfaceHolder));
    }

    public void setSurface(CameraSurface cameraSurface) {
        this.surface = cameraSurface;
    }

    public CameraSettings getCameraSettings() {
        return this.cameraSettings;
    }

    public void setCameraSettings(CameraSettings cameraSettings2) {
        if (!this.open) {
            this.cameraSettings = cameraSettings2;
            this.cameraManager.setCameraSettings(cameraSettings2);
        }
    }

    /* access modifiers changed from: private */
    public Size getPreviewSize() {
        return this.cameraManager.getPreviewSize();
    }

    public int getCameraRotation() {
        return this.cameraManager.getCameraRotation();
    }

    public void open() {
        Util.validateMainThread();
        this.open = true;
        this.cameraClosed = false;
        this.cameraThread.incrementAndEnqueue(this.opener);
    }

    public void configureCamera() {
        Util.validateMainThread();
        validateOpen();
        this.cameraThread.enqueue(this.configure);
    }

    public void startPreview() {
        Util.validateMainThread();
        validateOpen();
        this.cameraThread.enqueue(this.previewStarter);
    }

    public void setTorch(boolean z) {
        Util.validateMainThread();
        if (this.open) {
            this.cameraThread.enqueue(new CameraInstance$$ExternalSyntheticLambda1(this, z));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setTorch$0$com-journeyapps-barcodescanner-camera-CameraInstance  reason: not valid java name */
    public /* synthetic */ void m33lambda$setTorch$0$comjourneyappsbarcodescannercameraCameraInstance(boolean z) {
        this.cameraManager.setTorch(z);
    }

    public void changeCameraParameters(CameraParametersCallback cameraParametersCallback) {
        Util.validateMainThread();
        if (this.open) {
            this.cameraThread.enqueue(new CameraInstance$$ExternalSyntheticLambda0(this, cameraParametersCallback));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$changeCameraParameters$1$com-journeyapps-barcodescanner-camera-CameraInstance  reason: not valid java name */
    public /* synthetic */ void m30lambda$changeCameraParameters$1$comjourneyappsbarcodescannercameraCameraInstance(CameraParametersCallback cameraParametersCallback) {
        this.cameraManager.changeCameraParameters(cameraParametersCallback);
    }

    public void close() {
        Util.validateMainThread();
        if (this.open) {
            this.cameraThread.enqueue(this.closer);
        } else {
            this.cameraClosed = true;
        }
        this.open = false;
    }

    public boolean isOpen() {
        return this.open;
    }

    public boolean isCameraClosed() {
        return this.cameraClosed;
    }

    public void requestPreview(PreviewCallback previewCallback) {
        this.mainHandler.post(new CameraInstance$$ExternalSyntheticLambda3(this, previewCallback));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$requestPreview$3$com-journeyapps-barcodescanner-camera-CameraInstance  reason: not valid java name */
    public /* synthetic */ void m32lambda$requestPreview$3$comjourneyappsbarcodescannercameraCameraInstance(PreviewCallback previewCallback) {
        if (!this.open) {
            Log.d(TAG, "Camera is closed, not requesting preview");
        } else {
            this.cameraThread.enqueue(new CameraInstance$$ExternalSyntheticLambda2(this, previewCallback));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$requestPreview$2$com-journeyapps-barcodescanner-camera-CameraInstance  reason: not valid java name */
    public /* synthetic */ void m31lambda$requestPreview$2$comjourneyappsbarcodescannercameraCameraInstance(PreviewCallback previewCallback) {
        this.cameraManager.requestPreviewFrame(previewCallback);
    }

    private void validateOpen() {
        if (!this.open) {
            throw new IllegalStateException("CameraInstance is not open");
        }
    }

    /* access modifiers changed from: private */
    public void notifyError(Exception exc) {
        Handler handler = this.readyHandler;
        if (handler != null) {
            handler.obtainMessage(R.id.zxing_camera_error, exc).sendToTarget();
        }
    }

    /* access modifiers changed from: protected */
    public CameraManager getCameraManager() {
        return this.cameraManager;
    }

    /* access modifiers changed from: protected */
    public CameraThread getCameraThread() {
        return this.cameraThread;
    }

    /* access modifiers changed from: protected */
    public CameraSurface getSurface() {
        return this.surface;
    }
}
