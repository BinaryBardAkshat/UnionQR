package androidx.core.os;

import android.os.Environment;
import java.io.File;

public final class EnvironmentCompat {
    public static final String MEDIA_UNKNOWN = "unknown";
    private static final String TAG = "EnvironmentCompat";

    public static String getStorageState(File file) {
        return Environment.getExternalStorageState(file);
    }

    private EnvironmentCompat() {
    }
}
