package androidx.core.app;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.os.Build;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationChannelGroupCompat {
    private boolean mBlocked;
    private List<NotificationChannelCompat> mChannels;
    String mDescription;
    final String mId;
    CharSequence mName;

    public static class Builder {
        final NotificationChannelGroupCompat mGroup;

        public Builder(String str) {
            this.mGroup = new NotificationChannelGroupCompat(str);
        }

        public Builder setName(CharSequence charSequence) {
            this.mGroup.mName = charSequence;
            return this;
        }

        public Builder setDescription(String str) {
            this.mGroup.mDescription = str;
            return this;
        }

        public NotificationChannelGroupCompat build() {
            return this.mGroup;
        }
    }

    NotificationChannelGroupCompat(String str) {
        this.mChannels = Collections.emptyList();
        this.mId = (String) Preconditions.checkNotNull(str);
    }

    NotificationChannelGroupCompat(NotificationChannelGroup notificationChannelGroup) {
        this(notificationChannelGroup, Collections.emptyList());
    }

    NotificationChannelGroupCompat(NotificationChannelGroup notificationChannelGroup, List<NotificationChannel> list) {
        this(NotificationCompat$$ExternalSyntheticApiModelOutline0.m(notificationChannelGroup));
        this.mName = NotificationCompat$$ExternalSyntheticApiModelOutline0.m(notificationChannelGroup);
        if (Build.VERSION.SDK_INT >= 28) {
            this.mDescription = notificationChannelGroup.getDescription();
        }
        if (Build.VERSION.SDK_INT >= 28) {
            this.mBlocked = NotificationCompat$$ExternalSyntheticApiModelOutline0.m(notificationChannelGroup);
            this.mChannels = getChannelsCompat(NotificationCompat$$ExternalSyntheticApiModelOutline0.m(notificationChannelGroup));
            return;
        }
        this.mChannels = getChannelsCompat(list);
    }

    private List<NotificationChannelCompat> getChannelsCompat(List<NotificationChannel> list) {
        ArrayList arrayList = new ArrayList();
        for (NotificationChannel m : list) {
            NotificationChannel m2 = NotificationCompat$$ExternalSyntheticApiModelOutline0.m((Object) m);
            if (this.mId.equals(NotificationCompat$$ExternalSyntheticApiModelOutline0.m$3(m2))) {
                arrayList.add(new NotificationChannelCompat(m2));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public NotificationChannelGroup getNotificationChannelGroup() {
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        NotificationCompat$$ExternalSyntheticApiModelOutline0.m();
        NotificationChannelGroup m = NotificationCompat$$ExternalSyntheticApiModelOutline0.m(this.mId, this.mName);
        if (Build.VERSION.SDK_INT >= 28) {
            m.setDescription(this.mDescription);
        }
        return m;
    }

    public Builder toBuilder() {
        return new Builder(this.mId).setName(this.mName).setDescription(this.mDescription);
    }

    public String getId() {
        return this.mId;
    }

    public CharSequence getName() {
        return this.mName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public boolean isBlocked() {
        return this.mBlocked;
    }

    public List<NotificationChannelCompat> getChannels() {
        return this.mChannels;
    }
}
