package com.wallsc.scrk.open

import android.content.Context
import com.anythink.interstitial.api.ATInterstitial
import com.wallsc.scrk.other.FancyAdFetch
import com.wallsc.scrk.other.info.FancySceneryConfigure

/**
 * Date：2024/1/19
 * Describe:
 */
class TLoadImpl(val context: Context) {

    fun loadI(): ATInterstitial {
        return ATInterstitial(context, FancySceneryConfigure.sceneryAdId).apply {
            setAdListener(FancyAdFetch.loadListener)
            load()
        }
    }
}