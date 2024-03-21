package com.wallsc.scrk

import android.app.Application
import android.os.Build
import android.webkit.WebView
import com.wallsc.scrk.core.AppImpl
import com.wallsc.scrk.core.mApp
import com.wallsc.scrk.listener.FancyListener
import com.wallsc.scrk.other.FancyHelper
import com.wallsc.scrk.tools.ProcessHelper

/**
 * Date：2024/1/17
 * Describe:
 */
class FancyAppCore : FancyListener {
    private val helper by lazy { ProcessHelper(mApp) }
    private val appListenerList by lazy { arrayListOf(AppImpl(), FancyHelper()) }

    companion object {
        var appName = ""
    }

    override fun setWebView(application: Application) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val processName: String = Application.getProcessName()
            if (!application.packageName.equals(processName)) {
                WebView.setDataDirectorySuffix(processName)
            }
        }
    }

    override fun onCreate(application: Application) {
        mApp = application
        val isMineProcess = helper.isMain()
        if (isMineProcess) {
            appListenerList.forEach {
                it.mainProgressBefore(application)
            }
        }
        appListenerList.forEach {
            it.allProgress(application)
        }
        if (isMineProcess) {
            appListenerList.forEach {
                it.mainProgress(application)
            }
            helper.adjFancyInit()
        }
    }

}