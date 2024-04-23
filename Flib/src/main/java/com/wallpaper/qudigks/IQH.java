package com.wallpaper.qudigks;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Create by l
 * Description:
 **/
public class IQH implements Parcelable.Creator<IQJ> {
    @Override
    public IQJ createFromParcel(Parcel parcel) {
        return new IQJ(parcel);
    }

    @Override
    public IQJ[] newArray(int i2) {
        return new IQJ[i2];
    }
}