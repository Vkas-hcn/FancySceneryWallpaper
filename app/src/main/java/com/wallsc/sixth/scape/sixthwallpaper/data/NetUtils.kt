package com.wallsc.sixth.scape.sixthwallpaper.data

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import com.wallsc.sixth.scape.sixthwallpaper.base.App

object NetUtils {
    private val sharedPreferences by lazy {
        App.appContext.getSharedPreferences(
            "fancy_scenery",
            Context.MODE_PRIVATE
        )
    }
    var uuid_fancy_scenery: String = ""
        set(value) {
            sharedPreferences.edit().run {
                putString("uuid_fancy_scenery", value)
                commit()
            }
            field = value
        }
        get() = sharedPreferences.getString("uuid_fancy_scenery", "").toString()

    var cloak_fancy_scenery = ""
        set(value) {
            sharedPreferences.edit().run {
                putString("cloak_fancy_scenery", value)
                commit()
            }
            field = value
        }
        get() = sharedPreferences.getString("cloak_fancy_scenery", "").toString()

    fun setCloakData(context: Context): Map<String, String> {
        return mapOf(
            "carte" to uuid_fancy_scenery, // distinct_id
            "hoodlum" to System.currentTimeMillis().toString(), // client_ts
            "genesis" to Build.MODEL,//device_model
            "faithful" to "com.wallpapercollection.colorfulscenery",// bundle_id
            "bodice" to Build.VERSION.RELEASE, // os_version
            "carpal" to "", // gaid
            "trw" to Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID), // android_id
            "depute" to "exam", // os
            "culture" to getAppVersion(context), // app_version
            "prorogue" to "", // key
            "erda" to "", // sdk_ver
            "necrosis" to Build.BRAND, // brand
        )
    }
    private fun getAppVersion(context: Context): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            val versionName = packageInfo.versionName
            versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            ""
        }
    }
}