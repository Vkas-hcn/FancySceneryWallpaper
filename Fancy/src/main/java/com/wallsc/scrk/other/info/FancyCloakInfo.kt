package com.wallsc.scrk.other.info

import android.content.Context
import android.os.Build
import com.wallsc.scrk.tools.AppInfoTools
import com.wallsc.scrk.tools.FIntImpl
import com.wallsc.scrk.tools.FancyLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.CacheControl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * Date：2024/1/19
 * Describe:
 */

// 0 没拿到 5 黑名单 10 白名单
var mCloakInfo by FIntImpl(0)

class FancyCloakInfo(val context: Context) {

    fun startCheck() {
        if (isNeedRequest().not()) return
        val map = getCloakData(context)
        val urlBuilder =
            "https://carboloy.screenscapewallpaper.com/annie/ramo/tidal".toHttpUrl()
                .newBuilder()
        map.forEach { entry ->
            urlBuilder.addEncodedQueryParameter(
                entry.key, URLEncoder.encode(entry.value, StandardCharsets.UTF_8.toString())
            )
        }
        val request = Request.Builder().get().tag(map).url(urlBuilder.build())
            .cacheControl(CacheControl.FORCE_NETWORK).build()
        val ioScope = CoroutineScope(Dispatchers.IO)
        ioScope.launch {
            while (isNeedRequest()) {
                CoroutineScope(Dispatchers.IO).launch {
                    requestHttp(request)
                }
                delay(9190)
            }
        }
    }

    private fun requestHttp(request: Request) {
        val client = OkHttpClient()
        runCatching {
            val body = client.newCall(request).execute()
            if (body.isSuccessful && body.code == 200) {
                val res = body.body?.string()
                FancyLog.e("res---$res")
                when (res) {
                    "valhalla" -> {
                        mCloakInfo = 5
                    }

                    "carven" -> {
                        mCloakInfo = 10
                    }
                }
            }
        }
    }

    private fun getCloakData(context: Context): Map<String, String> {
        return mapOf(
            "carte" to AppInfoTools.getDId(), // distinct_id
            "hoodlum" to System.currentTimeMillis().toString(), // client_ts
            "genesis" to Build.MODEL,//device_model
            "faithful" to "com.screen.scape.wallpaper",// bundle_id
            "bodice" to Build.VERSION.RELEASE, // os_version
            "carpal" to AppInfoTools.gaid, // gaid
            "trw" to AppInfoTools.getPhoneId(context), // android_id
            "depute" to "exam", // os
            "culture" to AppInfoTools.getAppVersion(context), // app_version
            "necrosis" to Build.BRAND, // brand
        )
    }


    private fun isNeedRequest(): Boolean {
        return mCloakInfo == 0
    }
}