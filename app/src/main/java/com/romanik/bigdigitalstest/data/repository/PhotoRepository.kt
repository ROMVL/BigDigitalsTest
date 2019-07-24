package com.romanik.bigdigitalstest.data.repository

import androidx.lifecycle.LiveData
import com.romanik.bigdigitalstest.data.dp.PhotoDao
import com.romanik.bigdigitalstest.domain.model.Photo
import com.romanik.bigdigitalstest.domain.repository.IPhotoRepository

class PhotoRepository(private val photoDao: PhotoDao) : IPhotoRepository {

    override suspend fun savePhoto(photo: Photo) = photoDao.savePhotoAsync(photo)

    override fun fetchAllPhotos(): LiveData<List<Photo>> = photoDao.fetchAllPhoto()

    override fun fetchAllPhotoSortedByDateDesc(): LiveData<List<Photo>> = photoDao.fetchAllPhotoSortedByDateDesc()

}