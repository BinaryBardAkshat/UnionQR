package com.google.android.filament;

import java.nio.Buffer;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

public class Texture {
    public static final int BASE_LEVEL = 0;
    private long mNativeObject;

    public enum CompressedFormat {
        EAC_R11,
        EAC_R11_SIGNED,
        EAC_RG11,
        EAC_RG11_SIGNED,
        ETC2_RGB8,
        ETC2_SRGB8,
        ETC2_RGB8_A1,
        ETC2_SRGB8_A1,
        ETC2_EAC_RGBA8,
        ETC2_EAC_SRGBA8,
        DXT1_RGB,
        DXT1_RGBA,
        DXT3_RGBA,
        DXT5_RGBA
    }

    public enum CubemapFace {
        POSITIVE_X,
        NEGATIVE_X,
        POSITIVE_Y,
        NEGATIVE_Y,
        POSITIVE_Z,
        NEGATIVE_Z
    }

    public enum Format {
        R,
        R_INTEGER,
        RG,
        RG_INTEGER,
        RGB,
        RGB_INTEGER,
        RGBA,
        RGBA_INTEGER,
        UNUSED,
        DEPTH_COMPONENT,
        DEPTH_STENCIL,
        STENCIL_INDEX,
        ALPHA
    }

    public enum InternalFormat {
        R8,
        R8_SNORM,
        R8UI,
        R8I,
        STENCIL8,
        R16F,
        R16UI,
        R16I,
        RG8,
        RG8_SNORM,
        RG8UI,
        RG8I,
        RGB565,
        RGB9_E5,
        RGB5_A1,
        RGBA4,
        DEPTH16,
        RGB8,
        SRGB8,
        RGB8_SNORM,
        RGB8UI,
        RGB8I,
        DEPTH24,
        R32F,
        R32UI,
        R32I,
        RG16F,
        RG16UI,
        RG16I,
        R11F_G11F_B10F,
        RGBA8,
        SRGB8_A8,
        RGBA8_SNORM,
        UNUSED,
        RGB10_A2,
        RGBA8UI,
        RGBA8I,
        DEPTH32F,
        DEPTH24_STENCIL8,
        DEPTH32F_STENCIL8,
        RGB16F,
        RGB16UI,
        RGB16I,
        RG32F,
        RG32UI,
        RG32I,
        RGBA16F,
        RGBA16UI,
        RGBA16I,
        RGB32F,
        RGB32UI,
        RGB32I,
        RGBA32F,
        RGBA32UI,
        RGBA32I,
        EAC_R11,
        EAC_R11_SIGNED,
        EAC_RG11,
        EAC_RG11_SIGNED,
        ETC2_RGB8,
        ETC2_SRGB8,
        ETC2_RGB8_A1,
        ETC2_SRGB8_A1,
        ETC2_EAC_RGBA8,
        ETC2_EAC_SRGBA8,
        DXT1_RGB,
        DXT1_RGBA,
        DXT3_RGBA,
        DXT5_RGBA
    }

    public static class PrefilterOptions {
        public boolean mirror = true;
        public int sampleCount = 8;
    }

    public enum Sampler {
        SAMPLER_2D,
        SAMPLER_CUBEMAP,
        SAMPLER_EXTERNAL
    }

    public enum Type {
        UBYTE,
        BYTE,
        USHORT,
        SHORT,
        UINT,
        INT,
        HALF,
        FLOAT,
        COMPRESSED,
        UINT_10F_11F_11F_REV
    }

    public static class Usage {
        public static final int COLOR_ATTACHMENT = 1;
        public static final int DEFAULT = 24;
        public static final int DEPTH_ATTACHMENT = 2;
        public static final int SAMPLEABLE = 16;
        public static final int STENCIL_ATTACHMENT = 4;
        public static final int UPLOADABLE = 8;
    }

    /* access modifiers changed from: private */
    public static native long nBuilderBuild(long j, long j2);

    /* access modifiers changed from: private */
    public static native void nBuilderDepth(long j, int i);

    /* access modifiers changed from: private */
    public static native void nBuilderFormat(long j, int i);

    /* access modifiers changed from: private */
    public static native void nBuilderHeight(long j, int i);

    /* access modifiers changed from: private */
    public static native void nBuilderLevels(long j, int i);

    /* access modifiers changed from: private */
    public static native void nBuilderSampler(long j, int i);

    /* access modifiers changed from: private */
    public static native void nBuilderUsage(long j, int i);

    /* access modifiers changed from: private */
    public static native void nBuilderWidth(long j, int i);

    /* access modifiers changed from: private */
    public static native long nCreateBuilder();

    /* access modifiers changed from: private */
    public static native void nDestroyBuilder(long j);

    private static native void nGenerateMipmaps(long j, long j2);

    private static native int nGeneratePrefilterMipmap(long j, long j2, int i, int i2, Buffer buffer, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int[] iArr, Object obj, Runnable runnable, int i10, boolean z);

    private static native int nGetDepth(long j, int i);

    private static native int nGetHeight(long j, int i);

    private static native int nGetInternalFormat(long j);

    private static native int nGetLevels(long j);

    private static native int nGetTarget(long j);

    private static native int nGetWidth(long j, int i);

    private static native boolean nIsStreamValidForTexture(long j, long j2);

    private static native boolean nIsTextureFormatSupported(long j, int i);

    private static native void nSetExternalImage(long j, long j2, long j3);

    private static native void nSetExternalStream(long j, long j2, long j3);

    private static native int nSetImage(long j, long j2, int i, int i2, int i3, int i4, int i5, Buffer buffer, int i6, int i7, int i8, int i9, int i10, int i11, int i12, Object obj, Runnable runnable);

    private static native int nSetImageCompressed(long j, long j2, int i, int i2, int i3, int i4, int i5, Buffer buffer, int i6, int i7, int i8, int i9, int i10, int i11, int i12, Object obj, Runnable runnable);

    private static native int nSetImageCubemap(long j, long j2, int i, Buffer buffer, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int[] iArr, Object obj, Runnable runnable);

    private static native int nSetImageCubemapCompressed(long j, long j2, int i, Buffer buffer, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int[] iArr, Object obj, Runnable runnable);

    Texture(long j) {
        this.mNativeObject = j;
    }

    public static class PixelBufferDescriptor {
        public int alignment;
        public Runnable callback;
        public CompressedFormat compressedFormat;
        public int compressedSizeInBytes;
        public Format format;
        public Object handler;
        public int left;
        public Buffer storage;
        public int stride;
        public int top;
        public Type type;

        public PixelBufferDescriptor(Buffer buffer, Format format2, Type type2, int i, int i2, int i3, int i4, Object obj, Runnable runnable) {
            this.storage = buffer;
            this.left = i2;
            this.top = i3;
            this.type = type2;
            this.alignment = i;
            this.stride = i4;
            this.format = format2;
            this.handler = obj;
            this.callback = runnable;
        }

        public PixelBufferDescriptor(Buffer buffer, Format format2, Type type2) {
            this(buffer, format2, type2, 1, 0, 0, 0, (Object) null, (Runnable) null);
        }

        public PixelBufferDescriptor(Buffer buffer, Format format2, Type type2, int i) {
            this(buffer, format2, type2, i, 0, 0, 0, (Object) null, (Runnable) null);
        }

        public PixelBufferDescriptor(Buffer buffer, Format format2, Type type2, int i, int i2, int i3) {
            this(buffer, format2, type2, i, i2, i3, 0, (Object) null, (Runnable) null);
        }

        public PixelBufferDescriptor(ByteBuffer byteBuffer, CompressedFormat compressedFormat2, int i) {
            this.alignment = 1;
            this.left = 0;
            this.top = 0;
            this.stride = 0;
            this.storage = byteBuffer;
            this.type = Type.COMPRESSED;
            this.alignment = 1;
            this.compressedFormat = compressedFormat2;
            this.compressedSizeInBytes = i;
        }

        public void setCallback(Object obj, Runnable runnable) {
            this.handler = obj;
            this.callback = runnable;
        }

        static int computeDataSize(Format format2, Type type2, int i, int i2, int i3) {
            int i4 = 0;
            if (type2 == Type.COMPRESSED) {
                return 0;
            }
            int i5 = 4;
            switch (AnonymousClass1.$SwitchMap$com$google$android$filament$Texture$Format[format2.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                    i4 = 1;
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                    i4 = 2;
                    break;
                case 9:
                case 10:
                    i4 = 3;
                    break;
                case 11:
                case 12:
                    i4 = 4;
                    break;
            }
            switch (type2) {
                case USHORT:
                case SHORT:
                case HALF:
                    i5 = i4 * 2;
                    break;
                case UINT:
                case INT:
                case FLOAT:
                    i5 = i4 * 4;
                    break;
                case UINT_10F_11F_11F_REV:
                    break;
                default:
                    i5 = i4;
                    break;
            }
            return ((-i3) & ((i5 * i) + (i3 - 1))) * i2;
        }
    }

    /* renamed from: com.google.android.filament.Texture$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$filament$Texture$Format;

        /* JADX WARNING: Can't wrap try/catch for region: R(46:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|21|22|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|62) */
        /* JADX WARNING: Can't wrap try/catch for region: R(49:0|(2:1|2)|3|5|6|7|9|10|11|(2:13|14)|15|17|18|19|21|22|23|(2:25|26)|27|29|30|31|(2:33|34)|35|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|62) */
        /* JADX WARNING: Can't wrap try/catch for region: R(52:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|(2:33|34)|35|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|62) */
        /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x007d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x0087 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x0091 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x009b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00a5 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00af */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00b9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00c3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x00cd */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x00d9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x00e5 */
        static {
            /*
                com.google.android.filament.Texture$Type[] r0 = com.google.android.filament.Texture.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$android$filament$Texture$Type = r0
                r1 = 1
                com.google.android.filament.Texture$Type r2 = com.google.android.filament.Texture.Type.UBYTE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$google$android$filament$Texture$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.android.filament.Texture$Type r3 = com.google.android.filament.Texture.Type.BYTE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$google$android$filament$Texture$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.android.filament.Texture$Type r4 = com.google.android.filament.Texture.Type.USHORT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$google$android$filament$Texture$Type     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.android.filament.Texture$Type r5 = com.google.android.filament.Texture.Type.SHORT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$com$google$android$filament$Texture$Type     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.android.filament.Texture$Type r6 = com.google.android.filament.Texture.Type.HALF     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                r5 = 6
                int[] r6 = $SwitchMap$com$google$android$filament$Texture$Type     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.android.filament.Texture$Type r7 = com.google.android.filament.Texture.Type.UINT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                r6 = 7
                int[] r7 = $SwitchMap$com$google$android$filament$Texture$Type     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.android.filament.Texture$Type r8 = com.google.android.filament.Texture.Type.INT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r7[r8] = r6     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                r7 = 8
                int[] r8 = $SwitchMap$com$google$android$filament$Texture$Type     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.android.filament.Texture$Type r9 = com.google.android.filament.Texture.Type.FLOAT     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r8[r9] = r7     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                r8 = 9
                int[] r9 = $SwitchMap$com$google$android$filament$Texture$Type     // Catch:{ NoSuchFieldError -> 0x006c }
                com.google.android.filament.Texture$Type r10 = com.google.android.filament.Texture.Type.UINT_10F_11F_11F_REV     // Catch:{ NoSuchFieldError -> 0x006c }
                int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r9[r10] = r8     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                com.google.android.filament.Texture$Format[] r9 = com.google.android.filament.Texture.Format.values()
                int r9 = r9.length
                int[] r9 = new int[r9]
                $SwitchMap$com$google$android$filament$Texture$Format = r9
                com.google.android.filament.Texture$Format r10 = com.google.android.filament.Texture.Format.R     // Catch:{ NoSuchFieldError -> 0x007d }
                int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x007d }
                r9[r10] = r1     // Catch:{ NoSuchFieldError -> 0x007d }
            L_0x007d:
                int[] r1 = $SwitchMap$com$google$android$filament$Texture$Format     // Catch:{ NoSuchFieldError -> 0x0087 }
                com.google.android.filament.Texture$Format r9 = com.google.android.filament.Texture.Format.R_INTEGER     // Catch:{ NoSuchFieldError -> 0x0087 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0087 }
                r1[r9] = r0     // Catch:{ NoSuchFieldError -> 0x0087 }
            L_0x0087:
                int[] r0 = $SwitchMap$com$google$android$filament$Texture$Format     // Catch:{ NoSuchFieldError -> 0x0091 }
                com.google.android.filament.Texture$Format r1 = com.google.android.filament.Texture.Format.DEPTH_COMPONENT     // Catch:{ NoSuchFieldError -> 0x0091 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0091 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0091 }
            L_0x0091:
                int[] r0 = $SwitchMap$com$google$android$filament$Texture$Format     // Catch:{ NoSuchFieldError -> 0x009b }
                com.google.android.filament.Texture$Format r1 = com.google.android.filament.Texture.Format.ALPHA     // Catch:{ NoSuchFieldError -> 0x009b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009b }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x009b }
            L_0x009b:
                int[] r0 = $SwitchMap$com$google$android$filament$Texture$Format     // Catch:{ NoSuchFieldError -> 0x00a5 }
                com.google.android.filament.Texture$Format r1 = com.google.android.filament.Texture.Format.RG     // Catch:{ NoSuchFieldError -> 0x00a5 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a5 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x00a5 }
            L_0x00a5:
                int[] r0 = $SwitchMap$com$google$android$filament$Texture$Format     // Catch:{ NoSuchFieldError -> 0x00af }
                com.google.android.filament.Texture$Format r1 = com.google.android.filament.Texture.Format.RG_INTEGER     // Catch:{ NoSuchFieldError -> 0x00af }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00af }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x00af }
            L_0x00af:
                int[] r0 = $SwitchMap$com$google$android$filament$Texture$Format     // Catch:{ NoSuchFieldError -> 0x00b9 }
                com.google.android.filament.Texture$Format r1 = com.google.android.filament.Texture.Format.DEPTH_STENCIL     // Catch:{ NoSuchFieldError -> 0x00b9 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b9 }
                r0[r1] = r6     // Catch:{ NoSuchFieldError -> 0x00b9 }
            L_0x00b9:
                int[] r0 = $SwitchMap$com$google$android$filament$Texture$Format     // Catch:{ NoSuchFieldError -> 0x00c3 }
                com.google.android.filament.Texture$Format r1 = com.google.android.filament.Texture.Format.STENCIL_INDEX     // Catch:{ NoSuchFieldError -> 0x00c3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c3 }
                r0[r1] = r7     // Catch:{ NoSuchFieldError -> 0x00c3 }
            L_0x00c3:
                int[] r0 = $SwitchMap$com$google$android$filament$Texture$Format     // Catch:{ NoSuchFieldError -> 0x00cd }
                com.google.android.filament.Texture$Format r1 = com.google.android.filament.Texture.Format.RGB     // Catch:{ NoSuchFieldError -> 0x00cd }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cd }
                r0[r1] = r8     // Catch:{ NoSuchFieldError -> 0x00cd }
            L_0x00cd:
                int[] r0 = $SwitchMap$com$google$android$filament$Texture$Format     // Catch:{ NoSuchFieldError -> 0x00d9 }
                com.google.android.filament.Texture$Format r1 = com.google.android.filament.Texture.Format.RGB_INTEGER     // Catch:{ NoSuchFieldError -> 0x00d9 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d9 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d9 }
            L_0x00d9:
                int[] r0 = $SwitchMap$com$google$android$filament$Texture$Format     // Catch:{ NoSuchFieldError -> 0x00e5 }
                com.google.android.filament.Texture$Format r1 = com.google.android.filament.Texture.Format.RGBA     // Catch:{ NoSuchFieldError -> 0x00e5 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e5 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e5 }
            L_0x00e5:
                int[] r0 = $SwitchMap$com$google$android$filament$Texture$Format     // Catch:{ NoSuchFieldError -> 0x00f1 }
                com.google.android.filament.Texture$Format r1 = com.google.android.filament.Texture.Format.RGBA_INTEGER     // Catch:{ NoSuchFieldError -> 0x00f1 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f1 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00f1 }
            L_0x00f1:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.filament.Texture.AnonymousClass1.<clinit>():void");
        }
    }

    public static boolean isTextureFormatSupported(Engine engine, InternalFormat internalFormat) {
        return nIsTextureFormatSupported(engine.getNativeObject(), internalFormat.ordinal());
    }

    public static class Builder {
        private final BuilderFinalizer mFinalizer;
        private final long mNativeBuilder;

        public Builder() {
            long access$000 = Texture.nCreateBuilder();
            this.mNativeBuilder = access$000;
            this.mFinalizer = new BuilderFinalizer(access$000);
        }

        public Builder width(int i) {
            Texture.nBuilderWidth(this.mNativeBuilder, i);
            return this;
        }

        public Builder height(int i) {
            Texture.nBuilderHeight(this.mNativeBuilder, i);
            return this;
        }

        public Builder depth(int i) {
            Texture.nBuilderDepth(this.mNativeBuilder, i);
            return this;
        }

        public Builder levels(int i) {
            Texture.nBuilderLevels(this.mNativeBuilder, i);
            return this;
        }

        public Builder sampler(Sampler sampler) {
            Texture.nBuilderSampler(this.mNativeBuilder, sampler.ordinal());
            return this;
        }

        public Builder format(InternalFormat internalFormat) {
            Texture.nBuilderFormat(this.mNativeBuilder, internalFormat.ordinal());
            return this;
        }

        public Builder usage(int i) {
            Texture.nBuilderUsage(this.mNativeBuilder, i);
            return this;
        }

        public Texture build(Engine engine) {
            long access$800 = Texture.nBuilderBuild(this.mNativeBuilder, engine.getNativeObject());
            if (access$800 != 0) {
                return new Texture(access$800);
            }
            throw new IllegalStateException("Couldn't create Texture");
        }

        private static class BuilderFinalizer {
            private final long mNativeObject;

            BuilderFinalizer(long j) {
                this.mNativeObject = j;
            }

            public void finalize() {
                try {
                    super.finalize();
                } catch (Throwable unused) {
                }
                Texture.nDestroyBuilder(this.mNativeObject);
            }
        }
    }

    public int getWidth(int i) {
        return nGetWidth(getNativeObject(), i);
    }

    public int getHeight(int i) {
        return nGetHeight(getNativeObject(), i);
    }

    public int getDepth(int i) {
        return nGetDepth(getNativeObject(), i);
    }

    public int getLevels() {
        return nGetLevels(getNativeObject());
    }

    public Sampler getTarget() {
        return Sampler.values()[nGetTarget(getNativeObject())];
    }

    public InternalFormat getFormat() {
        return InternalFormat.values()[nGetInternalFormat(getNativeObject())];
    }

    public void setImage(Engine engine, int i, PixelBufferDescriptor pixelBufferDescriptor) {
        setImage(engine, i, 0, 0, getWidth(i), getHeight(i), pixelBufferDescriptor);
    }

    public void setImage(Engine engine, int i, int i2, int i3, int i4, int i5, PixelBufferDescriptor pixelBufferDescriptor) {
        int i6;
        PixelBufferDescriptor pixelBufferDescriptor2 = pixelBufferDescriptor;
        if (pixelBufferDescriptor2.type == Type.COMPRESSED) {
            i6 = nSetImageCompressed(getNativeObject(), engine.getNativeObject(), i, i2, i3, i4, i5, pixelBufferDescriptor2.storage, pixelBufferDescriptor2.storage.remaining(), pixelBufferDescriptor2.left, pixelBufferDescriptor2.top, pixelBufferDescriptor2.type.ordinal(), pixelBufferDescriptor2.alignment, pixelBufferDescriptor2.compressedSizeInBytes, pixelBufferDescriptor2.compressedFormat.ordinal(), pixelBufferDescriptor2.handler, pixelBufferDescriptor2.callback);
        } else {
            i6 = nSetImage(getNativeObject(), engine.getNativeObject(), i, i2, i3, i4, i5, pixelBufferDescriptor2.storage, pixelBufferDescriptor2.storage.remaining(), pixelBufferDescriptor2.left, pixelBufferDescriptor2.top, pixelBufferDescriptor2.type.ordinal(), pixelBufferDescriptor2.alignment, pixelBufferDescriptor2.stride, pixelBufferDescriptor2.format.ordinal(), pixelBufferDescriptor2.handler, pixelBufferDescriptor2.callback);
        }
        if (i6 < 0) {
            throw new BufferOverflowException();
        }
    }

    public void setImage(Engine engine, int i, PixelBufferDescriptor pixelBufferDescriptor, int[] iArr) {
        int i2;
        PixelBufferDescriptor pixelBufferDescriptor2 = pixelBufferDescriptor;
        if (pixelBufferDescriptor2.type == Type.COMPRESSED) {
            i2 = nSetImageCubemapCompressed(getNativeObject(), engine.getNativeObject(), i, pixelBufferDescriptor2.storage, pixelBufferDescriptor2.storage.remaining(), pixelBufferDescriptor2.left, pixelBufferDescriptor2.top, pixelBufferDescriptor2.type.ordinal(), pixelBufferDescriptor2.alignment, pixelBufferDescriptor2.compressedSizeInBytes, pixelBufferDescriptor2.compressedFormat.ordinal(), iArr, pixelBufferDescriptor2.handler, pixelBufferDescriptor2.callback);
        } else {
            i2 = nSetImageCubemap(getNativeObject(), engine.getNativeObject(), i, pixelBufferDescriptor2.storage, pixelBufferDescriptor2.storage.remaining(), pixelBufferDescriptor2.left, pixelBufferDescriptor2.top, pixelBufferDescriptor2.type.ordinal(), pixelBufferDescriptor2.alignment, pixelBufferDescriptor2.stride, pixelBufferDescriptor2.format.ordinal(), iArr, pixelBufferDescriptor2.handler, pixelBufferDescriptor2.callback);
        }
        if (i2 < 0) {
            throw new BufferOverflowException();
        }
    }

    public void setExternalImage(Engine engine, long j) {
        nSetExternalImage(getNativeObject(), engine.getNativeObject(), j);
    }

    public void setExternalStream(Engine engine, Stream stream) {
        long nativeObject = getNativeObject();
        long nativeObject2 = stream.getNativeObject();
        if (nIsStreamValidForTexture(nativeObject, nativeObject2)) {
            nSetExternalStream(nativeObject, engine.getNativeObject(), nativeObject2);
            return;
        }
        throw new IllegalStateException("Invalid texture sampler: When used with a stream, a texture must use a SAMPLER_EXTERNAL");
    }

    public void generateMipmaps(Engine engine) {
        nGenerateMipmaps(getNativeObject(), engine.getNativeObject());
    }

    public void generatePrefilterMipmap(Engine engine, PixelBufferDescriptor pixelBufferDescriptor, int[] iArr, PrefilterOptions prefilterOptions) {
        boolean z;
        int i;
        PixelBufferDescriptor pixelBufferDescriptor2 = pixelBufferDescriptor;
        PrefilterOptions prefilterOptions2 = prefilterOptions;
        int width = getWidth(0);
        int height = getHeight(0);
        if (prefilterOptions2 != null) {
            int i2 = prefilterOptions2.sampleCount;
            z = prefilterOptions2.mirror;
            i = i2;
        } else {
            i = 8;
            z = true;
        }
        if (nGeneratePrefilterMipmap(getNativeObject(), engine.getNativeObject(), width, height, pixelBufferDescriptor2.storage, pixelBufferDescriptor2.storage.remaining(), pixelBufferDescriptor2.left, pixelBufferDescriptor2.top, pixelBufferDescriptor2.type.ordinal(), pixelBufferDescriptor2.alignment, pixelBufferDescriptor2.stride, pixelBufferDescriptor2.format.ordinal(), iArr, pixelBufferDescriptor2.handler, pixelBufferDescriptor2.callback, i, z) < 0) {
            throw new BufferOverflowException();
        }
    }

    public long getNativeObject() {
        long j = this.mNativeObject;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("Calling method on destroyed Texture");
    }

    /* access modifiers changed from: package-private */
    public void clearNativeObject() {
        this.mNativeObject = 0;
    }
}
