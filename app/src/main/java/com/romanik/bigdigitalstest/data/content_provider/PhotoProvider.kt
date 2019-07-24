package com.romanik.bigdigitalstest.data.content_provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.romanik.bigdigitalstest.data.content_provider.PhotoContract.Companion.AUTHORITY
import com.romanik.bigdigitalstest.data.content_provider.PhotoContract.Companion.PHOTO_PATH
import com.romanik.bigdigitalstest.data.dp.PhotoDao
import com.romanik.bigdigitalstest.di.providesDatabase
import com.romanik.bigdigitalstest.di.providesPhotoDao

class PhotoProvider : ContentProvider() {

    companion object {
        const val URI_PHOTO = 1
        const val URI_PHOTO_ID = 2
        private var uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, PHOTO_PATH, URI_PHOTO)
            uriMatcher.addURI(AUTHORITY, "$PHOTO_PATH/#", URI_PHOTO_ID)
        }
    }

    private lateinit var photoDao: PhotoDao

    override fun onCreate(): Boolean {
        context?.let {
            photoDao = providesPhotoDao(providesDatabase(it))
            return true
        }
        return false
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? = throw IllegalArgumentException("Unknown URI: $uri, app not support query")

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return when(uriMatcher.match(uri)) {
            URI_PHOTO -> {
                val id = photoDao.savePhoto(PhotoContract.Photo.fromContentValues(values))
                context?.contentResolver?.notifyChange(uri, null)
                ContentUris.withAppendedId(uri, id)
            }
            URI_PHOTO_ID -> throw IllegalArgumentException("Invalid URI: $uri, cannot insert with ID")
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        return when(uriMatcher.match(uri)) {
            URI_PHOTO -> throw IllegalArgumentException("Invalid URI: $uri, cannot update without ID")
            URI_PHOTO_ID -> {
                if (!selectionArgs.isNullOrEmpty()) throw IllegalArgumentException("Invalid URI: $uri, cannot update with selectionArgs")
                val value = photoDao.updatePhoto(PhotoContract.Photo.fromContentValues(values))
                context?.contentResolver?.notifyChange(uri, null)
                value
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return when(uriMatcher.match(uri)) {
            URI_PHOTO -> throw IllegalArgumentException("Invalid URI: $uri, cannot delete without ID")
            URI_PHOTO_ID -> {
                if (!selectionArgs.isNullOrEmpty()) throw IllegalArgumentException("Invalid URI: $uri, cannot update with selectionArgs")
                val id = ContentUris.parseId(uri)
                val value = photoDao.deleteById(id)
                context?.contentResolver?.notifyChange(uri, null)
                value
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            URI_PHOTO -> PhotoContract.Photo.PHOTO_CONTENT_TYPE
            URI_PHOTO_ID -> PhotoContract.Photo.PHOTO_CONTENT_ITEM_TYPE
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }
}