package com.wallpaper.kepaper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author Create by l
 * Description:
 **/
public class IPD extends Service {

    public static class a implements IPW.a {
        @Override
        public void a(Context context) {
            IQA.a(context, IPD.class.getName());
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IPM(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IQA.a(this, IPD.class.getName());
        IPW.a(this, new a());
    }

    @Override
    public int onStartCommand(Intent intent, int i2, int i3) {
        return START_NOT_STICKY;
    }
}
