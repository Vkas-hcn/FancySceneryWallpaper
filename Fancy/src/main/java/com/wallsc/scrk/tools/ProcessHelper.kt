package com.wallsc.scrk.tools

import android.app.ActivityManager
import android.content.Context
import com.adjust.sdk.Adjust
import com.adjust.sdk.AdjustConfig
import com.wallsc.scrk.core.mApp
import com.wallsc.scrk.other.info.FancySceneryNetwork
import com.wallsc.scrk.other.info.SpFancy

/**
 * Date：2024/1/17
 * Describe:
 */

val spFancy by lazy { mApp.getSharedPreferences("FancySceneryWallpaper", Context.MODE_PRIVATE) }

class ProcessHelper(val context: Context) {
    fun isMain(): Boolean {
        return try {
            context.packageName == getPName()
        } catch (e: Exception) {
            false
        }
    }

    private fun getPName(): String? {
        with(context) {
            val pid = android.os.Process.myPid()
            val am = getSystemService(android.app.Application.ACTIVITY_SERVICE) as ActivityManager
            val runningApps = am.runningAppProcesses ?: return null
            for (procInfo in runningApps) {
                if (procInfo.pid == pid) {
                    return procInfo.processName
                }
            }
            return null
        }
    }

    fun adjFancyInit() {
        //todo delete
        val environment =AdjustConfig.ENVIRONMENT_SANDBOX
//            if (BuildConfig.DEBUG) AdjustConfig.ENVIRONMENT_SANDBOX else AdjustConfig.ENVIRONMENT_PRODUCTION
        //todo modify adjust key
        val config = AdjustConfig(context, "ih2pm2dr3k74", environment)
        Adjust.addSessionCallbackParameter("customer_user_id", AppInfoTools.getDId())
        config.setOnAttributionChangedListener {
            FancyLog.i("setOnAttributionChangedListener--->${it.network}")
            if (SpFancy.isAdJUser().not()) {
                val network = it.network
                if (network.isNotBlank()) {
                    SpFancy.fancyNetwork = network
                }
                if (SpFancy.isAdJUser()) {
                    FancySceneryNetwork.postMai("netjust", isPost = true)
                }
//            }
            }
        }

        Adjust.onCreate(config)
    }
}