package com.fast.sixth.man.sixthwallpaper.base

import android.app.Application

class App:Application() {
    companion object{
        lateinit var appContext: Application
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}