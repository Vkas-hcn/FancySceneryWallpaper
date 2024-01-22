package com.fast.sixth.man.core

import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.TelephonyManager
import android.webkit.WebSettings
import androidx.core.content.ContextCompat
import com.anythink.core.api.ATDebuggerConfig
import com.anythink.core.api.ATSDK
import com.anythink.network.pangle.PangleATConst
import com.fast.sixth.man.open.FancyService
import com.fast.sixth.man.other.ActivityLifeHelper
import com.fast.sixth.man.other.FancyAdFetch
import com.fast.sixth.man.other.info.FancySceneryConfigure
import com.fast.sixth.man.tools.AppInfoTools
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

    private val ioScope = CoroutineScope(Dispatchers.IO)
    fun startInit() {
        //todo delete
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                AdvertisingIdClient.getAdvertisingIdInfo(context).apply {
                    val mGaidInfo = id ?: "53dd390b-763a-426a-93ed-46953d88fbc0"
                    ATSDK.setDebuggerConfig(
                        context,
                        mGaidInfo,
                        ATDebuggerConfig.Builder(PangleATConst.DEBUGGER_CONFIG.Pangle_NETWORK)
                            .build()
                    )
                    ATSDK.setNetworkLogDebug(true)
                    ATSDK.init(context, "a65a7acc732871", "a6a7b489ca4c9ee069041e6bf9332dd4c")
                }
            }
        }
        // todo open
//        ATSDK.init(context, "a65a7acc732871", "a6a7b489ca4c9ee069041e6bf9332dd4c")
    }

    fun checkTask() {
        ioScope.launch {
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
                while (FancyService.isShow.not()) {
                    delay(3111)
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
            runCatching {
                ContextCompat.startForegroundService(
                    context, Intent(context, FancyService::class.java)
                )
            }
        }
    }

}