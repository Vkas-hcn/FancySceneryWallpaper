package com.fast.sixth.man.core

import android.app.Application
import android.content.Context
import android.os.Build
import android.webkit.WebView
import com.fast.sixth.man.listener.AppListener
import com.fast.sixth.man.other.ActivityLifeHelper

/**
 * Date：2024/1/17
 * Describe:
 */

lateinit var mApp: Application

class AppImpl : AppListener {
    private lateinit var initCore: InitCore
    private lateinit var activityLifeHelper: ActivityLifeHelper

    override fun mainProgressBefore(context: Context) {

    }

    override fun allProgress(context: Context) {

    }

    override fun mainProgress(app: Application) {
        activityLifeHelper = ActivityLifeHelper(app)
        activityLifeHelper.register(app)
        initCore = InitCore(app)
        initCore.startInit()
    }
}