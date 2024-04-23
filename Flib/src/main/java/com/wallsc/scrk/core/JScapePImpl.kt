package com.wallsc.scrk.core

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.PersistableBundle

/**
 * Date：2024/3/21
 * Describe:
 */
class JScapePImpl(val tag: String) {
    fun misjKslgSh(context: Context): Boolean { //这个函数可以放到其他文件减少关联
        val am: ActivityManager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val list0: List<ActivityManager.RunningAppProcessInfo> = am.runningAppProcesses
        for (info in list0) {
            if (!info.processName.equals(context.applicationInfo.processName) || info.importance != 100) {
                continue
            }
            return true
        }
        return false
    }

    private fun sijkkl(context: Context, n: String?): Boolean {
        if (n == null) return false
        runCatching {
            val cn = ComponentName(context, n)
            val intent = Intent()
            intent.setClassName(context, cn.className)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            return true
        }
        return false
    }

    fun sjuaigkInfk(bundle: PersistableBundle, context: Context) {
        sijkkl(context, bundle.getString(tag))
    }
}