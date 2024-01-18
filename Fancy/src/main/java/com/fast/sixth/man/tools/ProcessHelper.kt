package com.fast.sixth.man.tools

import android.app.ActivityManager
import android.content.Context

/**
 * Date：2024/1/17
 * Describe:
 */
class ProcessHelper(val context: Context) {
    fun isMain(): Boolean {
        return try {
            context.packageName == getPName()
        } catch (e: Exception) {
            false
        }
    }

    private fun getPName(): String? {
        with(context) {
            val pid = android.os.Process.myPid()
            val am = getSystemService(android.app.Application.ACTIVITY_SERVICE) as ActivityManager
            val runningApps = am.runningAppProcesses ?: return null
            for (procInfo in runningApps) {
                if (procInfo.pid == pid) {
                    return procInfo.processName
                }
            }
            return null
        }
    }
}