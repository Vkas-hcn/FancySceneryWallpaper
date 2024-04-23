package com.android.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.android.local.IQF;

/**
 * @author Create by l
 * Description:
 **/
public class IPW extends BroadcastReceiver {
    public static IPW f12221a;
    public a f12222b;

    public interface a {
        void a(Context context);
    }

    public static void a(Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction(IQF.COM_AM_MAIN_PROCESS_START);
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        a aVar = this.f12222b;
        if (aVar != null) {
            aVar.a(context);
        }
    }

    public static synchronized void a(Context context, a aVar) {
        synchronized (IPW.class) {
            synchronized (IPW.class) {
                if (f12221a == null) {
                    f12221a = new IPW();
                    f12221a.f12222b = aVar;
                    IntentFilter intentFilter = new IntentFilter(IQF.COM_AM_MAIN_PROCESS_START);
                    intentFilter.setPriority(1000);
                    context.registerReceiver(f12221a, intentFilter);
                }
            }
        }
    }
}