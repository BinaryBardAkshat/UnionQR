package androidx.core.os;

import android.content.res.Configuration;
import android.os.Build;
import androidx.core.app.DialogCompat$$ExternalSyntheticApiModelOutline0;

public final class ConfigurationCompat {
    private ConfigurationCompat() {
    }

    public static LocaleListCompat getLocales(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 24) {
            return LocaleListCompat.wrap(DialogCompat$$ExternalSyntheticApiModelOutline0.m(configuration));
        }
        return LocaleListCompat.create(configuration.locale);
    }
}
