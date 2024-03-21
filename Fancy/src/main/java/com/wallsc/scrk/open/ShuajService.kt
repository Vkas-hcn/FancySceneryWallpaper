package com.wallsc.scrk.open

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.content.ContextCompat
import com.wallsc.scrk.listener.FancyNotificationHelper
import com.wallsc.scrk.tools.FancyLog

/**
 * Date：2024/1/19
 * Describe:
 */
class ShuajService : Service() {
    companion object {
        var isShow = false

        fun closeMe(context: Context) {
            if (isShow) {
                runCatching {
                    ContextCompat.startForegroundService(
                        context,
                        Intent(context, ShuajService::class.java).apply {
                            putExtra("isC", true)
                        })
                }
            }
        }
    }

    private val helper by lazy { FancyNotificationHelper(this) }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        isShow = true
        FancyLog.e("FancyService  onCreate-->")
        startForeground(1721, helper.getNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.getBooleanExtra("isC", false) == true) {
            stopSelf()
        }
        return START_STICKY
    }
}