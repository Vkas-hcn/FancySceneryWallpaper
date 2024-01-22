package com.fast.sixth.man.tools

import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import com.fast.sixth.man.core.mApp
import com.fast.sixth.man.open.FSSssImpl
import org.json.JSONObject
import java.security.MessageDigest
import java.util.Locale
import java.util.UUID

/**
 * Date：2024/1/19
 * Describe:
 */
object AppInfoTools {
    var their = "" //operator
    var webSetStr = ""//user_agent
    var installTime = 0L
    var updateTime = 0L
    var gaid by FSSssImpl()
    private var mPhoneId by FSSssImpl()

    @SuppressLint("HardwareIds")
    fun getPhoneId(context: Context): String {
        if (mPhoneId.isNotBlank()) return mPhoneId
        mPhoneId =
            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID).ifBlank {
                UUID.randomUUID().toString()
            }
        return mPhoneId
    }

    private var mDistinctId = ""
    fun getDId(): String {
        if (mDistinctId.isNotBlank()) return mDistinctId
        mDistinctId = MessageDigest.getInstance("MD5").digest(getPhoneId(mApp).toByteArray())
            .joinToString("") { "%02x".format(it) }
        return mDistinctId
    }

    fun getAppVersion(context: Context): String {
        return try {
            val versionName = getPackageInfo(context).versionName
            versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            ""
        }
    }

    fun getPackageInfo(context: Context): PackageInfo {
        return context.packageManager.getPackageInfo(context.packageName, 0)
    }

    fun isShowIcon(): Boolean {
        val str = Build.BRAND
        val str2 = Build.MANUFACTURER
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.O && str.equals(
            "huawei", ignoreCase = true
        ) || str2.equals("huawei", ignoreCase = true)
    }

    fun createSuitor(): JSONObject {
        return JSONObject().apply {
            put("valine", "${Locale.getDefault().language}_${Locale.getDefault().country}")
            put("their", "")
            put("carpal", gaid)
            put("culture", getAppVersion(mApp))
        }
    }


    val netType = hashMapOf(
        Pair(1, "Facebook"),
        Pair(3, "Inmobi"),
        Pair(5, "Applovin"),
        Pair(6, "Mintegral"),
        Pair(9, "Chartboost"),
        Pair(10, "Tapjoy"),
        Pair(13, "Vungle"),
        Pair(14, "Adcolony"),
        Pair(34, "Yandex"),
        Pair(59, "Bigo"),
        Pair(50, "Pangle"),
    )

    fun isPhoneClose():Boolean{
        with(mApp){
            return (getSystemService(Context.POWER_SERVICE) as PowerManager).isInteractive && (getSystemService(
                Context.KEYGUARD_SERVICE
            ) as KeyguardManager).isDeviceLocked
        }
    }

}