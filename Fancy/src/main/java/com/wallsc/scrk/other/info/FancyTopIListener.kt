package com.wallsc.scrk.other.info

import com.adjust.sdk.Adjust
import com.adjust.sdk.AdjustAdRevenue
import com.adjust.sdk.AdjustConfig
import com.anythink.core.api.ATAdInfo
import com.anythink.core.api.AdError
import com.anythink.interstitial.api.ATInterstitialListener

/**
 * Date：2024/1/19
 * Describe:
 */
open class FancyTopIListener : ATInterstitialListener {
    override fun onInterstitialAdLoaded() {
    }

    override fun onInterstitialAdLoadFail(p0: AdError?) {
    }

    override fun onInterstitialAdClicked(p0: ATAdInfo?) {
    }

    override fun onInterstitialAdShow(p0: ATAdInfo?) {
        p0?.let {
            val adjustAdRevenue = AdjustAdRevenue(AdjustConfig.AD_REVENUE_SOURCE_PUBLISHER)
            adjustAdRevenue.setRevenue(p0.publisherRevenue, p0.currency)
            //可选配置
            adjustAdRevenue.setAdRevenueNetwork(p0.networkFirmId.toString())
            adjustAdRevenue.setAdRevenueUnit(p0.adsourceId)
            adjustAdRevenue.setAdRevenuePlacement(p0.topOnPlacementId)
            //发送收益数据
            Adjust.trackAdRevenue(adjustAdRevenue)
        }
    }

    override fun onInterstitialAdClose(p0: ATAdInfo?) {
    }

    override fun onInterstitialAdVideoStart(p0: ATAdInfo?) {
    }

    override fun onInterstitialAdVideoEnd(p0: ATAdInfo?) {
    }

    override fun onInterstitialAdVideoError(p0: AdError?) {
    }
}