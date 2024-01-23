package com.fast.sixth.man.tools

import android.os.Bundle
import com.fast.sixth.man.open.bro.BaseFActivity
import com.fast.sixth.man.other.info.FancySceneryNetwork

/**
 * Date：2024/1/22
 * Describe:
 * 外弹
 */
class SceneryActivity : BaseFActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FancySceneryNetwork.postMai("startup")
    }
}