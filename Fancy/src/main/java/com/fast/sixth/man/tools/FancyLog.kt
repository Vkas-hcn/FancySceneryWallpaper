package com.fast.sixth.man.tools

import android.util.Log

/**
 * Date：2024/1/17
 * Describe:
 */


object FancyLog {

    fun i(msg: String, tag: String = "FancyLog") {
        Log.i(tag, msg)
    }

    fun e(msg: String, tag: String = "FancyLog") {
        Log.e(tag, msg)
    }

}