package com.google.zxing.common;

import kotlin.UByte;

public final class BitSource {
    private int bitOffset;
    private int byteOffset;
    private final byte[] bytes;

    public BitSource(byte[] bArr) {
        this.bytes = bArr;
    }

    public int getBitOffset() {
        return this.bitOffset;
    }

    public int getByteOffset() {
        return this.byteOffset;
    }

    public int readBits(int i) {
        if (i <= 0 || i > 32 || i > available()) {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        int i2 = this.bitOffset;
        byte b = 0;
        if (i2 > 0) {
            int i3 = 8 - i2;
            int min = Math.min(i, i3);
            int i4 = i3 - min;
            byte[] bArr = this.bytes;
            int i5 = this.byteOffset;
            int i6 = (((255 >> (8 - min)) << i4) & bArr[i5]) >> i4;
            i -= min;
            int i7 = this.bitOffset + min;
            this.bitOffset = i7;
            if (i7 == 8) {
                this.bitOffset = 0;
                this.byteOffset = i5 + 1;
            }
            b = i6;
        }
        if (i <= 0) {
            return b;
        }
        while (i >= 8) {
            int i8 = b << 8;
            byte[] bArr2 = this.bytes;
            int i9 = this.byteOffset;
            b = (bArr2[i9] & UByte.MAX_VALUE) | i8;
            this.byteOffset = i9 + 1;
            i -= 8;
        }
        if (i <= 0) {
            return b;
        }
        int i10 = 8 - i;
        int i11 = (b << i) | ((((255 >> i10) << i10) & this.bytes[this.byteOffset]) >> i10);
        this.bitOffset += i;
        return i11;
    }

    public int available() {
        return ((this.bytes.length - this.byteOffset) * 8) - this.bitOffset;
    }
}
