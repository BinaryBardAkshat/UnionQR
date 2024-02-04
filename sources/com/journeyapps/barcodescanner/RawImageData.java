package com.journeyapps.barcodescanner;

import android.graphics.Rect;

public class RawImageData {
    private byte[] data;
    private int height;
    private int width;

    public RawImageData(byte[] bArr, int i, int i2) {
        this.data = bArr;
        this.width = i;
        this.height = i2;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public RawImageData cropAndScale(Rect rect, int i) {
        int width2 = rect.width() / i;
        int height2 = rect.height() / i;
        int i2 = rect.top;
        byte[] bArr = new byte[(width2 * height2)];
        if (i == 1) {
            int i3 = (i2 * this.width) + rect.left;
            for (int i4 = 0; i4 < height2; i4++) {
                System.arraycopy(this.data, i3, bArr, i4 * width2, width2);
                i3 += this.width;
            }
        } else {
            int i5 = (i2 * this.width) + rect.left;
            for (int i6 = 0; i6 < height2; i6++) {
                int i7 = i6 * width2;
                int i8 = i5;
                for (int i9 = 0; i9 < width2; i9++) {
                    bArr[i7] = this.data[i8];
                    i8 += i;
                    i7++;
                }
                i5 += this.width * i;
            }
        }
        return new RawImageData(bArr, width2, height2);
    }

    public RawImageData rotateCameraPreview(int i) {
        if (i == 90) {
            return new RawImageData(rotateCW(this.data, this.width, this.height), this.height, this.width);
        }
        if (i != 180) {
            return i != 270 ? this : new RawImageData(rotateCCW(this.data, this.width, this.height), this.height, this.width);
        }
        return new RawImageData(rotate180(this.data, this.width, this.height), this.width, this.height);
    }

    public static byte[] rotateCW(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[(i * i2)];
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            for (int i5 = i2 - 1; i5 >= 0; i5--) {
                bArr2[i3] = bArr[(i5 * i) + i4];
                i3++;
            }
        }
        return bArr2;
    }

    public static byte[] rotate180(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[i3];
        int i4 = i3 - 1;
        for (int i5 = 0; i5 < i3; i5++) {
            bArr2[i4] = bArr[i5];
            i4--;
        }
        return bArr2;
    }

    public static byte[] rotateCCW(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[i3];
        int i4 = i3 - 1;
        for (int i5 = 0; i5 < i; i5++) {
            for (int i6 = i2 - 1; i6 >= 0; i6--) {
                bArr2[i4] = bArr[(i6 * i) + i5];
                i4--;
            }
        }
        return bArr2;
    }
}
