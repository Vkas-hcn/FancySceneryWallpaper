package com.wallpaper.kepaper;


import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;

import com.wallpaper.qudigks.Contants;

/**
 * @author Create by l
 * Description:
 **/
public class Queisjgksdnjxmgd extends BroadcastReceiver {

    public static Queisjgksdnjxmgd f12223a;

    public class a implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public static synchronized void a(Context context) {
        synchronized (Queisjgksdnjxmgd.class) {
            synchronized (Queisjgksdnjxmgd.class) {
                if (f12223a == null) {
                    f12223a = new Queisjgksdnjxmgd();
                    IntentFilter intentFilter = new IntentFilter(Contants.COM_ASS_SERVICE_START);
                    intentFilter.setPriority(1000);
                    context.registerReceiver(f12223a, intentFilter);
                }
            }
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("service_name");
        if (!TextUtils.isEmpty(stringExtra)) {
            a1(context, stringExtra);
            //Log.e("DaemonLog", "ServiceStartReceiver bind:" + stringExtra);
        }
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction(Contants.COM_ASS_SERVICE_START);
        intent.putExtra("service_name", str);
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent);
    }

    private void a1(Context context, String str) {
        try {
            Intent intent = new Intent();
            intent.setClassName(context.getPackageName(), str);
            context.bindService(intent, new a(), 65);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
