package com.fast.sixth.man.open

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.JCSiSi.IFiCBnkuxQ.TJqwEFbY
import com.fast.sixth.man.core.mApp
import com.fast.sixth.man.listener.FancyNotificationHelper
import com.fast.sixth.man.other.info.mCloakInfo
import com.fast.sixth.man.tools.FancyLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Date：2024/1/19
 * Describe:
 */
class FancyService : Service() {
    companion object {
        var isShow = false

        fun closeMe(context: Context) {
            if (isShow) {
                runCatching {
                    ContextCompat.startForegroundService(
                        context,
                        Intent(context, FancyService::class.java).apply {
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
        startForeground(1920, helper.getNotification())
//        startJob()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.getBooleanExtra("isC", false) == true) {
            stopSelf()
        }
        return START_STICKY
    }

    private var jobTime: Job? = null

    //todo delete
    private fun startJob() {
        jobTime?.cancel()
        jobTime = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                TJqwEFbY.cnVHMqua(mApp, 0)
                delay(30000)
                FancyLog.e("time job--->")
            }
        }
    }
}