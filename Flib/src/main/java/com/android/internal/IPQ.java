package com.android.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import com.android.local.IQF;

/**
 * @author Create by l
 * Description:
 **/
public interface IPQ extends IInterface {

    public static abstract class a extends Binder implements IPQ {
        public a() {
            attachInterface(this, IQF.COM_IMMORTAL_AEGIS_IAEGIS_SERVICE);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
            if (i2 == 1) {
                parcel.enforceInterface(IQF.COM_IMMORTAL_AEGIS_IAEGIS_SERVICE);
                String name = getName();
                parcel2.writeNoException();
                parcel2.writeString(name);
                return true;
            } else if (i2 != 1598968902) {
                return super.onTransact(i2, parcel, parcel2, i3);
            } else {
                parcel2.writeString(IQF.COM_IMMORTAL_AEGIS_IAEGIS_SERVICE);
                return true;
            }
        }
    }

    String getName();
}
