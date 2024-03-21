package com.wallsc.sixth.scape.sixthwallpaper.view

import android.content.Context
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.wallsc.scrk.core.FancyOpenHelper
import com.wallsc.scrk.listener.LifecycleActivityListener
import com.wallsc.scrk.other.ActivityLifeHelper
import com.wallsc.sixth.scape.sixthwallpaper.R
import com.wallsc.sixth.scape.sixthwallpaper.base.BaseActivity
import com.wallsc.sixth.scape.sixthwallpaper.data.NetUtils
import com.wallsc.sixth.scape.sixthwallpaper.data.SixthUtils.startContinuousRotation
import com.wallsc.sixth.scape.sixthwallpaper.databinding.ActivityBootBinding
import com.wallsc.sixth.scape.sixthwallpaper.model.NoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.CacheControl
import okhttp3.Call
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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
        getLock()
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

    private fun getLock() {
        if (NetUtils.uuid_fancy_scenery.isEmpty()) {
            NetUtils.uuid_fancy_scenery = java.util.UUID.randomUUID().toString()
        }
        lifecycleScope.launch(Dispatchers.IO) {
            getRecordNetData(this@BsjiagkActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        isResume = true
        showJob()
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

    private fun showJob() {
        mJob?.cancel()
        mJob = lifecycleScope.launch {
            delay(400)
            while (isResume) {
                isShow =
                    FancyOpenHelper.showFancySp(this@BsjiagkActivity, binding.adLayout, showFinish = {
                        j()
                    })
                if (isShow) {
                    break
                }
                delay(411)
            }
        }
    }

    private fun j() {
        if (ActivityLifeHelper.isAppResume) {
            startActivityFirst<MainActivity>()
        }
        finish()
    }

    private fun getRecordNetData(context: Context) {
        if (NetUtils.cloak_fancy_scenery.isNotEmpty()) {
            return
        }
        val map = NetUtils.setCloakData(context)
        Log.e("TAG", "map-data=${map} ")
        val client = OkHttpClient()
        val urlBuilder =
            "https://slur.wallpapercollectioncolorfulscenery.com/mould/ontario".toHttpUrl()
                .newBuilder()
        map.forEach { entry ->
            urlBuilder.addEncodedQueryParameter(
                entry.key,
                URLEncoder.encode(entry.value, StandardCharsets.UTF_8.toString())
            )
        }
        val request = Request.Builder()
            .get()
            .tag(map)
            .url(urlBuilder.build())
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: Call, response: Response) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val responseBody = response.body?.string()
                    if (response.isSuccessful && responseBody != null) {
                        NetUtils.cloak_fancy_scenery = responseBody
                        Log.e("TAG", "blacklist results=${NetUtils.cloak_fancy_scenery} ")
                    } else {
                        delay(10003)
                        getRecordNetData(context)
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                GlobalScope.launch(Dispatchers.IO) {
                    delay(10002)
                    getRecordNetData(context)
                }
            }
        })
    }
}