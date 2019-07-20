package com.romanik.bigdigitalstest.domain.repository

import androidx.lifecycle.LiveData
import com.romanik.bigdigitalstest.domain.model.Photo

interface IPhotoRepository {

    suspend fun savePhoto(photo: Photo): Int

    fun fetchAllPhotos(): LiveData<List<Photo>>

}