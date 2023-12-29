package com.fast.sixth.man.sixthwallpaper.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.fast.sixth.man.sixthwallpaper.R
import com.fast.sixth.man.sixthwallpaper.base.BaseActivity
import com.fast.sixth.man.sixthwallpaper.data.SixthUtils
import com.fast.sixth.man.sixthwallpaper.databinding.ActivityMainBinding
import com.fast.sixth.man.sixthwallpaper.model.MainViewModel
import com.fast.sixth.man.sixthwallpaper.model.NoViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private lateinit var adapterTop: WallPaperAdapter
    private lateinit var adapterBottom: WallPaperAdapter
    private lateinit var bottomList: MutableList<String>
    private lateinit var topList: MutableList<String>
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun observeViewModel() {
        initTopAdapter()
        initBottomAdapter()
        initView()
    }

    private fun initView() {
        binding.imgSetting.setOnClickListener {
            startActivityFirst<NavActivity>()
        }
        binding.imgLeft.setOnClickListener {
            scrollToPreviousItem()
        }
        binding.imgRight.setOnClickListener {
            scrollToNextItem()
        }
        viewModel.jumpToSetting.observe(this) {
            jumpToSetting(it)
        }
    }

    private fun initTopAdapter() {
        topList = SixthUtils.getBannerData() as MutableList<String>
        adapterTop = WallPaperAdapter(1, topList)
        //左右滑动
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMainTop.layoutManager = linearLayoutManager
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvMainTop)
        binding.rvMainTop.adapter = adapterTop
        adapterTop.setOnItemClickListener(object : WallPaperAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                viewModel.jumpToSetting.value = topList[position]
            }
        })
        binding.rvMainTop.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // 滑动到顶部的状态
                val isFirstItemVisible =
                    linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0
                // 滑动到底部的状态
                val isLastItemVisible =
                    linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapterTop.itemCount - 1

                if (isFirstItemVisible) {
                    binding.imgLeft.visibility = View.INVISIBLE
                } else {
                    binding.imgLeft.visibility = View.VISIBLE
                }
                if (isLastItemVisible) {
                    binding.imgRight.visibility = View.INVISIBLE
                } else {
                    binding.imgRight.visibility = View.VISIBLE
                }
            }
        })

    }

    private fun initBottomAdapter() {
        bottomList = SixthUtils.getListData() as MutableList<String>
        adapterBottom = WallPaperAdapter(2, bottomList)
        //左右滑动
        binding.rvMainBottom.layoutManager = GridLayoutManager(this, 2)

        binding.rvMainBottom.adapter = adapterBottom
        adapterBottom.setOnItemClickListener(object : WallPaperAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                viewModel.jumpToSetting.value = bottomList[position]
            }
        })

    }

    // 滑动到下一个项
    private fun scrollToNextItem() {
        try {
            val nextItemPosition = linearLayoutManager.findFirstVisibleItemPosition() + 1
            binding.rvMainTop.smoothScrollToPosition(nextItemPosition)
        } catch (_: Exception) {
        }
    }

    // 滑动到上一个项
    private fun scrollToPreviousItem() {
        try {
            val previousItemPosition = linearLayoutManager.findFirstVisibleItemPosition() - 1
            binding.rvMainTop.smoothScrollToPosition(previousItemPosition)
        } catch (_: Exception) {
            binding.rvMainTop.smoothScrollToPosition(0)
        }
    }

    private fun jumpToSetting(name: String) {
        val bundle = Bundle()
        bundle.putString("img_name", name)
        startActivityWithParamsFirst<SettingActivity>(bundle)
    }
}