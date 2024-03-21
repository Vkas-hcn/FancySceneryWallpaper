package com.wallsc.sixth.scape.sixthwallpaper.view

import com.wallsc.sixth.scape.sixthwallpaper.R
import com.wallsc.sixth.scape.sixthwallpaper.base.BaseActivity
import com.wallsc.sixth.scape.sixthwallpaper.databinding.ActivityNetBinding
import com.wallsc.sixth.scape.sixthwallpaper.model.NoViewModel
import kotlin.properties.Delegates

class SjsiqWallActivity : BaseActivity<ActivityNetBinding, NoViewModel>() {
    private var imgInt by Delegates.notNull<Int>()
    override fun getLayoutId(): Int {
        return R.layout.activity_net
    }

    override fun getViewModelClass(): Class<NoViewModel> {
        return NoViewModel::class.java
    }

    override fun observeViewModel() {
        binding.imgReturn.setOnClickListener {
            finish()
        }
        //todo modify url
        binding.webViewNet.loadUrl("https://www.baidu.com/")
    }

}