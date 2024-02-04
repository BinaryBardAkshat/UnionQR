package com.google.android.filament;

public class Filament {
    public static void init() {
    }

    static {
        Platform.get();
        System.loadLibrary("filament-jni");
    }

    private Filament() {
    }
}
