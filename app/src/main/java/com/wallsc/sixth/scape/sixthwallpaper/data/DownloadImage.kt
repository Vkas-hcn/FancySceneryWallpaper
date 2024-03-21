package com.wallsc.sixth.scape.sixthwallpaper.data

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment.DIRECTORY_PICTURES
import android.os.Environment.getExternalStoragePublicDirectory
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
object DownloadImage {
    suspend fun saveMipmapImageToGallery(context: Context, mipmapResourceId: Int, imageName: String) {
        withContext(Dispatchers.IO) {
            val bitmap = ContextCompat.getDrawable(context, mipmapResourceId)?.toBitmap()

            if (bitmap != null) {
                try {
                    // 创建保存图片的目录
                    val imagesDir = File(getImagesDirectory(context))
                    if (!imagesDir.exists()) {
                        imagesDir.mkdirs()
                    }

                    // 保存图片文件
                    val imageFile = File(imagesDir, "$imageName.png")
                    saveBitmapToFile(imageFile, bitmap)

                    // 通知相册更新
                    notifyGallery(context, imageFile)

                    // 在 UI 线程显示 Toast
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Image saved to gallery", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getImagesDirectory(context: Context): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context.getExternalFilesDir(DIRECTORY_PICTURES)?.absolutePath
                ?: context.filesDir.absolutePath
        } else {
            getExternalStoragePublicDirectory(DIRECTORY_PICTURES).absolutePath
        }
    }

    private fun saveBitmapToFile(file: File, bitmap: Bitmap) {
        FileOutputStream(file).use { fos ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        }
    }

    private fun notifyGallery(context: Context, imageFile: File) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, imageFile.name)
                put(MediaStore.Images.Media.MIME_TYPE, "image/png")
                put(MediaStore.Images.Media.RELATIVE_PATH, DIRECTORY_PICTURES)
            }

            val contentResolver = context.contentResolver
            val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

            if (uri != null) {
                try {
                    contentResolver.openOutputStream(uri)?.use { outputStream ->
                        imageFile.inputStream().copyTo(outputStream)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        } else {
            // For Android versions below Q, notify the MediaScanner
            val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            val contentUri = Uri.fromFile(imageFile)
            mediaScanIntent.data = contentUri
            context.sendBroadcast(mediaScanIntent)
        }
    }
}

