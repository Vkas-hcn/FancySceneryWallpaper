package com.fast.sixth.man.open

import android.content.Context
import com.anythink.interstitial.api.ATInterstitial
import com.fast.sixth.man.other.FancyAdFetch
import com.fast.sixth.man.other.info.FancySceneryConfigure

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