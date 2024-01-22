package com.fast.sixth.man.open

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.fast.sixth.man.listener.FancyNotificationHelper
import com.fast.sixth.man.tools.FancyLog

/**
 * Date：2024/1/19
 * Describe:
 */
class FancyService : Service() {
    companion object {
        var isShow = false
    }

    private val helper by lazy { FancyNotificationHelper(this) }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        isShow = true
        FancyLog.e("FancyService  onCreate-->")
        startForeground(1920, helper.getNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }
}