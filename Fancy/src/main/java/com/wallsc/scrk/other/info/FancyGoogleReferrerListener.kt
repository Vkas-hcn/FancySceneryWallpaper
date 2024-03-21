package com.wallsc.scrk.other.info

import android.os.Build
import com.android.installreferrer.api.InstallReferrerStateListener
import com.android.installreferrer.api.ReferrerDetails
import com.wallsc.scrk.open.mReferrerDetails
import com.wallsc.scrk.tools.AppInfoTools
import org.json.JSONObject

/**
 * Date：2024/1/19
 * Describe:
 */
open class FancyGoogleReferrerListener : InstallReferrerStateListener {
    override fun onInstallReferrerSetupFinished(p0: Int) {}

    override fun onInstallReferrerServiceDisconnected() = Unit

    open fun getJson(referrerDetails: ReferrerDetails): JSONObject {
        return JSONObject().apply {
            put("eddie", "build/${Build.ID}")
            put("copy", referrerDetails.installReferrer)
            put("patina", referrerDetails.installVersion)
            put("salve", AppInfoTools.webSetStr)
            put("respect", "sardinia")
            put("tactual", referrerDetails.referrerClickTimestampSeconds)
            put("burgeon", referrerDetails.installBeginTimestampSeconds)
            put("epitome", referrerDetails.referrerClickTimestampServerSeconds)
            put("glaucous", referrerDetails.installBeginTimestampServerSeconds)
            put("bilge", AppInfoTools.installTime)
            put("caveman", AppInfoTools.updateTime)
            mReferrerDetails = this.toString()
        }
    }
}