package com.wallsc.scrk.other

import android.app.Activity
import com.anythink.core.api.ATAdInfo
import com.anythink.core.api.AdError
import com.anythink.interstitial.api.ATInterstitial
import com.facebook.appevents.AppEventsLogger
import com.wallsc.scrk.core.FancySceneryHelper
import com.wallsc.scrk.core.mApp
import com.wallsc.scrk.open.TLoadImpl
import com.wallsc.scrk.other.info.FancySceneryConfigure
import com.wallsc.scrk.other.info.FancySceneryNetwork
import com.wallsc.scrk.other.info.FancyTopIListener
import com.wallsc.scrk.tools.AppInfoTools
import com.wallsc.scrk.tools.FancyLog
import com.wallsc.scrk.tools.IS_TEST
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.Currency

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
            FancySceneryNetwork.postMai(
                "showfailer", map = mapOf(
                    "string" to "$p0"
                )
            )
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
                return false
            }
        }
        return true
    }

    fun showMe(activity: Activity, showOver: () -> Unit): Boolean {
        val ad = fancyAd
        FancySceneryHelper.retryNum = 0
        if (isCanUserAd(ad).not()) {
            load()
            activity.finish()
            return false
        }
        when (ad) {
            is ATInterstitial -> {
                ad.setAdListener(object : FancyTopIListener() {
                    override fun onInterstitialAdShow(p0: ATAdInfo?) {
                        super.onInterstitialAdShow(p0)
                        FancySceneryNetwork.postMai("showsuccess")
                        p0?.let {
                            FancySceneryNetwork.postLitton(createAdValueInfo(p0))
                            //todo delete
                            if (IS_TEST) return
                            runCatching {
                                AppEventsLogger.newLogger(mApp).logPurchase(
                                    (it.ecpm / 1000).toBigDecimal(), Currency.getInstance("USD")
                                )
                            }
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
                fancyAd = null
                return true
            }
        }
        load()
        activity.finish()
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