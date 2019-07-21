package com.romanik.bigdigitalstest.ui.list_links

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.romanik.bigdigitalstest.core.viewmodel.BaseViewModel
import com.romanik.bigdigitalstest.domain.model.Photo
import com.romanik.bigdigitalstest.domain.repository.IPhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoLinksViewModel(
    private val photoRepository: IPhotoRepository
) : BaseViewModel() {

    val photos: LiveData<List<Photo>> = photoRepository.fetchAllPhotos()

    fun getSortedPhotosByDateDesc(): LiveData<List<Photo>> = photoRepository.fetchAllPhotoSortedByDateDesc()

}
