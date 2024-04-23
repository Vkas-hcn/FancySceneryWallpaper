package com.wallpaper.kepaper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Create by l
 * Description:
 **/
public class QUeiwkskgds implements Parcelable.Creator<Qyeusjgies> {
    @Override
    public Qyeusjgies createFromParcel(Parcel parcel) {
        return new Qyeusjgies(parcel);
    }

    @Override
    public Qyeusjgies[] newArray(int i2) {
        return new Qyeusjgies[i2];
    }
}