package com.journeyapps.barcodescanner.camera;

import android.os.Handler;
import android.os.HandlerThread;

class CameraThread {
    private static final String TAG = "CameraThread";
    private static CameraThread instance;
    private final Object LOCK = new Object();
    private Handler handler;
    private int openCount = 0;
    private HandlerThread thread;

    public static CameraThread getInstance() {
        if (instance == null) {
            instance = new CameraThread();
        }
        return instance;
    }

    private CameraThread() {
    }

    /* access modifiers changed from: protected */
    public void enqueue(Runnable runnable) {
        synchronized (this.LOCK) {
            checkRunning();
            this.handler.post(runnable);
        }
    }

    /* access modifiers changed from: protected */
    public void enqueueDelayed(Runnable runnable, long j) {
        synchronized (this.LOCK) {
            checkRunning();
            this.handler.postDelayed(runnable, j);
        }
    }

    private void checkRunning() {
        synchronized (this.LOCK) {
            if (this.handler == null) {
                if (this.openCount > 0) {
                    HandlerThread handlerThread = new HandlerThread(TAG);
                    this.thread = handlerThread;
                    handlerThread.start();
                    this.handler = new Handler(this.thread.getLooper());
                } else {
                    throw new IllegalStateException("CameraThread is not open");
                }
            }
        }
    }

    private void quit() {
        synchronized (this.LOCK) {
            this.thread.quit();
            this.thread = null;
            this.handler = null;
        }
    }

    /* access modifiers changed from: protected */
    public void decrementInstances() {
        synchronized (this.LOCK) {
            int i = this.openCount - 1;
            this.openCount = i;
            if (i == 0) {
                quit();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void incrementAndEnqueue(Runnable runnable) {
        synchronized (this.LOCK) {
            this.openCount++;
            enqueue(runnable);
        }
    }
}
