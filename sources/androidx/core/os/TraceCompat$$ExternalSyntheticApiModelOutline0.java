package androidx.core.os;

import android.content.ClipData;
import android.graphics.Insets;
import android.graphics.Rect;
import android.location.LocationRequest;
import android.os.LocaleList;
import android.view.ContentInfo;
import android.view.DisplayCutout;
import java.util.List;
import java.util.Locale;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TraceCompat$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ LocationRequest.Builder m(long j) {
        return new LocationRequest.Builder(j);
    }

    public static /* synthetic */ LocaleList m(Locale[] localeArr) {
        return new LocaleList(localeArr);
    }

    public static /* synthetic */ ContentInfo.Builder m(ClipData clipData, int i) {
        return new ContentInfo.Builder(clipData, i);
    }

    public static /* synthetic */ DisplayCutout m(Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4) {
        return new DisplayCutout(insets, rect, rect2, rect3, rect4);
    }

    public static /* synthetic */ DisplayCutout m(Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4, Insets insets2) {
        return new DisplayCutout(insets, rect, rect2, rect3, rect4, insets2);
    }

    public static /* synthetic */ DisplayCutout m(Rect rect, List list) {
        return new DisplayCutout(rect, list);
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: MethodInlineVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.visitors.MethodInlineVisitor.inlineMth(MethodInlineVisitor.java:57)
        	at jadx.core.dex.visitors.MethodInlineVisitor.visit(MethodInlineVisitor.java:47)
        */
    public static /* synthetic */ void m$2() {
        /*
            android.view.DisplayCutout r0 = new android.view.DisplayCutout
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.os.TraceCompat$$ExternalSyntheticApiModelOutline0.m$2():void");
    }
}
