package com.wallsc.scrk.other

import android.content.Context
import android.telephony.TelephonyManager
import com.wallsc.scrk.core.mApp
import com.wallsc.scrk.tools.AppInfoTools

/**
 * Date：2024/1/18
 * Describe:
 */
data class FancyCBean(
    // 1开启 2关闭 依次为sim卡检测、是否上报日志、是否显示外弹、是否开启通知提醒
    var statusS: String = "1111",
    var listInfo: ArrayList<String> = arrayListOf("not%20set", "not set", "adjust", "bytedance"),
    var showPeriod: Int = 30 * 1000,
    var firstInstallTime: Long = 5 * 60 * 1000L,
) {

    //sim
    fun isOpenS(): Boolean {
        runCatching {
            return (statusS[0].toString() == "1").not() || allow()
        }
        return allow()
    }

    fun isPostL(): Boolean {
        runCatching {
            return statusS[1].toString() == "1"
        }
        return true
    }

    //外弹
    fun isShowP(): Boolean {
        runCatching {
            return statusS[2].toString() == "1"
        }
        return true
    }

    fun isShowN(): Boolean {
        runCatching {
            return statusS[3].toString() == "1"
        }
        return true
    }

    fun setLInfo(str: String) {
        val list = arrayListOf<String>()
        if (str.isNotEmpty()) {
            for (i in str.indices) {
                when (str[i].toString()) {
                    "1" -> {
                        list.add("fb4a")
                        list.add("facebook")
                    }

                    "2" -> list.add("gclid")
                    "3" -> {
                        list.add("not%20set")
                        list.add("not set")
                    }

                    "4" -> list.add("youtubeads")
                    "5" -> list.add("%7B%22")
                    "6" -> list.add("adjust")
                    "7" -> list.add("bytedance")
                }
            }
        }
        listInfo.clear()
        listInfo.addAll(list)
    }

    private fun allow(): Boolean {
        val telephonyManager = mApp.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.simState != TelephonyManager.SIM_STATE_ABSENT
    }

    private var isCheckIAllow = false
    fun isIAllow(): Boolean {
        if (isCheckIAllow) return true
        if (System.currentTimeMillis() - AppInfoTools.installTime in 0 until firstInstallTime) return false
        isCheckIAllow = true
        return true
    }
}