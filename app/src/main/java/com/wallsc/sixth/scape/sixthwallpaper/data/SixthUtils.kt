package com.wallsc.sixth.scape.sixthwallpaper.data

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.VectorDrawable
import android.view.animation.LinearInterpolator
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.wallsc.sixth.scape.sixthwallpaper.R

object SixthUtils {
    const val topData = """
        ["ic_11,"ic_22","ic_33,"ic_44","ic_25,"ic_36"]
    """
    const val bottomData = """
        ["ic_1","ic_2","ic_3","ic_4","ic_5","ic_6","ic_7","ic_8","ic_9","ic_10","ic_11","ic_12","ic_13","ic_14","ic_15","ic_16","ic_17","ic_18","ic_19","ic_20","ic_21","ic_22","ic_23","ic_24","ic_25","ic_26","ic_27","ic_28","ic_29","ic_30","ic_31","ic_32","ic_33","ic_34","ic_35","ic_36","ic_37","ic_38","ic_39","ic_40","ic_41","ic_42","ic_43","ic_44","ic_45","ic_46","ic_47","ic_48","ic_49","ic_50","ic_51"]
    """

    // 读取本地json转成数组
    fun getBannerData(): List<String> {
        return topData.trim().replace("\n", "")
            .replace("[", "")
            .replace("]", "")
            .replace("\"", "")
            .split(",")
    }

    fun getListData(): List<String> {
        return bottomData.trim().replace("\n", "")
            .replace("[", "")
            .replace("]", "")
            .replace("\"", "")
            .split(",")
    }


    //根据名称获取图片
    fun getImgByName(name: String): Int {
        return when (name) {
            "ic_1" -> R.mipmap.ic_1
            "ic_2" -> R.mipmap.ic_2
            "ic_3" -> R.mipmap.ic_3
            "ic_4" -> R.mipmap.ic_4
            "ic_5" -> R.mipmap.ic_5
            "ic_6" -> R.mipmap.ic_6
            "ic_7" -> R.mipmap.ic_7
            "ic_8" -> R.mipmap.ic_8
            "ic_9" -> R.mipmap.ic_9
            "ic_10" -> R.mipmap.ic_10
            "ic_11" -> R.mipmap.ic_11
            "ic_12" -> R.mipmap.ic_12
            "ic_13" -> R.mipmap.ic_13
            "ic_14" -> R.mipmap.ic_14
            "ic_15" -> R.mipmap.ic_15
            "ic_16" -> R.mipmap.ic_16
            "ic_17" -> R.mipmap.ic_17
            "ic_18" -> R.mipmap.ic_18
            "ic_19" -> R.mipmap.ic_19
            "ic_20" -> R.mipmap.ic_20
            "ic_21" -> R.mipmap.ic_21
            "ic_22" -> R.mipmap.ic_22
            "ic_23" -> R.mipmap.ic_23
            "ic_24" -> R.mipmap.ic_24
            "ic_25" -> R.mipmap.ic_25
            "ic_26" -> R.mipmap.ic_26
            "ic_27" -> R.mipmap.ic_27
            "ic_28" -> R.mipmap.ic_28
            "ic_29" -> R.mipmap.ic_29
            "ic_30" -> R.mipmap.ic_30
            "ic_31" -> R.mipmap.ic_31
            "ic_32" -> R.mipmap.ic_32
            "ic_33" -> R.mipmap.ic_33
            "ic_34" -> R.mipmap.ic_34
            "ic_35" -> R.mipmap.ic_35
            "ic_36" -> R.mipmap.ic_36
            "ic_37" -> R.mipmap.ic_37
            "ic_38" -> R.mipmap.ic_38
            "ic_39" -> R.mipmap.ic_39
            "ic_40" -> R.mipmap.ic_40
            "ic_41" -> R.mipmap.ic_41
            "ic_42" -> R.mipmap.ic_42
            "ic_43" -> R.mipmap.ic_43
            "ic_44" -> R.mipmap.ic_44
            "ic_45" -> R.mipmap.ic_45
            "ic_46" -> R.mipmap.ic_46
            "ic_47" -> R.mipmap.ic_47
            "ic_48" -> R.mipmap.ic_48
            "ic_49" -> R.mipmap.ic_49
            "ic_50" -> R.mipmap.ic_50
            "ic_51" -> R.mipmap.ic_51
            else -> R.mipmap.ic_51
        }
    }

    fun AppCompatImageView.startContinuousRotation(duration: Long = 1000): ObjectAnimator {
        val rotation = ObjectAnimator.ofFloat(this, "rotation", 0f, 360f)
        rotation.duration = duration
        rotation.repeatCount = ObjectAnimator.INFINITE
        rotation.interpolator = LinearInterpolator()

        rotation.start()
        return rotation
    }

    fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap {
        return when (val drawable = ContextCompat.getDrawable(context, drawableId)) {
            is BitmapDrawable -> {
                drawable.bitmap
            }

            is VectorDrawableCompat, is VectorDrawable -> {
                val bitmap = Bitmap.createBitmap(
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                drawable.draw(canvas)
                bitmap
            }

            else -> {
                throw IllegalArgumentException("unsupported drawable type")
            }
        }
    }


}