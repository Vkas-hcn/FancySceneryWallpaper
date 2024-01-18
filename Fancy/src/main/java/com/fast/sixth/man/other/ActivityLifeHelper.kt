package com.fast.sixth.man.other

import android.app.Activity
import android.app.Application
import android.content.Context
import com.fast.sixth.man.listener.LifecycleActivityListener
import kotlinx.coroutines.Job

/**
 * Date：2024/1/17
 * Describe:
 */
class ActivityLifeHelper(val context: Context) {

    companion object {
        var numActivity = 0
            set(value) {
                field = value
                if (value <= 0) {
                    field = 0
                    isAppResume = false
                    finishJob()
                } else {
                    mFinishJob?.cancel()
                }
            }

        var isAppResume = false
        private var mFinishJob: Job? = null
        private fun finishJob() {
            mFinishJob?.cancel()
            mFinishJob = LifecycleActivityListener.finishAllActivity()
        }
    }

    fun register(app: Application) {
        app.registerActivityLifecycleCallbacks(LifecycleActivityListener {
            activityOnCreate(it)
        })
    }

    private fun activityOnCreate(activity: Activity) {

    }

}