package com.fast.sixth.man.listener

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import com.adjust.sdk.Adjust
import com.fast.sixth.man.core.FancySceneryHelper
import com.fast.sixth.man.other.ActivityLifeHelper
import com.fast.sixth.man.tools.FancyLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Date：2024/1/17
 * Describe:
 */
class LifecycleActivityListener(private val onCreateEvent: (activity: Activity) -> Unit) :
    ActivityLifecycleCallbacks {

    companion object {
        var isInBoot = false
        private val listActivity = arrayListOf<Activity>()

        fun finishAllActivity(): Job? {
            if (isTarget()) {
                ArrayList(listActivity).forEach {
                    it.finishAndRemoveTask()
                }
                return null
            }
            return CoroutineScope(Dispatchers.Main).launch {
                delay(1500)
                if (ActivityLifeHelper.isAppResume) return@launch
                ArrayList(listActivity).forEach {
                    it.finish()
                }
            }
        }

        private var isFancyA = false
        fun isTarget(): Boolean {
            if (isFancyA) return true
            if (FancySceneryHelper.isProgressAllGo()) {
                isFancyA = true
                return true
            }
            return false
        }
    }


    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        FancyLog.i("onActivityCreated---$activity")
        listActivity.add(0, activity)
        onCreateEvent.invoke(activity)
    }

    override fun onActivityStarted(activity: Activity) {
        FancyLog.i("onActivityStarted---$activity")
        ActivityLifeHelper.isAppResume = true
        ActivityLifeHelper.numActivity++
    }

    override fun onActivityResumed(activity: Activity) {
        FancyLog.i("onActivityResumed---$activity")
        Adjust.onResume()
    }

    override fun onActivityPaused(activity: Activity) {
        FancyLog.i("onActivityPaused---$activity")
        Adjust.onPause()
    }

    override fun onActivityStopped(activity: Activity) {
        FancyLog.i("onActivityStopped---$activity")
        ActivityLifeHelper.numActivity--
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit

    override fun onActivityDestroyed(activity: Activity) {
        FancyLog.e("onActivityDestroyed---$activity")
        listActivity.remove(activity)
    }
}