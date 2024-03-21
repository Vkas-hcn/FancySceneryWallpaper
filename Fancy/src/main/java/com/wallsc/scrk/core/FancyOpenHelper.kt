package com.wallsc.scrk.core

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import com.anythink.core.api.ATAdInfo
import com.anythink.splashad.api.ATSplashAd
import com.anythink.splashad.api.ATSplashAdExtraInfo
import com.wallsc.scrk.open.FancyOpenListener
import com.wallsc.scrk.tools.FancyLog

/**
 * Date：2024/1/17
 * Describe:
 */
object FancyOpenHelper {
    var isLoading = false
    var mAdFancy: Any? = null

    fun load(context: Context) {
        if (isLoading) return
        if (isReady(mAdFancy)) return
        FancyLog.e("FancyOpenHelper-->load")
        isLoading = true
        mAdFancy = ATSplashAd(context, "b1faos9g3q2gin", FancyOpenListener()).apply {
            loadAd()
        }
    }

    fun showFancySp(activity: Activity, viewGroup: ViewGroup, showFinish: () -> Unit): Boolean {
        if (isReady(mAdFancy).not()) return false
        when (val ad = mAdFancy) {
            is ATSplashAd -> {
                ad.setAdListener(object : FancyOpenListener() {
                    override fun onAdDismiss(p0: ATAdInfo?, p1: ATSplashAdExtraInfo?) {
                        super.onAdDismiss(p0, p1)
                        showFinish.invoke()
                    }
                })
                ad.show(activity, viewGroup)
                return true
            }
        }
        return false
    }

    private fun isReady(any: Any?): Boolean {
        when (any) {
            is ATSplashAd -> return any.isAdReady
        }
        return false
    }


}