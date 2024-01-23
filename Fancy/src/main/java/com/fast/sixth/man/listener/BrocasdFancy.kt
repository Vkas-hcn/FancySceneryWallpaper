package com.fast.sixth.man.listener

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.fast.sixth.man.tools.FancyLog

/**
 * Date：2024/1/22
 * Describe:
 */
class BrocasdFancy : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        FancyLog.e("onReceive--->intent${intent?.action}")
    }
}

class FancySceneryBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        FancyLog.e("onReceive--->intent${intent?.action}")
    }
}