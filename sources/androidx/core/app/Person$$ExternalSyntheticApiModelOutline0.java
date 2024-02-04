package androidx.core.app;

import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.Context;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Icon;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Person$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ RemoteAction m(Icon icon, CharSequence charSequence, CharSequence charSequence2, PendingIntent pendingIntent) {
        return new RemoteAction(icon, charSequence, charSequence2, pendingIntent);
    }

    public static /* synthetic */ ShortcutInfo.Builder m(Context context, String str) {
        return new ShortcutInfo.Builder(context, str);
    }
}
