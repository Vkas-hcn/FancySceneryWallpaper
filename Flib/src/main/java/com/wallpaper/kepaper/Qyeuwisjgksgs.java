package com.wallpaper.kepaper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author Create by l
 * Description:
 **/
public class Qyeuwisjgksgs extends Service {

    public class a implements QUeisjkgs.a {
        public a() {
        }

        @Override
        public void a(Context context) {
            Queisjgksdnjxmgd.a(context, Qyeuwisjgksgs.class.getName());
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Quwijksgsd(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Queisjgksdnjxmgd.a(this, Qyeuwisjgksgs.class.getName());
        QUeisjkgs.a(this, new a());
    }

    @Override
    public int onStartCommand(Intent intent, int i2, int i3) {
        return START_NOT_STICKY;
    }
}
