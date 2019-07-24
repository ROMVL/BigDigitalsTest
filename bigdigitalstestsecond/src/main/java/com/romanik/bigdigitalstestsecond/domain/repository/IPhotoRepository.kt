package com.romanik.bigdigitalstestsecond.domain.repository

import com.romanik.bigdigitalstestsecond.domain.model.Status

interface IPhotoRepository {

    suspend fun savePhoto(link: String, date: Long, status: Status)

    suspend fun updatePhoto(id: Long, link: String, date: Long, status: Status)

    suspend fun deletePhoto(id: Long)

}