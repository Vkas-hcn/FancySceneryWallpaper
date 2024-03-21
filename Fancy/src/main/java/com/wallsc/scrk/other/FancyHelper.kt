package com.wallsc.scrk.other

import android.app.Application
import android.content.Context
import com.wallsc.scrk.core.FancySceneryHelper
import com.wallsc.scrk.listener.AppListener
import com.wallsc.scrk.open.FancyUser
import com.wallsc.scrk.other.info.FancyCloakInfo
import com.wallsc.scrk.other.info.FancySceneryConfigure

/**
 * Date：2024/1/17
 * Describe:
 */
class FancyHelper : AppListener {
    private val fancySceneryConfigure by lazy { FancySceneryConfigure() }
    override fun mainProgressBefore(context: Context) {

    }

    override fun allProgress(context: Context) {
    }

    override fun mainProgress(app: Application) {
        fancySceneryConfigure.loadAndFetch(app)
        FancyCloakInfo(app).startCheck()
        FancyUser(app).startCheck()
        FancySceneryHelper.startCheck()
    }


}