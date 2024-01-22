package com.fast.sixth.man.tools

import android.util.Log

/**
 * Date：2024/1/17
 * Describe:
 */

//todo hide
val IS_TEST = true

object FancyLog {

    fun i(msg: String, tag: String = "FancyLog") {
        //todo hide
        Log.i(tag, msg)
    }

    fun e(msg: String, tag: String = "FancyLog") {
        //todo hide
        Log.e(tag, msg)
    }

}