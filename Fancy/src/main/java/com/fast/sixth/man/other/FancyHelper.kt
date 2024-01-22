package com.fast.sixth.man.other

import android.app.Application
import android.content.Context
import com.fast.sixth.man.core.FancySceneryHelper
import com.fast.sixth.man.listener.AppListener
import com.fast.sixth.man.open.FancyUser
import com.fast.sixth.man.other.info.FancyCloakInfo
import com.fast.sixth.man.other.info.FancySceneryConfigure

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