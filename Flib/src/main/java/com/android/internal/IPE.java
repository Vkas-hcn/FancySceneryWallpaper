package com.android.internal;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author Create by l
 * Description:
 **/
public class IPE extends Service {

    public class a implements IPW.a {
        public a() {
        }

        @Override
        public void a(Context context) {
            IQA.a(context, IPE.class.getName());
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IPO(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IQA.a(this, IPE.class.getName());
        IPW.a(this, new a());
    }

    @Override
    public int onStartCommand(Intent intent, int i2, int i3) {
        return START_NOT_STICKY;
    }
}
