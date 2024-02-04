package androidx.core.os;

import android.os.LocaleList;
import java.util.Locale;

final class LocaleListPlatformWrapper implements LocaleListInterface {
    private final LocaleList mLocaleList;

    LocaleListPlatformWrapper(LocaleList localeList) {
        this.mLocaleList = localeList;
    }

    public Object getLocaleList() {
        return this.mLocaleList;
    }

    public Locale get(int i) {
        return this.mLocaleList.get(i);
    }

    public boolean isEmpty() {
        return TraceCompat$$ExternalSyntheticApiModelOutline0.m(this.mLocaleList);
    }

    public int size() {
        return TraceCompat$$ExternalSyntheticApiModelOutline0.m(this.mLocaleList);
    }

    public int indexOf(Locale locale) {
        return this.mLocaleList.indexOf(locale);
    }

    public boolean equals(Object obj) {
        return this.mLocaleList.equals(((LocaleListInterface) obj).getLocaleList());
    }

    public int hashCode() {
        return TraceCompat$$ExternalSyntheticApiModelOutline0.m$1(this.mLocaleList);
    }

    public String toString() {
        return TraceCompat$$ExternalSyntheticApiModelOutline0.m$1(this.mLocaleList);
    }

    public String toLanguageTags() {
        return TraceCompat$$ExternalSyntheticApiModelOutline0.m(this.mLocaleList);
    }

    public Locale getFirstMatch(String[] strArr) {
        return this.mLocaleList.getFirstMatch(strArr);
    }
}
