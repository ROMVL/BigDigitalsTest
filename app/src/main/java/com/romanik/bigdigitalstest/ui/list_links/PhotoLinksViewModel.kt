package com.romanik.bigdigitalstest.ui.list_links

import androidx.lifecycle.LiveData
import com.romanik.bigdigitalstest.core.viewmodel.BaseViewModel
import com.romanik.bigdigitalstest.domain.model.Photo
import com.romanik.bigdigitalstest.domain.repository.IPhotoRepository

class PhotoLinksViewModel(
    private val photoRepository: IPhotoRepository
) : BaseViewModel() {

    val photos: LiveData<List<Photo>> = photoRepository.fetchAllPhotos()

}
