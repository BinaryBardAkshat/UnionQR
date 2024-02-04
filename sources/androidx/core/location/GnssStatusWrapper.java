package androidx.core.location;

import android.location.GnssStatus;
import android.os.Build;
import androidx.core.graphics.Insets$$ExternalSyntheticApiModelOutline0;
import androidx.core.util.Preconditions;

class GnssStatusWrapper extends GnssStatusCompat {
    private final GnssStatus mWrapped;

    GnssStatusWrapper(GnssStatus gnssStatus) {
        this.mWrapped = Insets$$ExternalSyntheticApiModelOutline0.m(Preconditions.checkNotNull(gnssStatus));
    }

    public int getSatelliteCount() {
        return this.mWrapped.getSatelliteCount();
    }

    public int getConstellationType(int i) {
        return Insets$$ExternalSyntheticApiModelOutline0.m(this.mWrapped, i);
    }

    public int getSvid(int i) {
        return Insets$$ExternalSyntheticApiModelOutline0.m$1(this.mWrapped, i);
    }

    public float getCn0DbHz(int i) {
        return Insets$$ExternalSyntheticApiModelOutline0.m(this.mWrapped, i);
    }

    public float getElevationDegrees(int i) {
        return Insets$$ExternalSyntheticApiModelOutline0.m$4(this.mWrapped, i);
    }

    public float getAzimuthDegrees(int i) {
        return Insets$$ExternalSyntheticApiModelOutline0.m$2(this.mWrapped, i);
    }

    public boolean hasEphemerisData(int i) {
        return Insets$$ExternalSyntheticApiModelOutline0.m(this.mWrapped, i);
    }

    public boolean hasAlmanacData(int i) {
        return Insets$$ExternalSyntheticApiModelOutline0.m$3(this.mWrapped, i);
    }

    public boolean usedInFix(int i) {
        return Insets$$ExternalSyntheticApiModelOutline0.m$1(this.mWrapped, i);
    }

    public boolean hasCarrierFrequencyHz(int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            return Insets$$ExternalSyntheticApiModelOutline0.m$2(this.mWrapped, i);
        }
        return false;
    }

    public float getCarrierFrequencyHz(int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            return Insets$$ExternalSyntheticApiModelOutline0.m$1(this.mWrapped, i);
        }
        throw new UnsupportedOperationException();
    }

    public boolean hasBasebandCn0DbHz(int i) {
        if (Build.VERSION.SDK_INT >= 30) {
            return Insets$$ExternalSyntheticApiModelOutline0.m$4(this.mWrapped, i);
        }
        return false;
    }

    public float getBasebandCn0DbHz(int i) {
        if (Build.VERSION.SDK_INT >= 30) {
            return Insets$$ExternalSyntheticApiModelOutline0.m$3(this.mWrapped, i);
        }
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GnssStatusWrapper)) {
            return false;
        }
        return this.mWrapped.equals(((GnssStatusWrapper) obj).mWrapped);
    }

    public int hashCode() {
        return this.mWrapped.hashCode();
    }
}
