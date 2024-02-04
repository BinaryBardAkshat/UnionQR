package com.google.android.filament;

public final class MathUtils {
    private static native void nPackTangentFrame(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float[] fArr, int i);

    private MathUtils() {
    }

    public static void packTangentFrame(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float[] fArr) {
        nPackTangentFrame(f, f2, f3, f4, f5, f6, f7, f8, f9, fArr, 0);
    }

    public static void packTangentFrame(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float[] fArr, int i) {
        nPackTangentFrame(f, f2, f3, f4, f5, f6, f7, f8, f9, fArr, i);
    }
}
