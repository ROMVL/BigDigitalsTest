package com.romanik.bigdigitalstest.domain.repository

import androidx.lifecycle.LiveData
import com.romanik.bigdigitalstest.domain.model.Photo

interface IPhotoRepository {

    suspend fun savePhoto(photo: Photo)

    fun fetchAllPhotos(): LiveData<List<Photo>>

}