package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import android.util.Log;
import com.journeyapps.barcodescanner.Size;

public class CenterCropStrategy extends PreviewScalingStrategy {
    private static final String TAG = "CenterCropStrategy";

    /* access modifiers changed from: protected */
    public float getScore(Size size, Size size2) {
        if (size.width <= 0 || size.height <= 0) {
            return 0.0f;
        }
        Size scaleCrop = size.scaleCrop(size2);
        float f = (((float) scaleCrop.width) * 1.0f) / ((float) size.width);
        if (f > 1.0f) {
            f = (float) Math.pow((double) (1.0f / f), 1.1d);
        }
        float f2 = ((((float) scaleCrop.width) * 1.0f) / ((float) size2.width)) + ((((float) scaleCrop.height) * 1.0f) / ((float) size2.height));
        return f * ((1.0f / f2) / f2);
    }

    public Rect scalePreview(Size size, Size size2) {
        Size scaleCrop = size.scaleCrop(size2);
        String str = TAG;
        Log.i(str, "Preview: " + size + "; Scaled: " + scaleCrop + "; Want: " + size2);
        int i = (scaleCrop.width - size2.width) / 2;
        int i2 = (scaleCrop.height - size2.height) / 2;
        return new Rect(-i, -i2, scaleCrop.width - i, scaleCrop.height - i2);
    }
}
