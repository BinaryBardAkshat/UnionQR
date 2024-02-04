package com.google.zxing.client.android;

import com.google.zxing.client.android.InactivityTimer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class InactivityTimer$PowerStatusReceiver$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ InactivityTimer.PowerStatusReceiver f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ InactivityTimer$PowerStatusReceiver$$ExternalSyntheticLambda0(InactivityTimer.PowerStatusReceiver powerStatusReceiver, boolean z) {
        this.f$0 = powerStatusReceiver;
        this.f$1 = z;
    }

    public final void run() {
        this.f$0.m23lambda$onReceive$0$comgooglezxingclientandroidInactivityTimer$PowerStatusReceiver(this.f$1);
    }
}
