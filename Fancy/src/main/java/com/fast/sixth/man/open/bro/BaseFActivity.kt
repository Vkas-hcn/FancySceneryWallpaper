package com.fast.sixth.man.open.bro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fast.sixth.man.core.FancySceneryHelper
import com.fast.sixth.man.other.FancyAdFetch

/**
 * Date：2024/1/22
 * Describe:
 */
abstract class BaseFActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FancySceneryHelper.retryNum = 0
        val isShow = FancyAdFetch.showMe(this, showOver = {
            finishAndRemoveTask()
        })
        if (isShow.not()) {
            FancyAdFetch.load()
            finishAndRemoveTask()
        }
    }

}