package com.wallsc.sixth.scape.sixthwallpaper.base

import android.app.Application
import com.wallsc.scrk.FancyAppCore
import com.wallsc.scrk.listener.FancyListener
import com.wallsc.sixth.scape.sixthwallpaper.R

class App : Application() {
    companion object {
        lateinit var appContext: Application
    }

    private lateinit var fancyListener: FancyListener

    override fun onCreate() {
        super.onCreate()
        appContext = this
        fancyListener = FancyAppCore()
        FancyAppCore.appName = getString(R.string.app_name)
        fancyListener.setWebView(this@App)
        fancyListener.onCreate(this@App)
    }
}