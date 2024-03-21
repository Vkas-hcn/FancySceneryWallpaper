package com.wallsc.sixth.scape.sixthwallpaper.view

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.wallsc.scrk.core.FancyOpenHelper
import com.wallsc.scrk.listener.LifecycleActivityListener
import com.wallsc.scrk.other.ActivityLifeHelper
import com.wallsc.sixth.scape.sixthwallpaper.R
import com.wallsc.sixth.scape.sixthwallpaper.base.BaseActivity
import com.wallsc.sixth.scape.sixthwallpaper.data.SixthUtils.startContinuousRotation
import com.wallsc.sixth.scape.sixthwallpaper.databinding.ActivityBootBinding
import com.wallsc.sixth.scape.sixthwallpaper.model.NoViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BsjiagkActivity : BaseActivity<ActivityBootBinding, NoViewModel>() {

    private var isResume = false
    private var isShow = false
    private var mJob: Job? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_boot
    }

    override fun getViewModelClass(): Class<NoViewModel> {
        return NoViewModel::class.java
    }

    override fun observeViewModel() {
        LifecycleActivityListener.isInBoot = true
        lifecycleScope.launch {
            binding.aivLoading.post {
                binding.aivLoading.startContinuousRotation()
            }
            delay(3500)
            viewModel.jumpToMain.value = true
        }

        viewModel.jumpToMain.observe(this) {
            Log.i("TAG", "observeViewModel: $isShow")
            if (it && isShow.not()) {
                startActivityFirst<MainActivity>()
                finish()
            }
        }
        FancyOpenHelper.load(this.applicationContext)
    }

    override fun onResume() {
        super.onResume()
        isResume = true
    }

    override fun onPause() {
        super.onPause()
        isResume = false
        mJob?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        LifecycleActivityListener.isInBoot = false
    }

    private fun j() {
        if (ActivityLifeHelper.isAppResume) {
            startActivityFirst<MainActivity>()
        }
        finish()
    }

}