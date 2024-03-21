package com.wallsc.sixth.scape.sixthwallpaper.model

import com.wallsc.sixth.scape.sixthwallpaper.R
import com.wallsc.sixth.scape.sixthwallpaper.base.BaseActivity
import com.wallsc.sixth.scape.sixthwallpaper.databinding.ActivityNavWallBinding
import com.wallsc.sixth.scape.sixthwallpaper.view.SjsiqWallActivity

class GjusiaActivity : BaseActivity<ActivityNavWallBinding, NoViewModel>() {
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
           startActivityFirst<SjsiqWallActivity>()
        }
    }
}