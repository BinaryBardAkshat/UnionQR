package com.google.zxing.client.result;

import androidx.core.net.MailTo;
import java.util.List;

public final class VEventResultParser extends ResultParser {
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.client.result.CalendarParsedResult parse(com.google.zxing.Result r17) {
        /*
            r16 = this;
            java.lang.String r0 = getMassagedText(r17)
            java.lang.String r1 = "BEGIN:VEVENT"
            int r1 = r0.indexOf(r1)
            r2 = 0
            if (r1 >= 0) goto L_0x000e
            return r2
        L_0x000e:
            java.lang.String r1 = "SUMMARY"
            java.lang.String r4 = matchSingleVCardPrefixedField(r1, r0)
            java.lang.String r1 = "DTSTART"
            java.lang.String r5 = matchSingleVCardPrefixedField(r1, r0)
            if (r5 != 0) goto L_0x001d
            return r2
        L_0x001d:
            java.lang.String r1 = "DTEND"
            java.lang.String r6 = matchSingleVCardPrefixedField(r1, r0)
            java.lang.String r1 = "DURATION"
            java.lang.String r7 = matchSingleVCardPrefixedField(r1, r0)
            java.lang.String r1 = "LOCATION"
            java.lang.String r8 = matchSingleVCardPrefixedField(r1, r0)
            java.lang.String r1 = "ORGANIZER"
            java.lang.String r1 = matchSingleVCardPrefixedField(r1, r0)
            java.lang.String r9 = stripMailto(r1)
            java.lang.String r1 = "ATTENDEE"
            java.lang.String[] r10 = matchVCardPrefixedField(r1, r0)
            r1 = 0
            if (r10 == 0) goto L_0x0051
            r3 = 0
        L_0x0043:
            int r11 = r10.length
            if (r3 >= r11) goto L_0x0051
            r11 = r10[r3]
            java.lang.String r11 = stripMailto(r11)
            r10[r3] = r11
            int r3 = r3 + 1
            goto L_0x0043
        L_0x0051:
            java.lang.String r3 = "DESCRIPTION"
            java.lang.String r11 = matchSingleVCardPrefixedField(r3, r0)
            java.lang.String r3 = "GEO"
            java.lang.String r0 = matchSingleVCardPrefixedField(r3, r0)
            if (r0 != 0) goto L_0x0064
            r0 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            r12 = r0
            r14 = r12
            goto L_0x0080
        L_0x0064:
            r3 = 59
            int r3 = r0.indexOf(r3)
            if (r3 >= 0) goto L_0x006d
            return r2
        L_0x006d:
            java.lang.String r1 = r0.substring(r1, r3)     // Catch:{ NumberFormatException -> 0x0087 }
            double r12 = java.lang.Double.parseDouble(r1)     // Catch:{ NumberFormatException -> 0x0087 }
            int r3 = r3 + 1
            java.lang.String r0 = r0.substring(r3)     // Catch:{ NumberFormatException -> 0x0087 }
            double r0 = java.lang.Double.parseDouble(r0)     // Catch:{ NumberFormatException -> 0x0087 }
            r14 = r0
        L_0x0080:
            com.google.zxing.client.result.CalendarParsedResult r0 = new com.google.zxing.client.result.CalendarParsedResult     // Catch:{  }
            r3 = r0
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r14)     // Catch:{  }
            return r0
        L_0x0087:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.VEventResultParser.parse(com.google.zxing.Result):com.google.zxing.client.result.CalendarParsedResult");
    }

    private static String matchSingleVCardPrefixedField(CharSequence charSequence, String str) {
        List<String> matchSingleVCardPrefixedField = VCardResultParser.matchSingleVCardPrefixedField(charSequence, str, true, false);
        if (matchSingleVCardPrefixedField == null || matchSingleVCardPrefixedField.isEmpty()) {
            return null;
        }
        return matchSingleVCardPrefixedField.get(0);
    }

    private static String[] matchVCardPrefixedField(CharSequence charSequence, String str) {
        List<List<String>> matchVCardPrefixedField = VCardResultParser.matchVCardPrefixedField(charSequence, str, true, false);
        if (matchVCardPrefixedField == null || matchVCardPrefixedField.isEmpty()) {
            return null;
        }
        int size = matchVCardPrefixedField.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = (String) matchVCardPrefixedField.get(i).get(0);
        }
        return strArr;
    }

    private static String stripMailto(String str) {
        if (str != null) {
            return (str.startsWith(MailTo.MAILTO_SCHEME) || str.startsWith("MAILTO:")) ? str.substring(7) : str;
        }
        return str;
    }
}
