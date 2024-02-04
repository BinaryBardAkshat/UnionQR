package com.journeyapps.barcodescanner.camera;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import java.io.IOException;

public class CameraSurface {
    private SurfaceHolder surfaceHolder;
    private SurfaceTexture surfaceTexture;

    public CameraSurface(SurfaceHolder surfaceHolder2) {
        if (surfaceHolder2 != null) {
            this.surfaceHolder = surfaceHolder2;
            return;
        }
        throw new IllegalArgumentException("surfaceHolder may not be null");
    }

    public CameraSurface(SurfaceTexture surfaceTexture2) {
        if (surfaceTexture2 != null) {
            this.surfaceTexture = surfaceTexture2;
            return;
        }
        throw new IllegalArgumentException("surfaceTexture may not be null");
    }

    public SurfaceHolder getSurfaceHolder() {
        return this.surfaceHolder;
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.surfaceTexture;
    }

    public void setPreview(Camera camera) throws IOException {
        SurfaceHolder surfaceHolder2 = this.surfaceHolder;
        if (surfaceHolder2 != null) {
            camera.setPreviewDisplay(surfaceHolder2);
        } else {
            camera.setPreviewTexture(this.surfaceTexture);
        }
    }
}
