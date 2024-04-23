package com.android.local;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created  by l
 * Description:
 */
public class IQL {
    public static class Conn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public static void startService(Context context, Class<?> clazz) {
        try {
            context.startService(new Intent(context, clazz));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Intent intent = new Intent();
            intent.setClass(context, clazz);
            context.bindService(intent, new Conn(), Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bindService(Context context, String clazz) {
        try {
            Intent intent = new Intent();
            intent.setClassName(context.getPackageName(), clazz);
            context.bindService(intent, new Conn(), Context.BIND_AUTO_CREATE | Context.BIND_IMPORTANT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
