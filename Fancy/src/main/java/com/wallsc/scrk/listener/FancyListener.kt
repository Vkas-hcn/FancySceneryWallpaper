package com.wallsc.scrk.listener

import android.app.Application

/**
 * Date：2024/1/17
 * Describe:
 */
interface FancyListener {

    fun setWebView(application: Application)

    fun onCreate(application: Application)
}