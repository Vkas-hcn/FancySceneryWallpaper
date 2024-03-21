package com.wallsc.scrk.listener

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.wallsc.scrk.FancyAppCore
import com.wallsc.scrk.R
import com.wallsc.scrk.tools.AppInfoTools

/**
 * Date：2024/1/19
 * Describe:
 */
class FancyNotificationHelper(val context: Context) {

    fun getNotification(): Notification {
        val id = FancyAppCore.appName.ifBlank { "Notification" }
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(id, id, NotificationManager.IMPORTANCE_DEFAULT)
            (context.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                channel
            )
        }
        val notification =
            NotificationCompat.Builder(context, id).setContentTitle("").setContentText("").apply {
                    if (AppInfoTools.isShowIcon().not()) {
                        setSmallIcon(R.drawable.ic_car_sjigs)
                    }
                }.setOngoing(true).setAutoCancel(false).setOnlyAlertOnce(true)
                .setCustomContentView(RemoteViews(context.packageName, R.layout.layout_scape))
                .build()
        return notification
    }

}