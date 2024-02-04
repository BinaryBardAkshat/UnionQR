package androidx.core.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.DisplayCutout;
import androidx.core.graphics.Insets;
import androidx.core.os.BuildCompat;
import androidx.core.os.TraceCompat$$ExternalSyntheticApiModelOutline0;
import androidx.core.util.ObjectsCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DisplayCutoutCompat {
    private final Object mDisplayCutout;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DisplayCutoutCompat(Rect rect, List<Rect> list) {
        this(Build.VERSION.SDK_INT >= 28 ? TraceCompat$$ExternalSyntheticApiModelOutline0.m(rect, (List) list) : null);
    }

    public DisplayCutoutCompat(Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4, Insets insets2) {
        this(constructDisplayCutout(insets, rect, rect2, rect3, rect4, insets2));
    }

    private static DisplayCutout constructDisplayCutout(Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4, Insets insets2) {
        if (BuildCompat.isAtLeastR()) {
            TraceCompat$$ExternalSyntheticApiModelOutline0.m$2();
            return TraceCompat$$ExternalSyntheticApiModelOutline0.m(insets.toPlatformInsets(), rect, rect2, rect3, rect4, insets2.toPlatformInsets());
        } else if (Build.VERSION.SDK_INT >= 29) {
            TraceCompat$$ExternalSyntheticApiModelOutline0.m$2();
            return TraceCompat$$ExternalSyntheticApiModelOutline0.m(insets.toPlatformInsets(), rect, rect2, rect3, rect4);
        } else if (Build.VERSION.SDK_INT < 28) {
            return null;
        } else {
            Rect rect5 = new Rect(insets.left, insets.top, insets.right, insets.bottom);
            ArrayList arrayList = new ArrayList();
            if (rect != null) {
                arrayList.add(rect);
            }
            if (rect2 != null) {
                arrayList.add(rect2);
            }
            if (rect3 != null) {
                arrayList.add(rect3);
            }
            if (rect4 != null) {
                arrayList.add(rect4);
            }
            return TraceCompat$$ExternalSyntheticApiModelOutline0.m(rect5, (List) arrayList);
        }
    }

    private DisplayCutoutCompat(Object obj) {
        this.mDisplayCutout = obj;
    }

    public int getSafeInsetTop() {
        if (Build.VERSION.SDK_INT >= 28) {
            return TraceCompat$$ExternalSyntheticApiModelOutline0.m(this.mDisplayCutout).getSafeInsetTop();
        }
        return 0;
    }

    public int getSafeInsetBottom() {
        if (Build.VERSION.SDK_INT >= 28) {
            return TraceCompat$$ExternalSyntheticApiModelOutline0.m(this.mDisplayCutout).getSafeInsetBottom();
        }
        return 0;
    }

    public int getSafeInsetLeft() {
        if (Build.VERSION.SDK_INT >= 28) {
            return TraceCompat$$ExternalSyntheticApiModelOutline0.m(TraceCompat$$ExternalSyntheticApiModelOutline0.m(this.mDisplayCutout));
        }
        return 0;
    }

    public int getSafeInsetRight() {
        if (Build.VERSION.SDK_INT >= 28) {
            return TraceCompat$$ExternalSyntheticApiModelOutline0.m(this.mDisplayCutout).getSafeInsetRight();
        }
        return 0;
    }

    public List<Rect> getBoundingRects() {
        if (Build.VERSION.SDK_INT >= 28) {
            return TraceCompat$$ExternalSyntheticApiModelOutline0.m(TraceCompat$$ExternalSyntheticApiModelOutline0.m(this.mDisplayCutout));
        }
        return Collections.emptyList();
    }

    public Insets getWaterfallInsets() {
        if (BuildCompat.isAtLeastR()) {
            return Insets.toCompatInsets(TraceCompat$$ExternalSyntheticApiModelOutline0.m(TraceCompat$$ExternalSyntheticApiModelOutline0.m(this.mDisplayCutout)));
        }
        return Insets.NONE;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(this.mDisplayCutout, ((DisplayCutoutCompat) obj).mDisplayCutout);
    }

    public int hashCode() {
        Object obj = this.mDisplayCutout;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return "DisplayCutoutCompat{" + this.mDisplayCutout + "}";
    }

    static DisplayCutoutCompat wrap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new DisplayCutoutCompat(obj);
    }

    /* access modifiers changed from: package-private */
    public DisplayCutout unwrap() {
        return TraceCompat$$ExternalSyntheticApiModelOutline0.m(this.mDisplayCutout);
    }
}
