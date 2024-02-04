package androidx.core.telephony.mbms;

import android.content.Context;
import android.os.Build;
import android.os.LocaleList;
import android.telephony.mbms.ServiceInfo;
import androidx.core.app.DialogCompat$$ExternalSyntheticApiModelOutline0;
import java.util.Locale;

public final class MbmsHelper {
    private MbmsHelper() {
    }

    public static CharSequence getBestNameForService(Context context, ServiceInfo serviceInfo) {
        if (Build.VERSION.SDK_INT < 28) {
            return null;
        }
        LocaleList m = DialogCompat$$ExternalSyntheticApiModelOutline0.m(context.getResources().getConfiguration());
        int size = serviceInfo.getNamedContentLocales().size();
        if (size == 0) {
            return null;
        }
        String[] strArr = new String[size];
        int i = 0;
        for (Locale languageTag : serviceInfo.getNamedContentLocales()) {
            strArr[i] = languageTag.toLanguageTag();
            i++;
        }
        Locale m2 = m.getFirstMatch(strArr);
        if (m2 == null) {
            return null;
        }
        return serviceInfo.getNameForLocale(m2);
    }
}
