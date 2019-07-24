package com.romanik.bigdigitalstest.ui.add_link

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.romanik.bigdigitalstest.core.lifecycle.Event
import com.romanik.bigdigitalstest.core.lifecycle.SingleLiveEvent
import com.romanik.bigdigitalstest.core.viewmodel.BaseViewModel
import com.romanik.bigdigitalstest.domain.model.Photo
import com.romanik.bigdigitalstest.domain.model.Status
import com.romanik.bigdigitalstest.domain.repository.IPhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class AddPhotoViewModel(
    private val photoRepository: IPhotoRepository
) : BaseViewModel() {

    enum class AddPhotoEvent {
        OPEN_SECOND_APP
    }

    val photoLink by lazy { ObservableField<String>("") }
    val eventPhotoViewModel by lazy { SingleLiveEvent<Event<AddPhotoEvent>>() }

    fun savePhotoInDb() {
        eventPhotoViewModel.value = Event(AddPhotoEvent.OPEN_SECOND_APP)
        /*launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    photoLink.get()?.let {
                        val photo = Photo(id = 0, link = it, status = Status.UNKNOWN, date = Date())
                        photoRepository.savePhoto(photo)
                    }
                }
            }.onSuccess {
                it?.let {
                    Log.d(AddPhotoViewModel::class.java.name, "saved")
                }
                Log.d(AddPhotoViewModel::class.java.name, "hz")
            }.onFailure {
                errorHandler(it)
            }
        }*/
    }

}
