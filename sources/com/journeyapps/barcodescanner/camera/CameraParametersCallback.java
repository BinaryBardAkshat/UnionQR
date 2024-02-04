package com.journeyapps.barcodescanner.camera;

import android.hardware.Camera;

public interface CameraParametersCallback {
    Camera.Parameters changeCameraParameters(Camera.Parameters parameters);
}
