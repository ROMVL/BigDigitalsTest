package com.romanik.bigdigitalstestsecond.ui.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.romanik.bigdigitalstestsecond.R
import com.romanik.bigdigitalstestsecond.core.Event
import com.romanik.bigdigitalstestsecond.core.SingleLiveEvent
import com.romanik.bigdigitalstestsecond.domain.model.Photo
import com.romanik.bigdigitalstestsecond.domain.repository.IPhotoRepository
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(
    application: Application,
    private val photoRepository: IPhotoRepository
) : AndroidViewModel(application), CoroutineScope {

    enum class MainEvent {
        CLOSE_APP
    }

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main
    val showCloseMessage by lazy { MutableLiveData<Boolean>().apply { value = false } }
    val events by lazy { SingleLiveEvent<Event<MainEvent>>() }
    val closingMessage by lazy { MutableLiveData<String>() }
    val error by lazy { MutableLiveData<String>() }
    val successMessage by lazy { SingleLiveEvent<Event<String>>() }
    private val currentDate = Date()
    lateinit var photo: Photo

    fun savePhotoInDb() {
        launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    photoRepository.savePhoto(photo.link, currentDate.time, photo.status)
                }
            }.onSuccess {
                successMessage.value = Event(getApplication<Application>().getString(R.string.photo_saved))
            }.onFailure {
                error.value = it.localizedMessage
            }
        }
    }

    fun updatePhotoInDb() {
        launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    photoRepository.updatePhoto(photo.id, photo.link, photo.date.time, photo.status)
                }
            }.onSuccess {
                successMessage.value = Event(getApplication<Application>().getString(R.string.photo_updated))
            }.onFailure {
                error.value = it.localizedMessage
            }
        }
    }

    fun closeApp() {
        showCloseMessage.value = true
        launch {
            for (i in 10 downTo 0) {
                closingMessage.postValue("The second application is not a standalone application and will be closed after $i seconds.")
                delay(1000)
            }
            events.postValue(Event(MainEvent.CLOSE_APP))
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

}