package com.wallpaper.kepaper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * @author Create by l
 * Description:
 **/
public class IPS implements IPZ.AbstractC0188a{

    public static class a implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    @Override
    public boolean a(Context context, String str) {
        Class cls = null;
        try {
            IPZ a2 = Tiusjgjskg.a();
            if (str.equals(a2.f27345b)) {
                cls = IPD.class;
            } else if (str.equals(a2.f27346c)) {
                cls = IPE.class;
            } else if (str.equals(a2.f27347d)) {
                cls = IPF.class;
            }
            a(context, cls);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void a(Context context, Class<?> cls) {
        try {
            context.startService(new Intent(context, cls));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            a(context, null, cls);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public static void a(Context context, Intent intent, Class<?> cls) {
        if (cls != null) {
            if (intent == null) {
                intent = new Intent(context, cls);
            } else {
                intent.setClass(context, cls);
            }
            context.bindService(intent, new a(), Context.BIND_AUTO_CREATE);
        }
    }
}
