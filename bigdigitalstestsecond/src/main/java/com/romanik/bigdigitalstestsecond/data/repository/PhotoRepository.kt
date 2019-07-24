package com.romanik.bigdigitalstestsecond.data.repository

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import com.romanik.bigdigitalstestsecond.core.Constants
import com.romanik.bigdigitalstestsecond.data.provider.PhotoContract
import com.romanik.bigdigitalstestsecond.domain.model.Status
import com.romanik.bigdigitalstestsecond.domain.repository.IPhotoRepository

class PhotoRepository(
    private val contentResolver: ContentResolver
) : IPhotoRepository {

    override suspend fun savePhoto(link: String, date: Long, status: Status) {
        val values = ContentValues().apply {
            put(PhotoContract.PHOTO_LINK_COLUMN, link)
            put(PhotoContract.PHOTO_DATE_COLUMN, date)
            put(PhotoContract.PHOTO_STATUS_COLUMN, status.value)
        }
        contentResolver.insert(PhotoContract.PHOTO_CONTENT_URI, values)
    }

    override suspend fun updatePhoto(id: Long, link: String, date: Long, status: Status) {
        val values = ContentValues().apply {
            put(PhotoContract.PHOTO_ID_COLUMN, id)
            put(PhotoContract.PHOTO_LINK_COLUMN, link)
            put(PhotoContract.PHOTO_DATE_COLUMN, date)
            put(PhotoContract.PHOTO_STATUS_COLUMN, status.value)
        }
        val uri = ContentUris.withAppendedId(PhotoContract.PHOTO_CONTENT_URI, id)
        contentResolver.update(uri, values, null, null)
    }


    override suspend fun deletePhoto(id: Long) {
        val uri = ContentUris.withAppendedId(PhotoContract.PHOTO_CONTENT_URI, id)
        contentResolver.delete(uri, null, null)
    }
}