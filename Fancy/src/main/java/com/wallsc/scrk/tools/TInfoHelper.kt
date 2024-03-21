package com.wallsc.scrk.tools

import android.content.Context
import android.os.Build
import org.json.JSONObject
import java.net.URLEncoder
import java.util.Locale
import java.util.TimeZone
import java.util.UUID

/**
 * Date：2024/1/19
 * Describe:
 */
class TInfoHelper(val context: Context) {
    private val URL_FANCY ="https://test-pillage.wallpapercollectioncolorfulscenery.com/fl/cohosh/temple"
//        if (BuildConfig.DEBUG) "https://test-pillage.wallpapercollectioncolorfulscenery.com/fl/cohosh/temple"
//        else "https://pillage.wallpapercollectioncolorfulscenery.com/suburbia/distort"

    fun createBasicInfo(): JSONObject {
        return JSONObject().apply {
            put("suitor", AppInfoTools.createSuitor())
            put("anathema", JSONObject().apply {
                put("faithful", context.packageName)
                put("u", TimeZone.getDefault().rawOffset / 3600000)
                put("mason", "")
                put("avocate", UUID.randomUUID().toString())
                put("trw", AppInfoTools.getPhoneId(context))
                put("genesis", Build.MODEL)
                put("bodice", Build.VERSION.RELEASE)
                put("hoodlum", System.currentTimeMillis())
            })
            put("shrine", shrineJS())
        }
    }

    private fun shrineJS(): JSONObject {
        return JSONObject().apply {
            put("necrosis", Build.BRAND)
            put("aaas", Build.MANUFACTURER)
            put("tomb", Locale.getDefault().country)
            put("depute", "exam")
            put("carte", AppInfoTools.getDId())
        }
    }

    fun getUFancy(): String {
        return "$URL_FANCY?trw=${encode(AppInfoTools.getPhoneId(context))}&necrosis=${encode(Build.BRAND)}"
    }

    private fun encode(string: String): String {
        return URLEncoder.encode(string, "UTF-8")
    }
}