package com.android.local;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

/**
 * @author Create by l
 * Description:
 **/
public class IQJ implements Parcelable {
    public static final Creator<IQJ> IQH = new IQH();

    public String[] pathArr;

    public String processName;

    public Intent serviceIntent;

    public Intent dReceiverIntent;

    public Intent instruIntent;

    public IQJ() {
    }

    public static IQJ createParcel(String str) {
        Parcel obtain = Parcel.obtain();
        byte[] decode = Base64.decode(str, 2);
        obtain.unmarshall(decode, 0, decode.length);
        obtain.setDataPosition(0);
        IQJ createFromParcel = IQH.createFromParcel(obtain);
        obtain.recycle();
        return createFromParcel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        Parcel obtain = Parcel.obtain();
        writeToParcel(obtain, 0);
        String encodeToString = Base64.encodeToString(obtain.marshall(), 2);
        obtain.recycle();
        return encodeToString;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeStringArray(this.pathArr);
        parcel.writeString(this.processName);
        if (this.serviceIntent == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            this.serviceIntent.writeToParcel(parcel, 0);
        }
        if (this.dReceiverIntent == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            this.dReceiverIntent.writeToParcel(parcel, 0);
        }
        if (this.instruIntent == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        this.instruIntent.writeToParcel(parcel, 0);
    }

    public IQJ(Parcel parcel) {
        this.pathArr = parcel.createStringArray();
        this.processName = parcel.readString();
        if (parcel.readInt() != 0) {
            this.serviceIntent = (Intent) Intent.CREATOR.createFromParcel(parcel);
        }
        if (parcel.readInt() != 0) {
            this.dReceiverIntent = (Intent) Intent.CREATOR.createFromParcel(parcel);
        }
        if (parcel.readInt() != 0) {
            this.instruIntent = (Intent) Intent.CREATOR.createFromParcel(parcel);
        }
    }
}
