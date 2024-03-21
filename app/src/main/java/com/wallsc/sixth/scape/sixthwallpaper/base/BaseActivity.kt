package com.wallsc.sixth.scape.sixthwallpaper.base


import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wallsc.scrk.other.ActivityLifeHelper

abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        observeViewModel()
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = ViewModelProvider(this).get(getViewModelClass())
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    inline fun <reified T : AppCompatActivity> startActivityFirst() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }

    inline fun <reified T : AppCompatActivity> startActivityWithParamsFirst(params: Bundle) {
        val intent = Intent(this, T::class.java)
        intent.putExtras(params)
        startActivity(intent)
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModelClass(): Class<VM>

    abstract fun observeViewModel()

    override fun onStart() {
        super.onStart()
        ActivityLifeHelper.mAppNum++
        ActivityLifeHelper.isInMyApp = true
    }

    override fun onStop() {
        super.onStop()
        ActivityLifeHelper.mAppNum--
        if (ActivityLifeHelper.mAppNum <= 0) {
            ActivityLifeHelper.mAppNum = 0
            ActivityLifeHelper.isInMyApp = false
        }
    }
}


