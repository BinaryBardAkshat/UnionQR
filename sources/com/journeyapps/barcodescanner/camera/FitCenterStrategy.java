package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import android.util.Log;
import com.journeyapps.barcodescanner.Size;

public class FitCenterStrategy extends PreviewScalingStrategy {
    private static final String TAG = "FitCenterStrategy";

    /* access modifiers changed from: protected */
    public float getScore(Size size, Size size2) {
        if (size.width <= 0 || size.height <= 0) {
            return 0.0f;
        }
        Size scaleFit = size.scaleFit(size2);
        float f = (((float) scaleFit.width) * 1.0f) / ((float) size.width);
        if (f > 1.0f) {
            f = (float) Math.pow((double) (1.0f / f), 1.1d);
        }
        float f2 = ((((float) size2.width) * 1.0f) / ((float) scaleFit.width)) * ((((float) size2.height) * 1.0f) / ((float) scaleFit.height));
        return f * (((1.0f / f2) / f2) / f2);
    }

    public Rect scalePreview(Size size, Size size2) {
        Size scaleFit = size.scaleFit(size2);
        String str = TAG;
        Log.i(str, "Preview: " + size + "; Scaled: " + scaleFit + "; Want: " + size2);
        int i = (scaleFit.width - size2.width) / 2;
        int i2 = (scaleFit.height - size2.height) / 2;
        return new Rect(-i, -i2, scaleFit.width - i, scaleFit.height - i2);
    }
}
