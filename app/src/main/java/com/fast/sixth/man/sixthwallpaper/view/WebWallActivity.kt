package com.fast.sixth.man.sixthwallpaper.view

import com.fast.sixth.man.sixthwallpaper.R
import com.fast.sixth.man.sixthwallpaper.base.BaseActivity
import com.fast.sixth.man.sixthwallpaper.databinding.ActivityNetBinding
import com.fast.sixth.man.sixthwallpaper.model.NoViewModel
import kotlin.properties.Delegates

class WebWallActivity : BaseActivity<ActivityNetBinding, NoViewModel>() {
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
        binding.webViewNet.loadUrl("https://www.baidu.com/")
    }

}