package com.wallpaper.kepaper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Create by l
 * Description:
 **/
public class IPP implements Parcelable.Creator<IPY> {
    @Override
    public IPY createFromParcel(Parcel parcel) {
        return new IPY(parcel);
    }

    @Override
    public IPY[] newArray(int i2) {
        return new IPY[i2];
    }
}