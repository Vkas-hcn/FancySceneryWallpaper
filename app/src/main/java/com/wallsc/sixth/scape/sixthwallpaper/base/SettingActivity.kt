package com.wallsc.sixth.scape.sixthwallpaper.base

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.wallsc.sixth.scape.sixthwallpaper.R
import com.wallsc.sixth.scape.sixthwallpaper.data.SixthUtils
import com.wallsc.sixth.scape.sixthwallpaper.databinding.ActivitySettingBinding
import com.wallsc.sixth.scape.sixthwallpaper.model.SettingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class SettingActivity : BaseActivity<ActivitySettingBinding, SettingViewModel>() {
    private var imgInt by Delegates.notNull<Int>()
    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun getViewModelClass(): Class<SettingViewModel> {
        return SettingViewModel::class.java
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun observeViewModel() {
        val imgName = intent.getStringExtra("img_name")
        imgInt = SixthUtils.getImgByName(imgName ?: "ic_1")
        binding.clSetting.background = resources.getDrawable(imgInt)
        initView()
    }

    private fun initView() {
        binding.imgReturn.setOnClickListener {
            finish()
        }
        binding.imgDown.setOnClickListener {
            viewModel.downImageInt(this, imgInt)
        }
        binding.appCompatTextView.setOnClickListener {
            binding.clDialog.visibility = View.VISIBLE
        }
        binding.tvCancel.setOnClickListener {
            binding.clDialog.visibility = View.GONE
        }
        binding.tvLock.setOnClickListener {
            setWallpaper(WallpaperManager.FLAG_LOCK)
        }
        binding.tvHome.setOnClickListener {
            setWallpaper(WallpaperManager.FLAG_SYSTEM)
        }
        binding.tvBoth.setOnClickListener {
            setWallpaperBoth()
        }
    }

    private fun setWallpaper(type: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.setWallFun(imgInt, type, this@SettingActivity, loadBefore = {
                binding.progressBar.visibility = View.VISIBLE
            }, loadAfter = {
                binding.progressBar.visibility = View.GONE
                binding.clDialog.visibility = View.GONE
                Toast.makeText(this@SettingActivity, "The setup was successful", Toast.LENGTH_SHORT)
                    .show()
            })
        }
    }

    private fun setWallpaperBoth() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.setWallBothFun(imgInt, this@SettingActivity, loadBefore = {
                binding.progressBar.visibility = View.VISIBLE
            }, loadAfter = {
                binding.progressBar.visibility = View.GONE
                binding.clDialog.visibility = View.GONE
                Toast.makeText(this@SettingActivity, "The setup was successful", Toast.LENGTH_SHORT)
                    .show()
            })
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            0x234 -> {
                viewModel.downImageInt(this, imgInt)
            }
        }
    }
}