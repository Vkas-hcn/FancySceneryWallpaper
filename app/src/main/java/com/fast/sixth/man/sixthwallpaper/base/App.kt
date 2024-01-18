package com.fast.sixth.man.sixthwallpaper.base

import android.app.Application
import com.fast.sixth.man.FancyAppCore
import com.fast.sixth.man.listener.FancyListener

class App : Application() {
    companion object {
        lateinit var appContext: Application
    }

    private lateinit var fancyListener: FancyListener

    override fun onCreate() {
        super.onCreate()
        appContext = this
        fancyListener = FancyAppCore()
        fancyListener.setWebView(this@App)
        fancyListener.onCreate(this@App)
    }
}