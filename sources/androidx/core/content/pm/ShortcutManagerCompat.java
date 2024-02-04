package androidx.core.content.pm;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import androidx.core.app.Person$$ExternalSyntheticApiModelOutline0;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutInfoCompatSaver;
import androidx.core.graphics.Insets$$ExternalSyntheticApiModelOutline0;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.util.Preconditions;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShortcutManagerCompat {
    static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    private static final int DEFAULT_MAX_ICON_DIMENSION_DP = 96;
    private static final int DEFAULT_MAX_ICON_DIMENSION_LOWRAM_DP = 48;
    public static final String EXTRA_SHORTCUT_ID = "android.intent.extra.shortcut.ID";
    public static final int FLAG_MATCH_CACHED = 8;
    public static final int FLAG_MATCH_DYNAMIC = 2;
    public static final int FLAG_MATCH_MANIFEST = 1;
    public static final int FLAG_MATCH_PINNED = 4;
    static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";
    private static final String SHORTCUT_LISTENER_INTENT_FILTER_ACTION = "androidx.core.content.pm.SHORTCUT_LISTENER";
    private static final String SHORTCUT_LISTENER_META_DATA_KEY = "androidx.core.content.pm.shortcut_listener_impl";
    private static volatile List<ShortcutInfoChangeListener> sShortcutInfoChangeListeners;
    private static volatile ShortcutInfoCompatSaver<?> sShortcutInfoCompatSaver;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ShortcutMatchFlags {
    }

    private ShortcutManagerCompat() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isRequestPinShortcutSupported(android.content.Context r4) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 26
            if (r0 < r1) goto L_0x0017
            java.lang.Class r0 = androidx.core.app.Person$$ExternalSyntheticApiModelOutline0.m$1()
            java.lang.Object r4 = r4.getSystemService(r0)
            android.content.pm.ShortcutManager r4 = androidx.core.app.Person$$ExternalSyntheticApiModelOutline0.m((java.lang.Object) r4)
            boolean r4 = androidx.core.graphics.Insets$$ExternalSyntheticApiModelOutline0.m((android.content.pm.ShortcutManager) r4)
            return r4
        L_0x0017:
            java.lang.String r0 = "com.android.launcher.permission.INSTALL_SHORTCUT"
            int r1 = androidx.core.content.ContextCompat.checkSelfPermission(r4, r0)
            r2 = 0
            if (r1 == 0) goto L_0x0021
            return r2
        L_0x0021:
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            android.content.Intent r1 = new android.content.Intent
            java.lang.String r3 = "com.android.launcher.action.INSTALL_SHORTCUT"
            r1.<init>(r3)
            java.util.List r4 = r4.queryBroadcastReceivers(r1, r2)
            java.util.Iterator r4 = r4.iterator()
        L_0x0034:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0052
            java.lang.Object r1 = r4.next()
            android.content.pm.ResolveInfo r1 = (android.content.pm.ResolveInfo) r1
            android.content.pm.ActivityInfo r1 = r1.activityInfo
            java.lang.String r1 = r1.permission
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 != 0) goto L_0x0050
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0034
        L_0x0050:
            r4 = 1
            return r4
        L_0x0052:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.pm.ShortcutManagerCompat.isRequestPinShortcutSupported(android.content.Context):boolean");
    }

    public static boolean requestPinShortcut(Context context, ShortcutInfoCompat shortcutInfoCompat, final IntentSender intentSender) {
        if (Build.VERSION.SDK_INT >= 26) {
            return Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())).requestPinShortcut(shortcutInfoCompat.toShortcutInfo(), intentSender);
        }
        if (!isRequestPinShortcutSupported(context)) {
            return false;
        }
        Intent addToIntent = shortcutInfoCompat.addToIntent(new Intent(ACTION_INSTALL_SHORTCUT));
        if (intentSender == null) {
            context.sendBroadcast(addToIntent);
            return true;
        }
        context.sendOrderedBroadcast(addToIntent, (String) null, new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                try {
                    intentSender.sendIntent(context, 0, (Intent) null, (IntentSender.OnFinished) null, (Handler) null);
                } catch (IntentSender.SendIntentException unused) {
                }
            }
        }, (Handler) null, -1, (String) null, (Bundle) null);
        return true;
    }

    public static Intent createShortcutResultIntent(Context context, ShortcutInfoCompat shortcutInfoCompat) {
        Intent m = Build.VERSION.SDK_INT >= 26 ? Person$$ExternalSyntheticApiModelOutline0.m(Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())), shortcutInfoCompat.toShortcutInfo()) : null;
        if (m == null) {
            m = new Intent();
        }
        return shortcutInfoCompat.addToIntent(m);
    }

    public static List<ShortcutInfoCompat> getShortcuts(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 30) {
            return ShortcutInfoCompat.fromShortcuts(context, Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())).getShortcuts(i));
        }
        if (Build.VERSION.SDK_INT >= 25) {
            ShortcutManager m = Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1()));
            ArrayList arrayList = new ArrayList();
            if ((i & 1) != 0) {
                arrayList.addAll(Insets$$ExternalSyntheticApiModelOutline0.m(m));
            }
            if ((i & 2) != 0) {
                arrayList.addAll(Person$$ExternalSyntheticApiModelOutline0.m(m));
            }
            if ((i & 4) != 0) {
                arrayList.addAll(m.getPinnedShortcuts());
            }
            return ShortcutInfoCompat.fromShortcuts(context, arrayList);
        }
        if ((i & 2) != 0) {
            try {
                return getShortcutInfoSaverInstance(context).getShortcuts();
            } catch (Exception unused) {
            }
        }
        return Collections.emptyList();
    }

    public static boolean addDynamicShortcuts(Context context, List<ShortcutInfoCompat> list) {
        if (Build.VERSION.SDK_INT <= 29) {
            convertUriIconsToBitmapIcons(context, list);
        }
        if (Build.VERSION.SDK_INT >= 25) {
            ArrayList arrayList = new ArrayList();
            for (ShortcutInfoCompat shortcutInfo : list) {
                arrayList.add(shortcutInfo.toShortcutInfo());
            }
            if (!Person$$ExternalSyntheticApiModelOutline0.m(Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())), (List) arrayList)) {
                return false;
            }
        }
        getShortcutInfoSaverInstance(context).addShortcuts(list);
        for (ShortcutInfoChangeListener onShortcutAdded : getShortcutInfoListeners(context)) {
            onShortcutAdded.onShortcutAdded(list);
        }
        return true;
    }

    public static int getMaxShortcutCountPerActivity(Context context) {
        Preconditions.checkNotNull(context);
        if (Build.VERSION.SDK_INT >= 25) {
            return Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())).getMaxShortcutCountPerActivity();
        }
        return 5;
    }

    public static boolean isRateLimitingActive(Context context) {
        Preconditions.checkNotNull(context);
        if (Build.VERSION.SDK_INT >= 25) {
            return Person$$ExternalSyntheticApiModelOutline0.m(Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())));
        }
        return getShortcuts(context, 3).size() == getMaxShortcutCountPerActivity(context);
    }

    public static int getIconMaxWidth(Context context) {
        Preconditions.checkNotNull(context);
        if (Build.VERSION.SDK_INT >= 25) {
            return Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())).getIconMaxWidth();
        }
        return getIconDimensionInternal(context, true);
    }

    public static int getIconMaxHeight(Context context) {
        Preconditions.checkNotNull(context);
        if (Build.VERSION.SDK_INT >= 25) {
            return Person$$ExternalSyntheticApiModelOutline0.m(Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())));
        }
        return getIconDimensionInternal(context, false);
    }

    public static void reportShortcutUsed(Context context, String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        if (Build.VERSION.SDK_INT >= 25) {
            Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())).reportShortcutUsed(str);
        }
        for (ShortcutInfoChangeListener onShortcutUsageReported : getShortcutInfoListeners(context)) {
            onShortcutUsageReported.onShortcutUsageReported(Collections.singletonList(str));
        }
    }

    public static boolean setDynamicShortcuts(Context context, List<ShortcutInfoCompat> list) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(list);
        if (Build.VERSION.SDK_INT >= 25) {
            ArrayList arrayList = new ArrayList(list.size());
            for (ShortcutInfoCompat shortcutInfo : list) {
                arrayList.add(shortcutInfo.toShortcutInfo());
            }
            if (!Insets$$ExternalSyntheticApiModelOutline0.m(Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())), (List) arrayList)) {
                return false;
            }
        }
        getShortcutInfoSaverInstance(context).removeAllShortcuts();
        getShortcutInfoSaverInstance(context).addShortcuts(list);
        for (ShortcutInfoChangeListener next : getShortcutInfoListeners(context)) {
            next.onAllShortcutsRemoved();
            next.onShortcutAdded(list);
        }
        return true;
    }

    public static List<ShortcutInfoCompat> getDynamicShortcuts(Context context) {
        if (Build.VERSION.SDK_INT >= 25) {
            List<Object> m = Person$$ExternalSyntheticApiModelOutline0.m(Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())));
            ArrayList arrayList = new ArrayList(m.size());
            for (Object m2 : m) {
                arrayList.add(new ShortcutInfoCompat.Builder(context, Person$$ExternalSyntheticApiModelOutline0.m(m2)).build());
            }
            return arrayList;
        }
        try {
            return getShortcutInfoSaverInstance(context).getShortcuts();
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    public static boolean updateShortcuts(Context context, List<ShortcutInfoCompat> list) {
        if (Build.VERSION.SDK_INT <= 29) {
            convertUriIconsToBitmapIcons(context, list);
        }
        if (Build.VERSION.SDK_INT >= 25) {
            ArrayList arrayList = new ArrayList();
            for (ShortcutInfoCompat shortcutInfo : list) {
                arrayList.add(shortcutInfo.toShortcutInfo());
            }
            if (!Person$$ExternalSyntheticApiModelOutline0.m$1(Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())), (List) arrayList)) {
                return false;
            }
        }
        getShortcutInfoSaverInstance(context).addShortcuts(list);
        for (ShortcutInfoChangeListener onShortcutUpdated : getShortcutInfoListeners(context)) {
            onShortcutUpdated.onShortcutUpdated(list);
        }
        return true;
    }

    static boolean convertUriIconToBitmapIcon(Context context, ShortcutInfoCompat shortcutInfoCompat) {
        Bitmap decodeStream;
        IconCompat iconCompat;
        if (shortcutInfoCompat.mIcon == null) {
            return false;
        }
        int i = shortcutInfoCompat.mIcon.mType;
        if (i != 6 && i != 4) {
            return true;
        }
        InputStream uriInputStream = shortcutInfoCompat.mIcon.getUriInputStream(context);
        if (uriInputStream == null || (decodeStream = BitmapFactory.decodeStream(uriInputStream)) == null) {
            return false;
        }
        if (i == 6) {
            iconCompat = IconCompat.createWithAdaptiveBitmap(decodeStream);
        } else {
            iconCompat = IconCompat.createWithBitmap(decodeStream);
        }
        shortcutInfoCompat.mIcon = iconCompat;
        return true;
    }

    static void convertUriIconsToBitmapIcons(Context context, List<ShortcutInfoCompat> list) {
        for (ShortcutInfoCompat shortcutInfoCompat : new ArrayList(list)) {
            if (!convertUriIconToBitmapIcon(context, shortcutInfoCompat)) {
                list.remove(shortcutInfoCompat);
            }
        }
    }

    public static void disableShortcuts(Context context, List<String> list, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 25) {
            Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())).disableShortcuts(list, charSequence);
        }
        getShortcutInfoSaverInstance(context).removeShortcuts(list);
        for (ShortcutInfoChangeListener onShortcutRemoved : getShortcutInfoListeners(context)) {
            onShortcutRemoved.onShortcutRemoved(list);
        }
    }

    public static void enableShortcuts(Context context, List<ShortcutInfoCompat> list) {
        if (Build.VERSION.SDK_INT >= 25) {
            ArrayList arrayList = new ArrayList(list.size());
            for (ShortcutInfoCompat shortcutInfoCompat : list) {
                arrayList.add(shortcutInfoCompat.mId);
            }
            Person$$ExternalSyntheticApiModelOutline0.m(Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())), (List) arrayList);
        }
        getShortcutInfoSaverInstance(context).addShortcuts(list);
        for (ShortcutInfoChangeListener onShortcutAdded : getShortcutInfoListeners(context)) {
            onShortcutAdded.onShortcutAdded(list);
        }
    }

    public static void removeDynamicShortcuts(Context context, List<String> list) {
        if (Build.VERSION.SDK_INT >= 25) {
            Person$$ExternalSyntheticApiModelOutline0.m$1(Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())), (List) list);
        }
        getShortcutInfoSaverInstance(context).removeShortcuts(list);
        for (ShortcutInfoChangeListener onShortcutRemoved : getShortcutInfoListeners(context)) {
            onShortcutRemoved.onShortcutRemoved(list);
        }
    }

    public static void removeAllDynamicShortcuts(Context context) {
        if (Build.VERSION.SDK_INT >= 25) {
            Person$$ExternalSyntheticApiModelOutline0.m(Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())));
        }
        getShortcutInfoSaverInstance(context).removeAllShortcuts();
        for (ShortcutInfoChangeListener onAllShortcutsRemoved : getShortcutInfoListeners(context)) {
            onAllShortcutsRemoved.onAllShortcutsRemoved();
        }
    }

    public static void removeLongLivedShortcuts(Context context, List<String> list) {
        if (Build.VERSION.SDK_INT < 30) {
            removeDynamicShortcuts(context, list);
            return;
        }
        Insets$$ExternalSyntheticApiModelOutline0.m(Person$$ExternalSyntheticApiModelOutline0.m(context.getSystemService(Person$$ExternalSyntheticApiModelOutline0.m$1())), (List) list);
        getShortcutInfoSaverInstance(context).removeShortcuts(list);
        for (ShortcutInfoChangeListener onShortcutRemoved : getShortcutInfoListeners(context)) {
            onShortcutRemoved.onShortcutRemoved(list);
        }
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public static boolean pushDynamicShortcut(android.content.Context r6, androidx.core.content.pm.ShortcutInfoCompat r7) {
        /*
            androidx.core.util.Preconditions.checkNotNull(r6)
            androidx.core.util.Preconditions.checkNotNull(r7)
            int r0 = getMaxShortcutCountPerActivity(r6)
            r1 = 0
            if (r0 != 0) goto L_0x000e
            return r1
        L_0x000e:
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 29
            if (r2 > r3) goto L_0x0017
            convertUriIconToBitmapIcon(r6, r7)
        L_0x0017:
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 30
            r4 = 1
            if (r2 < r3) goto L_0x0032
            java.lang.Class r2 = androidx.core.app.Person$$ExternalSyntheticApiModelOutline0.m$1()
            java.lang.Object r2 = r6.getSystemService(r2)
            android.content.pm.ShortcutManager r2 = androidx.core.app.Person$$ExternalSyntheticApiModelOutline0.m((java.lang.Object) r2)
            android.content.pm.ShortcutInfo r3 = r7.toShortcutInfo()
            androidx.core.app.Person$$ExternalSyntheticApiModelOutline0.m((android.content.pm.ShortcutManager) r2, (android.content.pm.ShortcutInfo) r3)
            goto L_0x0073
        L_0x0032:
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 25
            if (r2 < r3) goto L_0x0073
            java.lang.Class r2 = androidx.core.app.Person$$ExternalSyntheticApiModelOutline0.m$1()
            java.lang.Object r2 = r6.getSystemService(r2)
            android.content.pm.ShortcutManager r2 = androidx.core.app.Person$$ExternalSyntheticApiModelOutline0.m((java.lang.Object) r2)
            boolean r3 = androidx.core.app.Person$$ExternalSyntheticApiModelOutline0.m((android.content.pm.ShortcutManager) r2)
            if (r3 == 0) goto L_0x004b
            return r1
        L_0x004b:
            java.util.List r3 = androidx.core.app.Person$$ExternalSyntheticApiModelOutline0.m((android.content.pm.ShortcutManager) r2)
            int r5 = r3.size()
            if (r5 < r0) goto L_0x0064
            java.lang.String r3 = androidx.core.content.pm.ShortcutManagerCompat.Api25Impl.getShortcutInfoWithLowestRank(r3)
            java.lang.String[] r3 = new java.lang.String[]{r3}
            java.util.List r3 = java.util.Arrays.asList(r3)
            androidx.core.app.Person$$ExternalSyntheticApiModelOutline0.m$1((android.content.pm.ShortcutManager) r2, (java.util.List) r3)
        L_0x0064:
            android.content.pm.ShortcutInfo[] r3 = new android.content.pm.ShortcutInfo[r4]
            android.content.pm.ShortcutInfo r5 = r7.toShortcutInfo()
            r3[r1] = r5
            java.util.List r3 = java.util.Arrays.asList(r3)
            androidx.core.app.Person$$ExternalSyntheticApiModelOutline0.m((android.content.pm.ShortcutManager) r2, (java.util.List) r3)
        L_0x0073:
            androidx.core.content.pm.ShortcutInfoCompatSaver r2 = getShortcutInfoSaverInstance(r6)
            java.util.List r3 = r2.getShortcuts()     // Catch:{ Exception -> 0x00e4, all -> 0x00bf }
            int r5 = r3.size()     // Catch:{ Exception -> 0x00e4, all -> 0x00bf }
            if (r5 < r0) goto L_0x0090
            java.lang.String[] r0 = new java.lang.String[r4]     // Catch:{ Exception -> 0x00e4, all -> 0x00bf }
            java.lang.String r3 = getShortcutInfoCompatWithLowestRank(r3)     // Catch:{ Exception -> 0x00e4, all -> 0x00bf }
            r0[r1] = r3     // Catch:{ Exception -> 0x00e4, all -> 0x00bf }
            java.util.List r0 = java.util.Arrays.asList(r0)     // Catch:{ Exception -> 0x00e4, all -> 0x00bf }
            r2.removeShortcuts(r0)     // Catch:{ Exception -> 0x00e4, all -> 0x00bf }
        L_0x0090:
            androidx.core.content.pm.ShortcutInfoCompat[] r0 = new androidx.core.content.pm.ShortcutInfoCompat[r4]     // Catch:{ Exception -> 0x00e4, all -> 0x00bf }
            r0[r1] = r7     // Catch:{ Exception -> 0x00e4, all -> 0x00bf }
            java.util.List r0 = java.util.Arrays.asList(r0)     // Catch:{ Exception -> 0x00e4, all -> 0x00bf }
            r2.addShortcuts(r0)     // Catch:{ Exception -> 0x00e4, all -> 0x00bf }
            java.util.List r0 = getShortcutInfoListeners(r6)
            java.util.Iterator r0 = r0.iterator()
        L_0x00a3:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00b7
            java.lang.Object r1 = r0.next()
            androidx.core.content.pm.ShortcutInfoChangeListener r1 = (androidx.core.content.pm.ShortcutInfoChangeListener) r1
            java.util.List r2 = java.util.Collections.singletonList(r7)
            r1.onShortcutAdded(r2)
            goto L_0x00a3
        L_0x00b7:
            java.lang.String r7 = r7.getId()
            reportShortcutUsed(r6, r7)
            return r4
        L_0x00bf:
            r0 = move-exception
            java.util.List r1 = getShortcutInfoListeners(r6)
            java.util.Iterator r1 = r1.iterator()
        L_0x00c8:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00dc
            java.lang.Object r2 = r1.next()
            androidx.core.content.pm.ShortcutInfoChangeListener r2 = (androidx.core.content.pm.ShortcutInfoChangeListener) r2
            java.util.List r3 = java.util.Collections.singletonList(r7)
            r2.onShortcutAdded(r3)
            goto L_0x00c8
        L_0x00dc:
            java.lang.String r7 = r7.getId()
            reportShortcutUsed(r6, r7)
            throw r0
        L_0x00e4:
            java.util.List r0 = getShortcutInfoListeners(r6)
            java.util.Iterator r0 = r0.iterator()
        L_0x00ec:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0100
            java.lang.Object r2 = r0.next()
            androidx.core.content.pm.ShortcutInfoChangeListener r2 = (androidx.core.content.pm.ShortcutInfoChangeListener) r2
            java.util.List r3 = java.util.Collections.singletonList(r7)
            r2.onShortcutAdded(r3)
            goto L_0x00ec
        L_0x0100:
            java.lang.String r7 = r7.getId()
            reportShortcutUsed(r6, r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.pm.ShortcutManagerCompat.pushDynamicShortcut(android.content.Context, androidx.core.content.pm.ShortcutInfoCompat):boolean");
    }

    private static String getShortcutInfoCompatWithLowestRank(List<ShortcutInfoCompat> list) {
        int i = -1;
        String str = null;
        for (ShortcutInfoCompat next : list) {
            if (next.getRank() > i) {
                String id = next.getId();
                str = id;
                i = next.getRank();
            }
        }
        return str;
    }

    static void setShortcutInfoCompatSaver(ShortcutInfoCompatSaver<Void> shortcutInfoCompatSaver) {
        sShortcutInfoCompatSaver = shortcutInfoCompatSaver;
    }

    static void setShortcutInfoChangeListeners(List<ShortcutInfoChangeListener> list) {
        sShortcutInfoChangeListeners = list;
    }

    static List<ShortcutInfoChangeListener> getShortcutInfoChangeListeners() {
        return sShortcutInfoChangeListeners;
    }

    private static int getIconDimensionInternal(Context context, boolean z) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        int max = Math.max(1, activityManager == null || activityManager.isLowRamDevice() ? 48 : 96);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (((float) max) * ((z ? displayMetrics.xdpi : displayMetrics.ydpi) / 160.0f));
    }

    private static ShortcutInfoCompatSaver<?> getShortcutInfoSaverInstance(Context context) {
        if (sShortcutInfoCompatSaver == null) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    sShortcutInfoCompatSaver = (ShortcutInfoCompatSaver) Class.forName("androidx.sharetarget.ShortcutInfoCompatSaverImpl", false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
                } catch (Exception unused) {
                }
            }
            if (sShortcutInfoCompatSaver == null) {
                sShortcutInfoCompatSaver = new ShortcutInfoCompatSaver.NoopImpl();
            }
        }
        return sShortcutInfoCompatSaver;
    }

    private static List<ShortcutInfoChangeListener> getShortcutInfoListeners(Context context) {
        Bundle bundle;
        String string;
        if (sShortcutInfoChangeListeners == null) {
            ArrayList arrayList = new ArrayList();
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(SHORTCUT_LISTENER_INTENT_FILTER_ACTION);
            intent.setPackage(context.getPackageName());
            for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 128)) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (!(activityInfo == null || (bundle = activityInfo.metaData) == null || (string = bundle.getString(SHORTCUT_LISTENER_META_DATA_KEY)) == null)) {
                    try {
                        arrayList.add((ShortcutInfoChangeListener) Class.forName(string, false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{context}));
                    } catch (Exception unused) {
                    }
                }
            }
            if (sShortcutInfoChangeListeners == null) {
                sShortcutInfoChangeListeners = arrayList;
            }
        }
        return sShortcutInfoChangeListeners;
    }

    private static class Api25Impl {
        private Api25Impl() {
        }

        static String getShortcutInfoWithLowestRank(List<ShortcutInfo> list) {
            int i = -1;
            String str = null;
            for (ShortcutInfo next : list) {
                if (next.getRank() > i) {
                    String id = next.getId();
                    str = id;
                    i = next.getRank();
                }
            }
            return str;
        }
    }
}
