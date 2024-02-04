package androidx.core.database;

import android.database.CursorWindow;
import android.os.Build;
import androidx.core.graphics.Insets$$ExternalSyntheticApiModelOutline0;

public final class CursorWindowCompat {
    private CursorWindowCompat() {
    }

    public static CursorWindow create(String str, long j) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Insets$$ExternalSyntheticApiModelOutline0.m(str, j);
        }
        return new CursorWindow(str);
    }
}
