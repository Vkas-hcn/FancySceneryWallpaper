package com.fast.sixth.man.listener

import android.app.Application
import android.content.Context

/**
 * Date：2024/1/17
 * Describe:
 */
interface AppListener {

    fun mainProgressBefore(context: Context)

    fun allProgress(context: Context)

    fun mainProgress(app: Application)
}