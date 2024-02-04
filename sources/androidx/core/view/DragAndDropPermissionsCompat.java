package androidx.core.view;

import android.app.Activity;
import android.os.Build;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
import androidx.core.os.TraceCompat$$ExternalSyntheticApiModelOutline0;

public final class DragAndDropPermissionsCompat {
    private Object mDragAndDropPermissions;

    private DragAndDropPermissionsCompat(Object obj) {
        this.mDragAndDropPermissions = obj;
    }

    public static DragAndDropPermissionsCompat request(Activity activity, DragEvent dragEvent) {
        DragAndDropPermissions m;
        if (Build.VERSION.SDK_INT < 24 || (m = activity.requestDragAndDropPermissions(dragEvent)) == null) {
            return null;
        }
        return new DragAndDropPermissionsCompat(m);
    }

    public void release() {
        if (Build.VERSION.SDK_INT >= 24) {
            TraceCompat$$ExternalSyntheticApiModelOutline0.m(this.mDragAndDropPermissions).release();
        }
    }
}
