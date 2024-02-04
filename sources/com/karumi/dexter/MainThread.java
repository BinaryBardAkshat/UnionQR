package com.karumi.dexter;

import android.os.Handler;
import android.os.Looper;

final class MainThread implements Thread {
    MainThread() {
    }

    private static boolean runningMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public void execute(Runnable runnable) {
        if (runningMainThread()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    public void loop() {
    }
}
