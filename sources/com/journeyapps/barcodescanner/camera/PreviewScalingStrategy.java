package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import android.util.Log;
import com.journeyapps.barcodescanner.Size;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class PreviewScalingStrategy {
    private static final String TAG = "PreviewScalingStrategy";

    /* access modifiers changed from: protected */
    public float getScore(Size size, Size size2) {
        return 0.5f;
    }

    public abstract Rect scalePreview(Size size, Size size2);

    public Size getBestPreviewSize(List<Size> list, Size size) {
        List<Size> bestPreviewOrder = getBestPreviewOrder(list, size);
        String str = TAG;
        Log.i(str, "Viewfinder size: " + size);
        Log.i(str, "Preview in order of preference: " + bestPreviewOrder);
        return bestPreviewOrder.get(0);
    }

    public List<Size> getBestPreviewOrder(List<Size> list, final Size size) {
        if (size == null) {
            return list;
        }
        Collections.sort(list, new Comparator<Size>() {
            public int compare(Size size, Size size2) {
                return Float.compare(PreviewScalingStrategy.this.getScore(size2, size), PreviewScalingStrategy.this.getScore(size, size));
            }
        });
        return list;
    }
}
