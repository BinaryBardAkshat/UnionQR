package com.google.zxing.client.android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import com.journeyapps.barcodescanner.camera.CameraManager;
import com.journeyapps.barcodescanner.camera.CameraSettings;

public final class AmbientLightManager implements SensorEventListener {
    private static final float BRIGHT_ENOUGH_LUX = 450.0f;
    private static final float TOO_DARK_LUX = 45.0f;
    private CameraManager cameraManager;
    private CameraSettings cameraSettings;
    private Context context;
    private Handler handler = new Handler();
    private Sensor lightSensor;

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public AmbientLightManager(Context context2, CameraManager cameraManager2, CameraSettings cameraSettings2) {
        this.context = context2;
        this.cameraManager = cameraManager2;
        this.cameraSettings = cameraSettings2;
    }

    public void start() {
        if (this.cameraSettings.isAutoTorchEnabled()) {
            SensorManager sensorManager = (SensorManager) this.context.getSystemService("sensor");
            Sensor defaultSensor = sensorManager.getDefaultSensor(5);
            this.lightSensor = defaultSensor;
            if (defaultSensor != null) {
                sensorManager.registerListener(this, defaultSensor, 3);
            }
        }
    }

    public void stop() {
        if (this.lightSensor != null) {
            ((SensorManager) this.context.getSystemService("sensor")).unregisterListener(this);
            this.lightSensor = null;
        }
    }

    private void setTorch(boolean z) {
        this.handler.post(new AmbientLightManager$$ExternalSyntheticLambda0(this, z));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setTorch$0$com-google-zxing-client-android-AmbientLightManager  reason: not valid java name */
    public /* synthetic */ void m22lambda$setTorch$0$comgooglezxingclientandroidAmbientLightManager(boolean z) {
        this.cameraManager.setTorch(z);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        if (this.cameraManager == null) {
            return;
        }
        if (f <= TOO_DARK_LUX) {
            setTorch(true);
        } else if (f >= BRIGHT_ENOUGH_LUX) {
            setTorch(false);
        }
    }
}
