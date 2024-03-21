package com.wallsc.scrk.core

import android.app.Application
import android.content.Context
import com.wallsc.scrk.listener.AppListener
import com.wallsc.scrk.other.ActivityLifeHelper

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
        initCore.checkTask()
    }
}