package com.romanik.bigdigitalstest.ui.add_link

import androidx.lifecycle.MutableLiveData
import com.romanik.bigdigitalstest.core.viewmodel.BaseViewModel
import com.romanik.bigdigitalstest.domain.model.Photo
import com.romanik.bigdigitalstest.domain.repository.IPhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddPhotoViewModel(
    private val photoRepository: IPhotoRepository
) : BaseViewModel() {

    val photo by lazy { MutableLiveData<Photo>().apply { value = Photo() } }

    fun savePhotoInDb() {
        launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    photo.value?.let {
                        photoRepository.savePhoto(it)
                    }
                }
            }.onSuccess {

            }.onFailure {
                errorHandler(it)
            }
        }
    }

}
