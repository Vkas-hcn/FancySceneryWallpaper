package com.wallpaper.kepaper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author Create by l
 * Description:
 **/
public class Qyeusigjsk extends Service {

    public static class a implements QUeisjkgs.a {
        @Override
        public void a(Context context) {
            Queisjgksdnjxmgd.a(context, Qyeusigjsk.class.getName());
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Qusigs(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Queisjgksdnjxmgd.a(this, Qyeusigjsk.class.getName());
        QUeisjkgs.a(this, new a());
    }

    @Override
    public int onStartCommand(Intent intent, int i2, int i3) {
        return START_NOT_STICKY;
    }
}
