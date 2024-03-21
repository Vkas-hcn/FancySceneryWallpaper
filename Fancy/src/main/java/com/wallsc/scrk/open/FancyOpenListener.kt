package com.wallsc.scrk.open

import com.anythink.core.api.ATAdInfo
import com.anythink.core.api.AdError
import com.anythink.splashad.api.ATSplashAdExtraInfo
import com.anythink.splashad.api.ATSplashAdListener
import com.wallsc.scrk.core.FancyOpenHelper
import com.wallsc.scrk.tools.FancyLog

/**
 * Date：2024/1/18
 * Describe:
 */
open class FancyOpenListener : ATSplashAdListener {
    override fun onAdLoaded(p0: Boolean) {
        FancyLog.e("FancyOpenListener-->onAdLoaded")
        FancyOpenHelper.isLoading = false
    }

    override fun onAdLoadTimeout() {
        FancyLog.e("FancyOpenListener-->onAdLoadTimeout")
        FancyOpenHelper.isLoading = false
    }

    override fun onNoAdError(p0: AdError?) {
        FancyLog.e("FancyOpenListener-->onAdLoadTimeout")
        FancyOpenHelper.isLoading = false
    }

    override fun onAdShow(p0: ATAdInfo?) {
        FancyLog.e("FancyOpenListener-->onAdShow")
    }

    override fun onAdClick(p0: ATAdInfo?) = Unit

    override fun onAdDismiss(p0: ATAdInfo?, p1: ATSplashAdExtraInfo?) {
        FancyLog.e("FancyOpenListener-->onAdDismiss")
    }
}