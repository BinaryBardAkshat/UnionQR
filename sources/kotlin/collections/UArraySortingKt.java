package kotlin.collections;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0012\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\bH\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000bH\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "sortArray-GBYM_sE", "([B)V", "sortArray--ajY-9A", "([I)V", "sortArray-QwZRm1k", "([J)V", "sortArray-rL5Bavg", "([S)V", "kotlin-stdlib"}, k = 2, mv = {1, 1, 16})
/* compiled from: UArraySorting.kt */
public final class UArraySortingKt {
    /* renamed from: partition-4UcCI2c  reason: not valid java name */
    private static final int m357partition4UcCI2c(byte[] bArr, int i, int i2) {
        byte b;
        byte b2 = UByteArray.m104getimpl(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                byte b3 = UByteArray.m104getimpl(bArr, i) & UByte.MAX_VALUE;
                b = b2 & UByte.MAX_VALUE;
                if (Intrinsics.compare((int) b3, (int) b) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare((int) UByteArray.m104getimpl(bArr, i2) & UByte.MAX_VALUE, (int) b) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte b4 = UByteArray.m104getimpl(bArr, i);
                UByteArray.m109setVurrAj0(bArr, i, UByteArray.m104getimpl(bArr, i2));
                UByteArray.m109setVurrAj0(bArr, i2, b4);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-4UcCI2c  reason: not valid java name */
    private static final void m361quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int r0 = m357partition4UcCI2c(bArr, i, i2);
        int i3 = r0 - 1;
        if (i < i3) {
            m361quickSort4UcCI2c(bArr, i, i3);
        }
        if (r0 < i2) {
            m361quickSort4UcCI2c(bArr, r0, i2);
        }
    }

    /* renamed from: partition-Aa5vz7o  reason: not valid java name */
    private static final int m358partitionAa5vz7o(short[] sArr, int i, int i2) {
        short s;
        short s2 = UShortArray.m337getimpl(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                short s3 = UShortArray.m337getimpl(sArr, i) & UShort.MAX_VALUE;
                s = s2 & UShort.MAX_VALUE;
                if (Intrinsics.compare((int) s3, (int) s) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare((int) UShortArray.m337getimpl(sArr, i2) & UShort.MAX_VALUE, (int) s) > 0) {
                i2--;
            }
            if (i <= i2) {
                short s4 = UShortArray.m337getimpl(sArr, i);
                UShortArray.m342set01HTLdE(sArr, i, UShortArray.m337getimpl(sArr, i2));
                UShortArray.m342set01HTLdE(sArr, i2, s4);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-Aa5vz7o  reason: not valid java name */
    private static final void m362quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int r0 = m358partitionAa5vz7o(sArr, i, i2);
        int i3 = r0 - 1;
        if (i < i3) {
            m362quickSortAa5vz7o(sArr, i, i3);
        }
        if (r0 < i2) {
            m362quickSortAa5vz7o(sArr, r0, i2);
        }
    }

    /* renamed from: partition-oBK06Vg  reason: not valid java name */
    private static final int m359partitionoBK06Vg(int[] iArr, int i, int i2) {
        int i3 = UIntArray.m173getimpl(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.uintCompare(UIntArray.m173getimpl(iArr, i), i3) < 0) {
                i++;
            }
            while (UnsignedKt.uintCompare(UIntArray.m173getimpl(iArr, i2), i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                int i4 = UIntArray.m173getimpl(iArr, i);
                UIntArray.m178setVXSXFK8(iArr, i, UIntArray.m173getimpl(iArr, i2));
                UIntArray.m178setVXSXFK8(iArr, i2, i4);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-oBK06Vg  reason: not valid java name */
    private static final void m363quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int r0 = m359partitionoBK06Vg(iArr, i, i2);
        int i3 = r0 - 1;
        if (i < i3) {
            m363quickSortoBK06Vg(iArr, i, i3);
        }
        if (r0 < i2) {
            m363quickSortoBK06Vg(iArr, r0, i2);
        }
    }

    /* renamed from: partition--nroSd4  reason: not valid java name */
    private static final int m356partitionnroSd4(long[] jArr, int i, int i2) {
        long j = ULongArray.m242getimpl(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.ulongCompare(ULongArray.m242getimpl(jArr, i), j) < 0) {
                i++;
            }
            while (UnsignedKt.ulongCompare(ULongArray.m242getimpl(jArr, i2), j) > 0) {
                i2--;
            }
            if (i <= i2) {
                long j2 = ULongArray.m242getimpl(jArr, i);
                ULongArray.m247setk8EXiF4(jArr, i, ULongArray.m242getimpl(jArr, i2));
                ULongArray.m247setk8EXiF4(jArr, i2, j2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort--nroSd4  reason: not valid java name */
    private static final void m360quickSortnroSd4(long[] jArr, int i, int i2) {
        int r0 = m356partitionnroSd4(jArr, i, i2);
        int i3 = r0 - 1;
        if (i < i3) {
            m360quickSortnroSd4(jArr, i, i3);
        }
        if (r0 < i2) {
            m360quickSortnroSd4(jArr, r0, i2);
        }
    }

    /* renamed from: sortArray-GBYM_sE  reason: not valid java name */
    public static final void m365sortArrayGBYM_sE(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "array");
        m361quickSort4UcCI2c(bArr, 0, UByteArray.m105getSizeimpl(bArr) - 1);
    }

    /* renamed from: sortArray-rL5Bavg  reason: not valid java name */
    public static final void m367sortArrayrL5Bavg(short[] sArr) {
        Intrinsics.checkParameterIsNotNull(sArr, "array");
        m362quickSortAa5vz7o(sArr, 0, UShortArray.m338getSizeimpl(sArr) - 1);
    }

    /* renamed from: sortArray--ajY-9A  reason: not valid java name */
    public static final void m364sortArrayajY9A(int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "array");
        m363quickSortoBK06Vg(iArr, 0, UIntArray.m174getSizeimpl(iArr) - 1);
    }

    /* renamed from: sortArray-QwZRm1k  reason: not valid java name */
    public static final void m366sortArrayQwZRm1k(long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, "array");
        m360quickSortnroSd4(jArr, 0, ULongArray.m243getSizeimpl(jArr) - 1);
    }
}
