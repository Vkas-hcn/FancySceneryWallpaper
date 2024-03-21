package com.wallsc.scrk.other.info

import com.wallsc.scrk.open.FSSssImpl

/**
 * Date：2024/1/18
 * Describe:
 */
object SpFancy {

    var fancyNetwork by FSSssImpl()

    fun isAdJUser():Boolean{
        if (fancyNetwork.isBlank()) return false
        if (fancyNetwork.contains("organic", true)) return false
        return true
    }


}