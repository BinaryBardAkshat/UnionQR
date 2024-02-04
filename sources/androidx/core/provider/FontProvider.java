package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.os.CancellationSignal;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class FontProvider {
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator<byte[]>() {
        public int compare(byte[] bArr, byte[] bArr2) {
            if (bArr.length != bArr2.length) {
                return bArr.length - bArr2.length;
            }
            for (int i = 0; i < bArr.length; i++) {
                byte b = bArr[i];
                byte b2 = bArr2[i];
                if (b != b2) {
                    return b - b2;
                }
            }
            return 0;
        }
    };

    private FontProvider() {
    }

    static FontsContractCompat.FontFamilyResult getFontFamilyResult(Context context, FontRequest fontRequest, CancellationSignal cancellationSignal) throws PackageManager.NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return FontsContractCompat.FontFamilyResult.create(1, (FontsContractCompat.FontInfo[]) null);
        }
        return FontsContractCompat.FontFamilyResult.create(0, query(context, fontRequest, provider.authority, cancellationSignal));
    }

    static ProviderInfo getProvider(PackageManager packageManager, FontRequest fontRequest, Resources resources) throws PackageManager.NameNotFoundException {
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
        } else if (resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            List<byte[]> convertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
            Collections.sort(convertToByteArrayList, sByteArrayComparator);
            List<List<byte[]>> certificates = getCertificates(fontRequest, resources);
            for (int i = 0; i < certificates.size(); i++) {
                ArrayList arrayList = new ArrayList(certificates.get(i));
                Collections.sort(arrayList, sByteArrayComparator);
                if (equalsByteArrayList(convertToByteArrayList, arrayList)) {
                    return resolveContentProvider;
                }
            }
            return null;
        } else {
            throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static androidx.core.provider.FontsContractCompat.FontInfo[] query(android.content.Context r18, androidx.core.provider.FontRequest r19, java.lang.String r20, android.os.CancellationSignal r21) {
        /*
            r0 = r20
            java.lang.String r1 = "result_code"
            java.lang.String r2 = "font_italic"
            java.lang.String r3 = "font_weight"
            java.lang.String r4 = "font_ttc_index"
            java.lang.String r5 = "file_id"
            java.lang.String r6 = "_id"
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            android.net.Uri$Builder r8 = new android.net.Uri$Builder
            r8.<init>()
            java.lang.String r9 = "content"
            android.net.Uri$Builder r8 = r8.scheme(r9)
            android.net.Uri$Builder r8 = r8.authority(r0)
            android.net.Uri r8 = r8.build()
            android.net.Uri$Builder r10 = new android.net.Uri$Builder
            r10.<init>()
            android.net.Uri$Builder r9 = r10.scheme(r9)
            android.net.Uri$Builder r0 = r9.authority(r0)
            java.lang.String r9 = "file"
            android.net.Uri$Builder r0 = r0.appendPath(r9)
            android.net.Uri r0 = r0.build()
            r9 = 7
            r17 = 0
            java.lang.String[] r12 = new java.lang.String[r9]     // Catch:{ all -> 0x00f1 }
            r9 = 0
            r12[r9] = r6     // Catch:{ all -> 0x00f1 }
            r15 = 1
            r12[r15] = r5     // Catch:{ all -> 0x00f1 }
            r10 = 2
            r12[r10] = r4     // Catch:{ all -> 0x00f1 }
            java.lang.String r10 = "font_variation_settings"
            r11 = 3
            r12[r11] = r10     // Catch:{ all -> 0x00f1 }
            r10 = 4
            r12[r10] = r3     // Catch:{ all -> 0x00f1 }
            r10 = 5
            r12[r10] = r2     // Catch:{ all -> 0x00f1 }
            r10 = 6
            r12[r10] = r1     // Catch:{ all -> 0x00f1 }
            android.content.ContentResolver r10 = r18.getContentResolver()     // Catch:{ all -> 0x00f1 }
            java.lang.String r13 = "query = ?"
            java.lang.String[] r14 = new java.lang.String[r15]     // Catch:{ all -> 0x00f1 }
            java.lang.String r11 = r19.getQuery()     // Catch:{ all -> 0x00f1 }
            r14[r9] = r11     // Catch:{ all -> 0x00f1 }
            r16 = 0
            r11 = r8
            r9 = 1
            r15 = r16
            r16 = r21
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x00f1 }
            if (r10 == 0) goto L_0x00e2
            int r11 = r10.getCount()     // Catch:{ all -> 0x00de }
            if (r11 <= 0) goto L_0x00e2
            int r1 = r10.getColumnIndex(r1)     // Catch:{ all -> 0x00de }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x00de }
            r7.<init>()     // Catch:{ all -> 0x00de }
            int r6 = r10.getColumnIndex(r6)     // Catch:{ all -> 0x00de }
            int r5 = r10.getColumnIndex(r5)     // Catch:{ all -> 0x00de }
            int r4 = r10.getColumnIndex(r4)     // Catch:{ all -> 0x00de }
            int r3 = r10.getColumnIndex(r3)     // Catch:{ all -> 0x00de }
            int r2 = r10.getColumnIndex(r2)     // Catch:{ all -> 0x00de }
        L_0x0098:
            boolean r11 = r10.moveToNext()     // Catch:{ all -> 0x00de }
            if (r11 == 0) goto L_0x00e2
            r11 = -1
            if (r1 == r11) goto L_0x00a6
            int r12 = r10.getInt(r1)     // Catch:{ all -> 0x00de }
            goto L_0x00a7
        L_0x00a6:
            r12 = 0
        L_0x00a7:
            if (r4 == r11) goto L_0x00ae
            int r13 = r10.getInt(r4)     // Catch:{ all -> 0x00de }
            goto L_0x00af
        L_0x00ae:
            r13 = 0
        L_0x00af:
            if (r5 != r11) goto L_0x00ba
            long r14 = r10.getLong(r6)     // Catch:{ all -> 0x00de }
            android.net.Uri r14 = android.content.ContentUris.withAppendedId(r8, r14)     // Catch:{ all -> 0x00de }
            goto L_0x00c2
        L_0x00ba:
            long r14 = r10.getLong(r5)     // Catch:{ all -> 0x00de }
            android.net.Uri r14 = android.content.ContentUris.withAppendedId(r0, r14)     // Catch:{ all -> 0x00de }
        L_0x00c2:
            if (r3 == r11) goto L_0x00c9
            int r15 = r10.getInt(r3)     // Catch:{ all -> 0x00de }
            goto L_0x00cb
        L_0x00c9:
            r15 = 400(0x190, float:5.6E-43)
        L_0x00cb:
            if (r2 == r11) goto L_0x00d5
            int r11 = r10.getInt(r2)     // Catch:{ all -> 0x00de }
            if (r11 != r9) goto L_0x00d5
            r11 = 1
            goto L_0x00d6
        L_0x00d5:
            r11 = 0
        L_0x00d6:
            androidx.core.provider.FontsContractCompat$FontInfo r11 = androidx.core.provider.FontsContractCompat.FontInfo.create(r14, r13, r15, r11, r12)     // Catch:{ all -> 0x00de }
            r7.add(r11)     // Catch:{ all -> 0x00de }
            goto L_0x0098
        L_0x00de:
            r0 = move-exception
            r17 = r10
            goto L_0x00f2
        L_0x00e2:
            if (r10 == 0) goto L_0x00e7
            r10.close()
        L_0x00e7:
            r0 = 0
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = new androidx.core.provider.FontsContractCompat.FontInfo[r0]
            java.lang.Object[] r0 = r7.toArray(r0)
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = (androidx.core.provider.FontsContractCompat.FontInfo[]) r0
            return r0
        L_0x00f1:
            r0 = move-exception
        L_0x00f2:
            if (r17 == 0) goto L_0x00f7
            r17.close()
        L_0x00f7:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontProvider.query(android.content.Context, androidx.core.provider.FontRequest, java.lang.String, android.os.CancellationSignal):androidx.core.provider.FontsContractCompat$FontInfo[]");
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature byteArray : signatureArr) {
            arrayList.add(byteArray.toByteArray());
        }
        return arrayList;
    }
}
