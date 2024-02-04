package com.google.zxing.client.result;

public final class ExpandedProductResultParser extends ResultParser {
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x020e, code lost:
        if (r1.equals("10") == false) goto L_0x0052;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.client.result.ExpandedProductParsedResult parse(com.google.zxing.Result r24) {
        /*
            r23 = this;
            com.google.zxing.BarcodeFormat r0 = r24.getBarcodeFormat()
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.RSS_EXPANDED
            r2 = 0
            if (r0 == r1) goto L_0x000a
            return r2
        L_0x000a:
            java.lang.String r4 = getMassagedText(r24)
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r5 = r2
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r16 = r15
            r17 = r16
            r3 = 0
        L_0x0023:
            int r1 = r4.length()
            if (r3 >= r1) goto L_0x0280
            java.lang.String r1 = findAIvalue(r3, r4)
            if (r1 != 0) goto L_0x0030
            return r2
        L_0x0030:
            int r18 = r1.length()
            r19 = 2
            int r18 = r18 + 2
            int r3 = r3 + r18
            java.lang.String r2 = findValue(r3, r4)
            int r20 = r2.length()
            int r3 = r3 + r20
            r1.hashCode()
            int r20 = r1.hashCode()
            r21 = r3
            r22 = -1
            switch(r20) {
                case 1536: goto L_0x021f;
                case 1537: goto L_0x0212;
                case 1567: goto L_0x0208;
                case 1568: goto L_0x01fb;
                case 1570: goto L_0x01ee;
                case 1572: goto L_0x01e1;
                case 1574: goto L_0x01d4;
                case 1567966: goto L_0x01c7;
                case 1567967: goto L_0x01b9;
                case 1567968: goto L_0x01ab;
                case 1567969: goto L_0x019d;
                case 1567970: goto L_0x018f;
                case 1567971: goto L_0x0181;
                case 1567972: goto L_0x0173;
                case 1567973: goto L_0x0165;
                case 1567974: goto L_0x0157;
                case 1567975: goto L_0x0149;
                case 1568927: goto L_0x013b;
                case 1568928: goto L_0x012d;
                case 1568929: goto L_0x011f;
                case 1568930: goto L_0x0111;
                case 1568931: goto L_0x0103;
                case 1568932: goto L_0x00f5;
                case 1568933: goto L_0x00e7;
                case 1568934: goto L_0x00d9;
                case 1568935: goto L_0x00cb;
                case 1568936: goto L_0x00be;
                case 1575716: goto L_0x00b1;
                case 1575717: goto L_0x00a4;
                case 1575718: goto L_0x0097;
                case 1575719: goto L_0x008a;
                case 1575747: goto L_0x007d;
                case 1575748: goto L_0x0070;
                case 1575749: goto L_0x0063;
                case 1575750: goto L_0x0056;
                default: goto L_0x0052;
            }
        L_0x0052:
            r19 = -1
            goto L_0x022b
        L_0x0056:
            java.lang.String r3 = "3933"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x005f
            goto L_0x0052
        L_0x005f:
            r19 = 34
            goto L_0x022b
        L_0x0063:
            java.lang.String r3 = "3932"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x006c
            goto L_0x0052
        L_0x006c:
            r19 = 33
            goto L_0x022b
        L_0x0070:
            java.lang.String r3 = "3931"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0079
            goto L_0x0052
        L_0x0079:
            r19 = 32
            goto L_0x022b
        L_0x007d:
            java.lang.String r3 = "3930"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0086
            goto L_0x0052
        L_0x0086:
            r19 = 31
            goto L_0x022b
        L_0x008a:
            java.lang.String r3 = "3923"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0093
            goto L_0x0052
        L_0x0093:
            r19 = 30
            goto L_0x022b
        L_0x0097:
            java.lang.String r3 = "3922"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x00a0
            goto L_0x0052
        L_0x00a0:
            r19 = 29
            goto L_0x022b
        L_0x00a4:
            java.lang.String r3 = "3921"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x00ad
            goto L_0x0052
        L_0x00ad:
            r19 = 28
            goto L_0x022b
        L_0x00b1:
            java.lang.String r3 = "3920"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x00ba
            goto L_0x0052
        L_0x00ba:
            r19 = 27
            goto L_0x022b
        L_0x00be:
            java.lang.String r3 = "3209"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x00c7
            goto L_0x0052
        L_0x00c7:
            r19 = 26
            goto L_0x022b
        L_0x00cb:
            java.lang.String r3 = "3208"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x00d5
            goto L_0x0052
        L_0x00d5:
            r19 = 25
            goto L_0x022b
        L_0x00d9:
            java.lang.String r3 = "3207"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x00e3
            goto L_0x0052
        L_0x00e3:
            r19 = 24
            goto L_0x022b
        L_0x00e7:
            java.lang.String r3 = "3206"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x00f1
            goto L_0x0052
        L_0x00f1:
            r19 = 23
            goto L_0x022b
        L_0x00f5:
            java.lang.String r3 = "3205"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x00ff
            goto L_0x0052
        L_0x00ff:
            r19 = 22
            goto L_0x022b
        L_0x0103:
            java.lang.String r3 = "3204"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x010d
            goto L_0x0052
        L_0x010d:
            r19 = 21
            goto L_0x022b
        L_0x0111:
            java.lang.String r3 = "3203"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x011b
            goto L_0x0052
        L_0x011b:
            r19 = 20
            goto L_0x022b
        L_0x011f:
            java.lang.String r3 = "3202"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0129
            goto L_0x0052
        L_0x0129:
            r19 = 19
            goto L_0x022b
        L_0x012d:
            java.lang.String r3 = "3201"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0137
            goto L_0x0052
        L_0x0137:
            r19 = 18
            goto L_0x022b
        L_0x013b:
            java.lang.String r3 = "3200"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0145
            goto L_0x0052
        L_0x0145:
            r19 = 17
            goto L_0x022b
        L_0x0149:
            java.lang.String r3 = "3109"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0153
            goto L_0x0052
        L_0x0153:
            r19 = 16
            goto L_0x022b
        L_0x0157:
            java.lang.String r3 = "3108"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0161
            goto L_0x0052
        L_0x0161:
            r19 = 15
            goto L_0x022b
        L_0x0165:
            java.lang.String r3 = "3107"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x016f
            goto L_0x0052
        L_0x016f:
            r19 = 14
            goto L_0x022b
        L_0x0173:
            java.lang.String r3 = "3106"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x017d
            goto L_0x0052
        L_0x017d:
            r19 = 13
            goto L_0x022b
        L_0x0181:
            java.lang.String r3 = "3105"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x018b
            goto L_0x0052
        L_0x018b:
            r19 = 12
            goto L_0x022b
        L_0x018f:
            java.lang.String r3 = "3104"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0199
            goto L_0x0052
        L_0x0199:
            r19 = 11
            goto L_0x022b
        L_0x019d:
            java.lang.String r3 = "3103"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x01a7
            goto L_0x0052
        L_0x01a7:
            r19 = 10
            goto L_0x022b
        L_0x01ab:
            java.lang.String r3 = "3102"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x01b5
            goto L_0x0052
        L_0x01b5:
            r19 = 9
            goto L_0x022b
        L_0x01b9:
            java.lang.String r3 = "3101"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x01c3
            goto L_0x0052
        L_0x01c3:
            r19 = 8
            goto L_0x022b
        L_0x01c7:
            java.lang.String r3 = "3100"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x01d1
            goto L_0x0052
        L_0x01d1:
            r19 = 7
            goto L_0x022b
        L_0x01d4:
            java.lang.String r3 = "17"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x01de
            goto L_0x0052
        L_0x01de:
            r19 = 6
            goto L_0x022b
        L_0x01e1:
            java.lang.String r3 = "15"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x01eb
            goto L_0x0052
        L_0x01eb:
            r19 = 5
            goto L_0x022b
        L_0x01ee:
            java.lang.String r3 = "13"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x01f8
            goto L_0x0052
        L_0x01f8:
            r19 = 4
            goto L_0x022b
        L_0x01fb:
            java.lang.String r3 = "11"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0205
            goto L_0x0052
        L_0x0205:
            r19 = 3
            goto L_0x022b
        L_0x0208:
            java.lang.String r3 = "10"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x022b
            goto L_0x0052
        L_0x0212:
            java.lang.String r3 = "01"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x021c
            goto L_0x0052
        L_0x021c:
            r19 = 1
            goto L_0x022b
        L_0x021f:
            java.lang.String r3 = "00"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0229
            goto L_0x0052
        L_0x0229:
            r19 = 0
        L_0x022b:
            switch(r19) {
                case 0: goto L_0x027a;
                case 1: goto L_0x0278;
                case 2: goto L_0x0276;
                case 3: goto L_0x0274;
                case 4: goto L_0x0272;
                case 5: goto L_0x0270;
                case 6: goto L_0x026e;
                case 7: goto L_0x0264;
                case 8: goto L_0x0264;
                case 9: goto L_0x0264;
                case 10: goto L_0x0264;
                case 11: goto L_0x0264;
                case 12: goto L_0x0264;
                case 13: goto L_0x0264;
                case 14: goto L_0x0264;
                case 15: goto L_0x0264;
                case 16: goto L_0x0264;
                case 17: goto L_0x025b;
                case 18: goto L_0x025b;
                case 19: goto L_0x025b;
                case 20: goto L_0x025b;
                case 21: goto L_0x025b;
                case 22: goto L_0x025b;
                case 23: goto L_0x025b;
                case 24: goto L_0x025b;
                case 25: goto L_0x025b;
                case 26: goto L_0x025b;
                case 27: goto L_0x0253;
                case 28: goto L_0x0253;
                case 29: goto L_0x0253;
                case 30: goto L_0x0253;
                case 31: goto L_0x0232;
                case 32: goto L_0x0232;
                case 33: goto L_0x0232;
                case 34: goto L_0x0232;
                default: goto L_0x022e;
            }
        L_0x022e:
            r0.put(r1, r2)
            goto L_0x027b
        L_0x0232:
            int r3 = r2.length()
            r15 = 4
            if (r3 >= r15) goto L_0x023b
            r3 = 0
            return r3
        L_0x023b:
            r3 = 0
            r15 = 3
            java.lang.String r16 = r2.substring(r15)
            r3 = 0
            java.lang.String r17 = r2.substring(r3, r15)
            java.lang.String r1 = r1.substring(r15)
            r15 = r16
            r3 = r21
            r2 = 0
            r16 = r1
            goto L_0x0023
        L_0x0253:
            r3 = 0
            r15 = 3
            java.lang.String r16 = r1.substring(r15)
            r15 = r2
            goto L_0x027b
        L_0x025b:
            r3 = 0
            r12 = 3
            java.lang.String r14 = r1.substring(r12)
            java.lang.String r13 = "LB"
            goto L_0x026c
        L_0x0264:
            r3 = 0
            r12 = 3
            java.lang.String r14 = r1.substring(r12)
            java.lang.String r13 = "KG"
        L_0x026c:
            r12 = r2
            goto L_0x027b
        L_0x026e:
            r11 = r2
            goto L_0x027b
        L_0x0270:
            r10 = r2
            goto L_0x027b
        L_0x0272:
            r9 = r2
            goto L_0x027b
        L_0x0274:
            r8 = r2
            goto L_0x027b
        L_0x0276:
            r7 = r2
            goto L_0x027b
        L_0x0278:
            r5 = r2
            goto L_0x027b
        L_0x027a:
            r6 = r2
        L_0x027b:
            r3 = r21
            r2 = 0
            goto L_0x0023
        L_0x0280:
            com.google.zxing.client.result.ExpandedProductParsedResult r1 = new com.google.zxing.client.result.ExpandedProductParsedResult
            r3 = r1
            r18 = r0
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.ExpandedProductResultParser.parse(com.google.zxing.Result):com.google.zxing.client.result.ExpandedProductParsedResult");
    }

    private static String findAIvalue(int i, String str) {
        if (str.charAt(i) != '(') {
            return null;
        }
        String substring = str.substring(i + 1);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt == ')') {
                return sb.toString();
            }
            if (charAt < '0' || charAt > '9') {
                return null;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    private static String findValue(int i, String str) {
        StringBuilder sb = new StringBuilder();
        String substring = str.substring(i);
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt == '(') {
                if (findAIvalue(i2, substring) != null) {
                    break;
                }
                sb.append('(');
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }
}
