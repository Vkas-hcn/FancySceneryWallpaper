package com.wallsc.scrk.open

import android.content.Context
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.ReferrerDetails
import com.wallsc.scrk.other.info.FancyGoogleReferrerListener
import com.wallsc.scrk.other.info.FancySceneryConfigure
import com.wallsc.scrk.other.info.FancySceneryNetwork
import com.wallsc.scrk.other.info.SpFancy
import com.wallsc.scrk.tools.FancyLog
import com.wallsc.scrk.tools.IS_TEST
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.Locale

/**
 * Date：2024/1/19
 * Describe:
 */

var mReferrerStr by FSSssImpl(tag = "google")
var mReferrerDetails by FSSssImpl(tag = "google")

private val isLimitCountry: Boolean by lazy {
    arrayListOf(
        "SG", "SGP", "US", "USA", "IN", "IND", "KE", "KEN"
    ).find { it == Locale.getDefault().country } != null
}

private val notLimitType = arrayListOf("adjust", "bytedance")

private fun isLimitType(type: String): Boolean {
    if (isLimitCountry.not()) return false
    if (notLimitType.contains(type)) return false
    return true
}


class FancyUser(val context: Context) {
    companion object {
        var referrerTag = ""
            get() {
                return field.ifBlank { SpFancy.fancyNetwork }
            }

        fun isTargetUser(): Boolean {
            if (mReferrerStr.isBlank()) return false
            ArrayList(FancySceneryConfigure.fancyCBean.listInfo).filter { isLimitType(it).not() }
                .forEach {
                    if (mReferrerStr.contains(it)) {
                        referrerTag = it
                        return true
                    }
                }
            return false
        }
    }

    fun startCheck() {
        if (mReferrerStr.isNotBlank()) {
            if (mReferrerDetails.isNotBlank()) {
                runCatching {
                    FancySceneryNetwork.postInsEvent(JSONObject(mReferrerDetails))
                }
            }
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            while (mReferrerStr.isBlank()) {
                requestReferrer()
                delay(7890)
            }
        }
    }


    private fun requestReferrer() {
        val referrerClient = InstallReferrerClient.newBuilder(context).build()
        referrerClient.apply {
            startConnection(object : FancyGoogleReferrerListener() {
                override fun onInstallReferrerSetupFinished(responseCode: Int) {
                    try {
                        when (responseCode) {
                            InstallReferrerClient.InstallReferrerResponse.OK -> {
                                val response: ReferrerDetails = installReferrer
                                mReferrerStr = response.installReferrer
                                FancyLog.e("mGoogleReferStr-->$mReferrerStr")
                                //todo delete
                                if (IS_TEST) {
                                    mReferrerStr += "adjust"
                                }
                                //post install
//                                success.invoke(response)
                                endConnection()
                                FancySceneryNetwork.postInsEvent(getJson(response))
                            }

                            else -> {
                                endConnection()
                            }
                        }
                    } catch (e: Exception) {
                        endConnection()
                    }
                }
            })

        }
    }

}