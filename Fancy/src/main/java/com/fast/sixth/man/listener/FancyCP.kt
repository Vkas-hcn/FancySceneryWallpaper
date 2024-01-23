package com.fast.sixth.man.listener

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri

/**
 * Date：2024/1/22
 * Describe:
 */
class FancyCP : ContentProvider() {
    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String?>?,
        selection: String?,
        selectionArgs: Array<String?>?,
        sortOrder: String?
    ): Cursor? {
        return shusjikkjia(uri)
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String?>?): Int {
        return 0
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String?>?
    ): Int {
        return 0
    }

    fun shusjikkjia(uri: Uri?): Cursor? {
        if (uri == null || !uri.toString().endsWith("/directories")) {
            return null
        }
        //MatrixCursor matrixCursor = new MatrixCursor(new String[]{new String("accountName"), new String("accountType"), new String(FileProvider.DISPLAYNAME_FIELD), "typeResourceId", "exportSupport", "shortcutSupport", "photoSupport"});
        val matrixCursor = MatrixCursor(arrayOf("accountName", "accountType", "displayName", "typeResourceId", "exportSupport", "shortcutSupport", "photoSupport"))
        val packageName = context?.packageName ?: ""
        matrixCursor.addRow(
            arrayOf<Any>(
                packageName, packageName, packageName, 0, 1, 1, 1
            )
        )
        return matrixCursor
    }
}