package com.fast.sixth.man.open.bro

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.fast.sixth.man.core.FancySceneryHelper
import com.fast.sixth.man.other.info.FancySceneryNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Date：2024/1/22
 * Describe:
 */

val mIntentFilter = IntentFilter().apply {
    addAction(Intent.ACTION_USER_PRESENT)
    addAction(Intent.ACTION_SCREEN_OFF)
}
var isCanBroadEvent = false

class FancyBroadcastReceiver : BroadcastReceiver() {
    private var job: Job? = null
    private var timeEvent = 0L
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_SCREEN_OFF -> {
                job?.cancel()
            }

            Intent.ACTION_USER_PRESENT -> {
                timeEvent = System.currentTimeMillis()
                if (isCanBroadEvent) {
                    job?.cancel()
                    job = CoroutineScope(Dispatchers.Main).launch {
                        delay(7810)
                        FancySceneryNetwork.postMai("time_charge")
                        FancySceneryHelper.fancyE()
                    }
                }
            }
        }
    }
}