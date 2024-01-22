package com.fast.sixth.man.other

import android.app.Activity
import com.anythink.core.api.ATAdInfo
import com.anythink.core.api.AdError
import com.anythink.interstitial.api.ATInterstitial
import com.fast.sixth.man.core.FancySceneryHelper
import com.fast.sixth.man.core.mApp
import com.fast.sixth.man.open.TLoadImpl
import com.fast.sixth.man.other.info.FancySceneryNetwork
import com.fast.sixth.man.other.info.FancyTopIListener
import com.fast.sixth.man.tools.AppInfoTools
import com.fast.sixth.man.tools.FancyLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

/**
 * Date：2024/1/19
 * Describe:
 */
object FancyAdFetch {
    private val TAG_LOG = "FancyAd--->"
    private var isFetching = false
    private val tLoadImpl by lazy { TLoadImpl(mApp) }
    private var fancyAd: Any? = null
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val loadListener = object : FancyTopIListener() {
        override fun onInterstitialAdLoaded() {
            super.onInterstitialAdLoaded()
            FancyLog.e("onInterstitialAdLoaded --->", TAG_LOG)
            isFetching = false
            lastLoadTime = System.currentTimeMillis()
            FancySceneryNetwork.postMai("getprogress")
            if (isReadyAd()) {
                FancySceneryHelper.eventNow()
            }
        }

        override fun onInterstitialAdLoadFail(p0: AdError?) {
            super.onInterstitialAdLoadFail(p0)
            FancyLog.e("onInterstitialAdLoadFail --->$p0", TAG_LOG)
            isFetching = false
        }
    }

    fun load() {
        mainScope.launch {
            if (isCanLoad().not()) return@launch
            FancySceneryNetwork.postMai("reqprogress")
            fancyAd = tLoadImpl.loadI()
            isFetching = true
            lastFetchTime = System.currentTimeMillis()
        }
    }

    private var lastFetchTime = 0L
    private val TIME = 1000L * 60
    private fun isCanLoad(): Boolean {
        if (isFetching) {
            if (System.currentTimeMillis() - lastFetchTime in 0 until TIME) {
                return false
            }
        }
        val ad = fancyAd
        if (ad != null && ad is ATInterstitial) {
            if (ad.isAdReady) {
                FancyLog.e("have cache ad --->", TAG_LOG)
                return true
            }
        }
        return false
    }

    fun showMe(activity: Activity, showOver: () -> Unit): Boolean {
        val ad = fancyAd
        if (isCanUserAd(ad).not()) return false
        when (ad) {
            is ATInterstitial -> {
                ad.setAdListener(object : FancyTopIListener() {
                    override fun onInterstitialAdShow(p0: ATAdInfo?) {
                        super.onInterstitialAdShow(p0)
                        p0?.let {
                            FancySceneryNetwork.postLitton(createAdValueInfo(p0))
                        }
                        load()
                    }

                    override fun onInterstitialAdClose(p0: ATAdInfo?) {
                        super.onInterstitialAdClose(p0)
                        showOver.invoke()
                        load()
                    }

                    override fun onInterstitialAdVideoError(p0: AdError?) {
                        super.onInterstitialAdVideoError(p0)
                        showOver.invoke()
                    }
                })
                ad.show(activity)
                return true
            }
        }
        return false
    }

    var lastLoadTime = 0L
    private val TIME_LIMIT = 1000 * 60 * 59L
    private fun isCanUserAd(ad: Any?): Boolean {
        if (lastLoadTime != 0L && System.currentTimeMillis() - lastLoadTime > TIME_LIMIT) return false
        when (ad) {
            is ATInterstitial -> {
                return ad.isAdReady
            }
        }
        return false
    }

    private fun createAdValueInfo(atAdInfo: ATAdInfo): JSONObject {
        return JSONObject().apply {
            put("guru", atAdInfo.ecpm * 1000000)
            put("bosonic", atAdInfo.currency)
            put("teapot", AppInfoTools.netType[atAdInfo.networkFirmId] ?: "notConfigure")
            put("yoga", "Topon")
            put("taper", atAdInfo.topOnPlacementId)
            put("stasis", "iii")
            put("debonair", atAdInfo.topOnAdFormat)
        }
    }

    fun isReadyAd(): Boolean {
        return isCanUserAd(fancyAd)
    }

}