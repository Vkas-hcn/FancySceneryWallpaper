package com.fast.sixth.man.sixthwallpaper.model

import android.Manifest
import android.app.WallpaperManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.SystemClock
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.fast.sixth.man.sixthwallpaper.base.BaseViewModel
import com.fast.sixth.man.sixthwallpaper.data.DownloadImage
import com.fast.sixth.man.sixthwallpaper.data.SixthUtils
import com.fast.sixth.man.sixthwallpaper.view.SettingActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingViewModel: BaseViewModel() {

    suspend fun setWallFun(
        imagInt: Int,
        type: Int,
        context: Context,
        loadBefore: () -> Unit,
        loadAfter: () -> Unit
    ) {
        withContext(Dispatchers.Main) {
            loadBefore()
        }
        val wallpaperManager = WallpaperManager.getInstance(context)
        val bitmap = SixthUtils.getBitmapFromVectorDrawable(context, imagInt)
        try {
            wallpaperManager.setBitmap(
                bitmap,
                null,
                false,
                type
            )
            withContext(Dispatchers.Main) {
                loadAfter()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    suspend fun setWallBothFun(
        imageInt: Int,
        context: Context,
        loadBefore: () -> Unit,
        loadAfter: () -> Unit

    ) {
        withContext(Dispatchers.Main) {
            loadBefore()
        }
        val wallpaperManager = WallpaperManager.getInstance(context)
        val bitmap = SixthUtils.getBitmapFromVectorDrawable(context, imageInt)
        try {
            wallpaperManager.setBitmap(
                bitmap,
                null,
                false,
                WallpaperManager.FLAG_SYSTEM
            )
            wallpaperManager.setBitmap(
                bitmap,
                null,
                false,
                WallpaperManager.FLAG_LOCK
            )
            withContext(Dispatchers.Main) {
                loadAfter()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun downImageInt(activity: SettingActivity,imgInt: Int){
        if (!checkPermission(activity)) {
            requestPermission(activity)
            return
        }
        activity.lifecycleScope.launch {
            DownloadImage.saveMipmapImageToGallery(activity, imgInt, System.currentTimeMillis().toString())
        }

    }

    private fun checkPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    private fun requestPermission(activity: SettingActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                0x234
            )
        }
    }

}