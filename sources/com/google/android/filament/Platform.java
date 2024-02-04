package com.google.android.filament;

abstract class Platform {
    private static Platform mCurrentPlatform;

    /* access modifiers changed from: package-private */
    public abstract long getSharedContextNativeHandle(Object obj);

    /* access modifiers changed from: package-private */
    public abstract void log(String str);

    /* access modifiers changed from: package-private */
    public abstract boolean validateSharedContext(Object obj);

    /* access modifiers changed from: package-private */
    public abstract boolean validateStreamSource(Object obj);

    /* access modifiers changed from: package-private */
    public abstract boolean validateSurface(Object obj);

    /* access modifiers changed from: package-private */
    public abstract void warn(String str);

    static boolean isAndroid() {
        return "The Android Project".equalsIgnoreCase(System.getProperty("java.vendor"));
    }

    static boolean isWindows() {
        return System.getProperty("os.name").contains("Windows");
    }

    static boolean isMacOS() {
        return System.getProperty("os.name").contains("Mac OS X");
    }

    static boolean isLinux() {
        return System.getProperty("os.name").contains("Linux") && !isAndroid();
    }

    static Platform get() {
        if (mCurrentPlatform == null) {
            try {
                if (isAndroid()) {
                    mCurrentPlatform = (Platform) Class.forName("com.google.android.filament.AndroidPlatform").newInstance();
                } else {
                    mCurrentPlatform = (Platform) Class.forName("com.google.android.filament.DesktopPlatform").newInstance();
                }
            } catch (Exception unused) {
            }
            if (mCurrentPlatform == null) {
                mCurrentPlatform = new UnknownPlatform();
            }
        }
        return mCurrentPlatform;
    }

    Platform() {
    }

    private static class UnknownPlatform extends Platform {
        /* access modifiers changed from: package-private */
        public long getSharedContextNativeHandle(Object obj) {
            return 0;
        }

        /* access modifiers changed from: package-private */
        public boolean validateSharedContext(Object obj) {
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean validateStreamSource(Object obj) {
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean validateSurface(Object obj) {
            return false;
        }

        private UnknownPlatform() {
        }

        /* access modifiers changed from: package-private */
        public void log(String str) {
            System.out.println(str);
        }

        /* access modifiers changed from: package-private */
        public void warn(String str) {
            System.out.println(str);
        }
    }
}
