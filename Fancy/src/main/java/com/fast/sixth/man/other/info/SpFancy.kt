package com.fast.sixth.man.other.info

import com.fast.sixth.man.open.FSSssImpl

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