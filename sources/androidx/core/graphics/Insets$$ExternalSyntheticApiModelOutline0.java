package androidx.core.graphics;

import android.database.CursorWindow;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.fingerprint.FingerprintManager;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Insets$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ CursorWindow m(String str, long j) {
        return new CursorWindow(str, j);
    }

    public static /* synthetic */ BlendModeColorFilter m(int i, BlendMode blendMode) {
        return new BlendModeColorFilter(i, blendMode);
    }

    public static /* synthetic */ AdaptiveIconDrawable m(Drawable drawable, Drawable drawable2) {
        return new AdaptiveIconDrawable(drawable, drawable2);
    }

    public static /* synthetic */ FingerprintManager.CryptoObject m(Signature signature) {
        return new FingerprintManager.CryptoObject(signature);
    }

    public static /* synthetic */ FingerprintManager.CryptoObject m(Cipher cipher) {
        return new FingerprintManager.CryptoObject(cipher);
    }

    public static /* synthetic */ FingerprintManager.CryptoObject m(Mac mac) {
        return new FingerprintManager.CryptoObject(mac);
    }
}
