package com.fast.sixth.man.sixthwallpaper.view

import com.fast.sixth.man.sixthwallpaper.R
import com.fast.sixth.man.sixthwallpaper.base.BaseActivity
import com.fast.sixth.man.sixthwallpaper.databinding.ActivityNavWallBinding
import com.fast.sixth.man.sixthwallpaper.model.NoViewModel

class NavActivity : BaseActivity<ActivityNavWallBinding, NoViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_nav_wall
    }

    override fun getViewModelClass(): Class<NoViewModel> {
        return NoViewModel::class.java
    }

    override fun observeViewModel() {
        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.atvPrivacyAgreement.setOnClickListener {
           startActivityFirst<WebWallActivity>()
        }
    }
}