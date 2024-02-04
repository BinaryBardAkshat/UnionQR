package com.journeyapps.barcodescanner;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.common.HybridBinarizer;

public class MixedDecoder extends Decoder {
    private boolean isInverted = true;

    public MixedDecoder(Reader reader) {
        super(reader);
    }

    /* access modifiers changed from: protected */
    public BinaryBitmap toBitmap(LuminanceSource luminanceSource) {
        if (this.isInverted) {
            this.isInverted = false;
            return new BinaryBitmap(new HybridBinarizer(luminanceSource.invert()));
        }
        this.isInverted = true;
        return new BinaryBitmap(new HybridBinarizer(luminanceSource));
    }
}
