package com.wallsc.scrk.core

import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.TelephonyManager
import android.webkit.WebSettings
import androidx.core.content.ContextCompat
import com.anythink.core.api.ATDebuggerConfig
import com.anythink.core.api.ATSDK
import com.anythink.network.bigo.BigoATConst
import com.anythink.network.chartboost.ChartboostATConst
import com.wallsc.scrk.open.ShuajService
import com.wallsc.scrk.other.ActivityLifeHelper
import com.wallsc.scrk.other.FancyAdFetch
import com.wallsc.scrk.other.info.FancySceneryConfigure
import com.wallsc.scrk.other.info.mCloakInfo
import com.wallsc.scrk.tools.AppInfoTools
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Date：2024/1/17
 * Describe:
 */
class InitCore(val context: Context) {
    init {
        dataInit()
    }

    private val mScope = CoroutineScope(Dispatchers.Main)
    fun startInit() {
        //todo delete
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                AdvertisingIdClient.getAdvertisingIdInfo(context).apply {
                    val mGaidInfo = id ?: "53dd390b-763a-426a-93ed-46953d88fbc0"
                    ATSDK.setDebuggerConfig(
                        context,
                        mGaidInfo,
                        ATDebuggerConfig.Builder(ChartboostATConst.DEBUGGER_CONFIG.Chartboost_NETWORK)
                            .build()
                    )
                    ATSDK.setNetworkLogDebug(true)
                    ATSDK.init(context, "a65fbc897b5363", "a85337a358c483ccc80417e95a0fad52b")
                }
            }
        }
        // todo open
//          ATSDK.init(context, "a65fbc897b5363", "a85337a358c483ccc80417e95a0fad52b")
    }

    fun checkTask() {
        mScope.launch {
            delay(4000)
            while (true) {
                FancyAdFetch.load()
                delay(FancySceneryConfigure.checkPeriodTime)
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            if (Build.VERSION.SDK_INT < "31".toInt()) {
                delay(819)
                startFancyService()
            } else {
                while (ShuajService.isShow.not()) {
                    delay(3411)
                    if (ActivityLifeHelper.isAppResume) {
                        startFancyService()
                    }
                }
            }
        }
    }

    private fun dataInit() {
        if (AppInfoTools.gaid.isBlank()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    AppInfoTools.gaid = AdvertisingIdClient.getAdvertisingIdInfo(context).id ?: ""
                } catch (e: Exception) {
                    ""
                }
            }
        }
        val manager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        AppInfoTools.their = manager.networkOperator
        runCatching {
            AppInfoTools.webSetStr = WebSettings.getDefaultUserAgent(context)
        }
        AppInfoTools.getPackageInfo(context).apply {
            AppInfoTools.installTime = firstInstallTime
            AppInfoTools.updateTime = lastUpdateTime
        }
    }

    private fun startFancyService() {
        if (FancySceneryConfigure.fancyCBean.isShowN()) {
            if (mCloakInfo == 5) {
                return
            }
            runCatching {
                ContextCompat.startForegroundService(
                    context, Intent(context, ShuajService::class.java)
                )
            }
        }
    }

}