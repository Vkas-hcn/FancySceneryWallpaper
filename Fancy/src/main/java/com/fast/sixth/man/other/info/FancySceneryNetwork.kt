package com.fast.sixth.man.other.info

import com.fast.sixth.man.core.mApp
import com.fast.sixth.man.open.mReferrerDetails
import com.fast.sixth.man.tools.FancyLog
import com.fast.sixth.man.tools.TInfoHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.net.URLEncoder

/**
 * Date：2024/1/19
 * Describe:
 */
private val tInfoHelper by lazy { TInfoHelper(mApp) }

object FancySceneryNetwork {
    private val mNetworkClient = OkHttpClient()

    fun postInsEvent(jsonObject: JSONObject) {
        tInfoHelper.createBasicInfo().apply {
            put("arsenic", jsonObject)
            post(this, retry = 9, successCall = {
                mReferrerDetails = ""
            })
        }
    }

    fun postLitton(jsonObject: JSONObject) {
        tInfoHelper.createBasicInfo().apply {
            put("litton", jsonObject)
            post(this)
        }
    }

    fun postMai(
        name: String, map: Map<String, String>? = null, isPost: Boolean = false, retry: Int = 0
    ) {
        if (isPost.not() && FancySceneryConfigure.fancyCBean.isPostL().not()) {
            FancyLog.e("postMai--cancel-$name")
            return
        }
        FancyLog.e("postMai---$name")
        tInfoHelper.createBasicInfo().apply {
            put("inventor", name)
            map?.forEach { (t, u) ->
                put(t, u)
            }
            post(this, retry = retry)
        }
    }

    private val ioScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private fun post(js: JSONObject, successCall: (() -> Unit)? = null, retry: Int = 0) {
        val request = Request.Builder().url(tInfoHelper.getUFancy()).post(
            js.toString().toRequestBody("application/json".toMediaType())
        ).build()
        ioScope.launch {
            execute(request, successCall ?: {}, retry)
        }
    }

    private suspend fun execute(request: Request, successCall: () -> Unit, retry: Int) {
        suspend fun retryF() {
            if (retry > 0) {
                delay(14000)
                execute(request, successCall, retry - 1)
            }
        }
        runCatching {
            val result = mNetworkClient.newCall(request).execute()
            FancyLog.e("postInfo---code>${result.code} ---${result.body?.string()}")
            if (result.isSuccessful && result.code == 200) {
                successCall.invoke()
            } else {
                retryF()
            }
        }.onFailure {
            it.printStackTrace()
            retryF()
        }
    }

}