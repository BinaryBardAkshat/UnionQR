package com.karumi.dexter.listener;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class SettingsClickListener implements View.OnClickListener {
    public void onClick(View view) {
        Context context = view.getContext();
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + context.getPackageName()));
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setFlags(268435456);
        context.startActivity(intent);
    }
}
