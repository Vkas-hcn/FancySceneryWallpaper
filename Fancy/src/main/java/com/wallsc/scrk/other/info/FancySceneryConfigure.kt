package com.wallsc.scrk.other.info

import android.content.Context
import com.wallsc.scrk.other.FancyCBean
import com.wallsc.scrk.tools.FancyLog
import com.wallsc.scrk.tools.IS_TEST
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.google.firebase.remoteconfig.ktx.remoteConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject

/**
 * Date：2024/1/18
 * Describe:
 */
class FancySceneryConfigure {
    private val TEST = """
{
          "fancy_status_l": "0011",
          "fancy_list_shi": "763",
          "fancy_p_d_s": 30,
          "fancy_p_c": 30,
          "fancy_wa_i": 1,
          "fancy_s_f_w": 99
}
    """.trimIndent()
    private val TIME_1 = 1000 * 60 * 65L

    companion object {
        var checkPeriodTime: Long = 60 * 1000L
        val fancyCBean = FancyCBean()
        var sceneryAdId = "b1fflhav9fk9vp"
        var showFancyAdTime = 0L //显示广告的时间

        fun isFancyBeanAllow(): Boolean {
            if (fancyCBean.isShowP().not()) return false
            if (fancyCBean.isIAllow().not()) return false
            if (System.currentTimeMillis() - showFancyAdTime < fancyCBean.showPeriod) return false
            FancySceneryNetwork.postMai("ispass")
            return true
        }
    }

    private val ioScope = CoroutineScope(Dispatchers.IO)


    fun loadAndFetch(context: Context) {
        fetchData(context, true)
        refresh()
        ioScope.launch {
            while (true) {
                delay(TIME_1)
                fetchData(context)
            }
        }
    }

    private fun fetchData(context: Context, isInit: Boolean = false) {
        runCatching {
            if (isInit) {
                Firebase.initialize(context)
            }
            Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener {
                if (it.isSuccessful) {
                    refresh()
                }
            }
        }
    }

    private fun refresh() {
        if (IS_TEST) {
            JSONObject(TEST).apply {
                fancyCBean.statusS = optString("fancy_status_l", "1111")
                checkPeriodTime = optInt("fancy_p_c", 50) * 1000L
                fancyCBean.setLInfo(optString("fancy_list_shi", "367"))
                fancyCBean.firstInstallTime = optInt("fancy_wa_i", 5) * 1000L * 60
                fancyCBean.showPeriod = optInt("fancy_p_d_s", 40) * 1000
            }
        }
        runCatching {
            val str = Firebase.remoteConfig.getString("fancy_c_wall_scenery")
            JSONObject(str).apply {
                fancyCBean.statusS = optString("fancy_status_l", "1111")
                checkPeriodTime = optInt("fancy_p_c", 50) * 1000L
                fancyCBean.setLInfo(optString("fancy_list_shi", "367"))
                fancyCBean.firstInstallTime = optInt("fancy_wa_i", 5) * 1000L * 60
                fancyCBean.showPeriod = optInt("fancy_p_d_s", 40) * 1000
            }
        }
        runCatching {
            sceneryAdId = Firebase.remoteConfig.getString("fdvcrjpmr").ifBlank { "b1fflhav9fk9vp" }
        }
        FancyLog.e("fancyCBean---->$fancyCBean")
    }


}