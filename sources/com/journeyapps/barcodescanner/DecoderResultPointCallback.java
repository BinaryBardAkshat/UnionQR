package com.journeyapps.barcodescanner;

import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;

public class DecoderResultPointCallback implements ResultPointCallback {
    private Decoder decoder;

    public DecoderResultPointCallback(Decoder decoder2) {
        this.decoder = decoder2;
    }

    public DecoderResultPointCallback() {
    }

    public Decoder getDecoder() {
        return this.decoder;
    }

    public void setDecoder(Decoder decoder2) {
        this.decoder = decoder2;
    }

    public void foundPossibleResultPoint(ResultPoint resultPoint) {
        Decoder decoder2 = this.decoder;
        if (decoder2 != null) {
            decoder2.foundPossibleResultPoint(resultPoint);
        }
    }
}
