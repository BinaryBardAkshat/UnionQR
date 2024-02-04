package com.google.android.filament;

public class Fence {
    public static final long WAIT_FOR_EVER = -1;
    private long mNativeObject;

    public enum FenceStatus {
        ERROR,
        CONDITION_SATISFIED,
        TIMEOUT_EXPIRED
    }

    public enum Mode {
        FLUSH,
        DONT_FLUSH
    }

    private static native int nWait(long j, int i, long j2);

    private static native int nWaitAndDestroy(long j, int i);

    Fence(long j) {
        this.mNativeObject = j;
    }

    public FenceStatus wait(Mode mode, long j) {
        int nWait = nWait(getNativeObject(), mode.ordinal(), j);
        if (nWait == -1) {
            return FenceStatus.ERROR;
        }
        if (nWait == 0) {
            return FenceStatus.CONDITION_SATISFIED;
        }
        if (nWait != 1) {
            return FenceStatus.ERROR;
        }
        return FenceStatus.TIMEOUT_EXPIRED;
    }

    public static FenceStatus waitAndDestroy(Fence fence, Mode mode) {
        int nWaitAndDestroy = nWaitAndDestroy(fence.getNativeObject(), mode.ordinal());
        if (nWaitAndDestroy == -1) {
            return FenceStatus.ERROR;
        }
        if (nWaitAndDestroy != 0) {
            return FenceStatus.ERROR;
        }
        return FenceStatus.CONDITION_SATISFIED;
    }

    public long getNativeObject() {
        long j = this.mNativeObject;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("Calling method on destroyed Fence");
    }

    /* access modifiers changed from: package-private */
    public void clearNativeObject() {
        this.mNativeObject = 0;
    }
}
