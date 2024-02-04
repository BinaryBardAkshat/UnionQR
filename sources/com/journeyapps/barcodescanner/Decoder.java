package com.journeyapps.barcodescanner;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.HybridBinarizer;
import java.util.ArrayList;
import java.util.List;

public class Decoder implements ResultPointCallback {
    private List<ResultPoint> possibleResultPoints = new ArrayList();
    private Reader reader;

    public Decoder(Reader reader2) {
        this.reader = reader2;
    }

    /* access modifiers changed from: protected */
    public Reader getReader() {
        return this.reader;
    }

    public Result decode(LuminanceSource luminanceSource) {
        return decode(toBitmap(luminanceSource));
    }

    /* access modifiers changed from: protected */
    public BinaryBitmap toBitmap(LuminanceSource luminanceSource) {
        return new BinaryBitmap(new HybridBinarizer(luminanceSource));
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public Result decode(BinaryBitmap binaryBitmap) {
        this.possibleResultPoints.clear();
        try {
            Reader reader2 = this.reader;
            if (reader2 instanceof MultiFormatReader) {
                Result decodeWithState = ((MultiFormatReader) reader2).decodeWithState(binaryBitmap);
                this.reader.reset();
                return decodeWithState;
            }
            Result decode = reader2.decode(binaryBitmap);
            this.reader.reset();
            return decode;
        } catch (Exception unused) {
            this.reader.reset();
            return null;
        } catch (Throwable th) {
            this.reader.reset();
            throw th;
        }
    }

    public List<ResultPoint> getPossibleResultPoints() {
        return new ArrayList(this.possibleResultPoints);
    }

    public void foundPossibleResultPoint(ResultPoint resultPoint) {
        this.possibleResultPoints.add(resultPoint);
    }
}
