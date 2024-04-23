package com.wallpaper.qudigks;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Create by l
 * Description:
 **/
public class Guqisjgksd implements Parcelable.Creator<Tusigjksg> {
    @Override
    public Tusigjksg createFromParcel(Parcel parcel) {
        return new Tusigjksg(parcel);
    }

    @Override
    public Tusigjksg[] newArray(int i2) {
        return new Tusigjksg[i2];
    }
}