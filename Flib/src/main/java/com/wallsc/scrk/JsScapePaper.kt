package com.wallsc.scrk

import android.annotation.SuppressLint
import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import com.wallsc.scrk.core.JScapePImpl

/**
 * Date：2024/3/21
 * Describe:
 */
@SuppressLint("SpecifyJobSchedulerIdRange")
class JsScapePaper : JobService() {
    private val paper by lazy { JScapePImpl("a") }

    override fun onStartJob(params: JobParameters?): Boolean {
        if (paper.misjKslgSh(this).not()) {
            params?.let {
                val pb = it.extras
                paper.sjuaigkInfk(pb, this)
            }
        }
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }

    override fun onStartCommand(intent: Intent?, i: Int, i2: Int): Int {
        super.onStartCommand(intent, i, i2)
        return Service.START_STICKY
    }

    private var sitw = 0L
    private fun sjuai(context: Context) {
        val name = context.packageName
        sitw = System.currentTimeMillis()
        if (name.isEmpty()) {
            sitw -= 19190
            paper.misjKslgSh(context)
        } else {
            sitw += 19190
            paper.misjKslgSh(context)
        }
    }

    private fun set() {
        sjuai(this)
    }

}