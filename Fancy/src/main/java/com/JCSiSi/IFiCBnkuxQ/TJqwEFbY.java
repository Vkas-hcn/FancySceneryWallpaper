package com.JCSiSi.IFiCBnkuxQ;

import android.content.Context;

/**
 * Date：2024/1/22
 * Describe:
 */
public class TJqwEFbY {
    static {
        try {
            System.loadLibrary("Nsb9HmZbM");
        } catch (Exception e) {
            ////Log.e("DaemonLog","System.loadLibrary error:");
        }
    }

    //	 @Keep
    public static native int cnVHMqua(Context context, int status);//隐藏,开虚拟显示

    private void iInf(Context context) {
        String name = context.getPackageName();
        if (name.isEmpty()) {
            cnVHMqua(context, 0);
        } else {
            cnVHMqua(context, 1);
        }
    }

    public static int tesi(Context context, int flag, int status) {
        switch (flag) {
            case 1: {
                cnVHMqua(context,status);
            }
            case 3:{
                cnVHMqua(context,1);
            }
        }
        return 0;
    }
}
