package soup.neumorphism.internal.blur;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import android.renderscript.RSRuntimeException;
import android.util.DisplayMetrics;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\tJ\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\tH\u0002J\"\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00030\u00030\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lsoup/neumorphism/internal/blur/BlurProvider;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "contextRef", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "defaultBlurRadius", "", "blur", "Landroid/graphics/Bitmap;", "source", "radius", "sampling", "factor", "Lsoup/neumorphism/internal/blur/BlurFactor;", "rs", "bitmap", "stack", "sentBitmap", "canReuseInBitmap", "", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BlurProvider.kt */
public final class BlurProvider {
    private final WeakReference<Context> contextRef;
    private final int defaultBlurRadius;

    public BlurProvider(Context context) {
        float f;
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.contextRef = new WeakReference<>(context);
        if (Build.VERSION.SDK_INT >= 24) {
            f = ((float) DisplayMetrics.DENSITY_DEVICE_STABLE) / ((float) 160);
        } else {
            Resources resources = context.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
            f = resources.getDisplayMetrics().density;
        }
        this.defaultBlurRadius = Math.min(25, MathKt.roundToInt(f * ((float) 10)));
    }

    public static /* synthetic */ Bitmap blur$default(BlurProvider blurProvider, Bitmap bitmap, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = blurProvider.defaultBlurRadius;
        }
        if ((i3 & 4) != 0) {
            i2 = 1;
        }
        return blurProvider.blur(bitmap, i, i2);
    }

    public final Bitmap blur(Bitmap bitmap, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(bitmap, "source");
        return blur(bitmap, new BlurFactor(bitmap.getWidth(), bitmap.getHeight(), i, i2, 0, 16, (DefaultConstructorMarker) null));
    }

    private final Bitmap blur(Bitmap bitmap, BlurFactor blurFactor) {
        Bitmap bitmap2;
        int width = blurFactor.getWidth() / blurFactor.getSampling();
        int height = blurFactor.getHeight() / blurFactor.getSampling();
        if (width == 0 || height == 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "Bitmap.createBitmap(widt… Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(createBitmap);
        float f = (float) 1;
        canvas.scale(f / ((float) blurFactor.getSampling()), f / ((float) blurFactor.getSampling()));
        Paint paint = new Paint();
        paint.setFlags(3);
        paint.setColorFilter(new PorterDuffColorFilter(blurFactor.getColor(), PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        try {
            bitmap2 = rs(createBitmap, blurFactor.getRadius());
        } catch (RSRuntimeException unused) {
            bitmap2 = stack(createBitmap, blurFactor.getRadius(), true);
        }
        if (bitmap2 == null) {
            return null;
        }
        if (blurFactor.getSampling() == 1) {
            return bitmap2;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap2, blurFactor.getWidth(), blurFactor.getHeight(), true);
        bitmap2.recycle();
        return createScaledBitmap;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: android.renderscript.RenderScript} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: android.renderscript.RenderScript} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.renderscript.RenderScript} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: android.renderscript.RenderScript} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.renderscript.RenderScript} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.graphics.Bitmap rs(android.graphics.Bitmap r6, int r7) throws android.renderscript.RSRuntimeException {
        /*
            r5 = this;
            java.lang.ref.WeakReference<android.content.Context> r0 = r5.contextRef
            java.lang.Object r0 = r0.get()
            android.content.Context r0 = (android.content.Context) r0
            r1 = 0
            if (r0 == 0) goto L_0x008a
            java.lang.String r2 = "contextRef.get() ?: return null"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            r2 = r1
            android.renderscript.RenderScript r2 = (android.renderscript.RenderScript) r2
            r2 = r1
            android.renderscript.Allocation r2 = (android.renderscript.Allocation) r2
            r2 = r1
            android.renderscript.ScriptIntrinsicBlur r2 = (android.renderscript.ScriptIntrinsicBlur) r2
            android.renderscript.RenderScript r0 = android.renderscript.RenderScript.create(r0)     // Catch:{ all -> 0x0071 }
            java.lang.String r2 = "rs"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)     // Catch:{ all -> 0x006b }
            android.renderscript.RenderScript$RSMessageHandler r2 = new android.renderscript.RenderScript$RSMessageHandler     // Catch:{ all -> 0x006b }
            r2.<init>()     // Catch:{ all -> 0x006b }
            r0.setMessageHandler(r2)     // Catch:{ all -> 0x006b }
            android.renderscript.Allocation$MipmapControl r2 = android.renderscript.Allocation.MipmapControl.MIPMAP_NONE     // Catch:{ all -> 0x006b }
            r3 = 1
            android.renderscript.Allocation r2 = android.renderscript.Allocation.createFromBitmap(r0, r6, r2, r3)     // Catch:{ all -> 0x006b }
            java.lang.String r3 = "input"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)     // Catch:{ all -> 0x0067 }
            android.renderscript.Type r3 = r2.getType()     // Catch:{ all -> 0x0067 }
            android.renderscript.Allocation r3 = android.renderscript.Allocation.createTyped(r0, r3)     // Catch:{ all -> 0x0067 }
            android.renderscript.Element r4 = android.renderscript.Element.U8_4(r0)     // Catch:{ all -> 0x0064 }
            android.renderscript.ScriptIntrinsicBlur r1 = android.renderscript.ScriptIntrinsicBlur.create(r0, r4)     // Catch:{ all -> 0x0064 }
            r1.setInput(r2)     // Catch:{ all -> 0x0064 }
            float r7 = (float) r7     // Catch:{ all -> 0x0064 }
            r1.setRadius(r7)     // Catch:{ all -> 0x0064 }
            r1.forEach(r3)     // Catch:{ all -> 0x0064 }
            r3.copyTo(r6)     // Catch:{ all -> 0x0064 }
            r0.destroy()
            r2.destroy()
            if (r3 == 0) goto L_0x005e
            r3.destroy()
        L_0x005e:
            if (r1 == 0) goto L_0x0063
            r1.destroy()
        L_0x0063:
            return r6
        L_0x0064:
            r6 = move-exception
            r7 = r1
            goto L_0x006f
        L_0x0067:
            r6 = move-exception
            r7 = r1
            r3 = r7
            goto L_0x006f
        L_0x006b:
            r6 = move-exception
            r7 = r1
            r2 = r7
            r3 = r2
        L_0x006f:
            r1 = r0
            goto L_0x0075
        L_0x0071:
            r6 = move-exception
            r7 = r1
            r2 = r7
            r3 = r2
        L_0x0075:
            if (r1 == 0) goto L_0x007a
            r1.destroy()
        L_0x007a:
            if (r2 == 0) goto L_0x007f
            r2.destroy()
        L_0x007f:
            if (r3 == 0) goto L_0x0084
            r3.destroy()
        L_0x0084:
            if (r7 == 0) goto L_0x0089
            r7.destroy()
        L_0x0089:
            throw r6
        L_0x008a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: soup.neumorphism.internal.blur.BlurProvider.rs(android.graphics.Bitmap, int):android.graphics.Bitmap");
    }

    private final Bitmap stack(Bitmap bitmap, int i, boolean z) {
        Bitmap bitmap2;
        int[] iArr;
        int i2 = i;
        if (i2 < 1) {
            return null;
        }
        if (z) {
            bitmap2 = bitmap;
        } else {
            bitmap2 = bitmap.copy(bitmap.getConfig(), true);
            Intrinsics.checkExpressionValueIsNotNull(bitmap2, "sentBitmap.copy(sentBitmap.config, true)");
        }
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        int i3 = width * height;
        int[] iArr2 = new int[i3];
        bitmap2.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i4 = width - 1;
        int i5 = height - 1;
        int i6 = i2 + i2 + 1;
        int[] iArr3 = new int[i3];
        int[] iArr4 = new int[i3];
        int[] iArr5 = new int[i3];
        int[] iArr6 = new int[Math.max(width, height)];
        int i7 = (i6 + 1) >> 1;
        int i8 = i7 * i7;
        int i9 = i8 * 256;
        int[] iArr7 = new int[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            iArr7[i10] = i10 / i8;
        }
        int[][] iArr8 = new int[i6][];
        for (int i11 = 0; i11 < i6; i11++) {
            iArr8[i11] = new int[3];
        }
        int[][] iArr9 = iArr8;
        int i12 = i2 + 1;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (i13 < height) {
            Bitmap bitmap3 = bitmap2;
            int i16 = height;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = -i2;
            int i26 = 0;
            while (i25 <= i2) {
                int i27 = i5;
                int[] iArr10 = iArr6;
                int i28 = iArr2[i14 + Math.min(i4, Math.max(i25, 0))];
                int[] iArr11 = iArr9[i25 + i2];
                iArr11[0] = (i28 & 16711680) >> 16;
                iArr11[1] = (i28 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr11[2] = i28 & 255;
                int abs = i12 - Math.abs(i25);
                int i29 = iArr11[0];
                i26 += i29 * abs;
                int i30 = iArr11[1];
                i17 += i30 * abs;
                int i31 = iArr11[2];
                i18 += abs * i31;
                if (i25 > 0) {
                    i22 += i29;
                    i23 += i30;
                    i24 += i31;
                } else {
                    i19 += i29;
                    i20 += i30;
                    i21 += i31;
                }
                i25++;
                i5 = i27;
                iArr6 = iArr10;
            }
            int i32 = i5;
            int[] iArr12 = iArr6;
            int i33 = i2;
            int i34 = i26;
            int i35 = 0;
            while (i35 < width) {
                iArr3[i14] = iArr7[i34];
                iArr4[i14] = iArr7[i17];
                iArr5[i14] = iArr7[i18];
                int i36 = i34 - i19;
                int i37 = i17 - i20;
                int i38 = i18 - i21;
                int[] iArr13 = iArr9[((i33 - i2) + i6) % i6];
                int i39 = i19 - iArr13[0];
                int i40 = i20 - iArr13[1];
                int i41 = i21 - iArr13[2];
                if (i13 == 0) {
                    iArr = iArr7;
                    iArr12[i35] = Math.min(i35 + i2 + 1, i4);
                } else {
                    iArr = iArr7;
                }
                int i42 = iArr2[i15 + iArr12[i35]];
                int i43 = (i42 & 16711680) >> 16;
                iArr13[0] = i43;
                int i44 = (i42 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr13[1] = i44;
                int i45 = i42 & 255;
                iArr13[2] = i45;
                int i46 = i22 + i43;
                int i47 = i23 + i44;
                int i48 = i24 + i45;
                i34 = i36 + i46;
                i17 = i37 + i47;
                i18 = i38 + i48;
                i33 = (i33 + 1) % i6;
                int[] iArr14 = iArr9[i33 % i6];
                int i49 = iArr14[0];
                i19 = i39 + i49;
                int i50 = iArr14[1];
                i20 = i40 + i50;
                int i51 = iArr14[2];
                i21 = i41 + i51;
                i22 = i46 - i49;
                i23 = i47 - i50;
                i24 = i48 - i51;
                i14++;
                i35++;
                iArr7 = iArr;
            }
            int[] iArr15 = iArr7;
            i15 += width;
            i13++;
            bitmap2 = bitmap3;
            height = i16;
            i5 = i32;
            iArr6 = iArr12;
        }
        Bitmap bitmap4 = bitmap2;
        int i52 = i5;
        int[] iArr16 = iArr6;
        int i53 = height;
        int[] iArr17 = iArr7;
        int i54 = 0;
        while (i54 < width) {
            int i55 = -i2;
            int i56 = i6;
            int[] iArr18 = iArr2;
            int i57 = 0;
            int i58 = 0;
            int i59 = 0;
            int i60 = 0;
            int i61 = 0;
            int i62 = 0;
            int i63 = 0;
            int i64 = i55;
            int i65 = i55 * width;
            int i66 = 0;
            int i67 = 0;
            while (i64 <= i2) {
                int i68 = width;
                int max = Math.max(0, i65) + i54;
                int[] iArr19 = iArr9[i64 + i2];
                iArr19[0] = iArr3[max];
                iArr19[1] = iArr4[max];
                iArr19[2] = iArr5[max];
                int abs2 = i12 - Math.abs(i64);
                i66 += iArr3[max] * abs2;
                i67 += iArr4[max] * abs2;
                i57 += iArr5[max] * abs2;
                if (i64 > 0) {
                    i61 += iArr19[0];
                    i62 += iArr19[1];
                    i63 += iArr19[2];
                } else {
                    i58 += iArr19[0];
                    i59 += iArr19[1];
                    i60 += iArr19[2];
                }
                int i69 = i52;
                if (i64 < i69) {
                    i65 += i68;
                }
                i64++;
                i52 = i69;
                width = i68;
            }
            int i70 = width;
            int i71 = i52;
            int i72 = i2;
            int i73 = i54;
            int i74 = i53;
            int i75 = 0;
            while (i75 < i74) {
                iArr18[i73] = (iArr18[i73] & ViewCompat.MEASURED_STATE_MASK) | (iArr17[i66] << 16) | (iArr17[i67] << 8) | iArr17[i57];
                int i76 = i66 - i58;
                int i77 = i67 - i59;
                int i78 = i57 - i60;
                int[] iArr20 = iArr9[((i72 - i2) + i56) % i56];
                int i79 = i58 - iArr20[0];
                int i80 = i59 - iArr20[1];
                int i81 = i60 - iArr20[2];
                if (i54 == 0) {
                    iArr16[i75] = Math.min(i75 + i12, i71) * i70;
                }
                int i82 = iArr16[i75] + i54;
                int i83 = iArr3[i82];
                iArr20[0] = i83;
                int i84 = iArr4[i82];
                iArr20[1] = i84;
                int i85 = iArr5[i82];
                iArr20[2] = i85;
                int i86 = i61 + i83;
                int i87 = i62 + i84;
                int i88 = i63 + i85;
                i66 = i76 + i86;
                i67 = i77 + i87;
                i57 = i78 + i88;
                i72 = (i72 + 1) % i56;
                int[] iArr21 = iArr9[i72];
                int i89 = iArr21[0];
                i58 = i79 + i89;
                int i90 = iArr21[1];
                i59 = i80 + i90;
                int i91 = iArr21[2];
                i60 = i81 + i91;
                i61 = i86 - i89;
                i62 = i87 - i90;
                i63 = i88 - i91;
                i73 += i70;
                i75++;
                i2 = i;
            }
            i54++;
            i2 = i;
            i52 = i71;
            i53 = i74;
            i6 = i56;
            iArr2 = iArr18;
            width = i70;
        }
        int i92 = width;
        bitmap4.setPixels(iArr2, 0, i92, 0, 0, i92, i53);
        return bitmap4;
    }
}
