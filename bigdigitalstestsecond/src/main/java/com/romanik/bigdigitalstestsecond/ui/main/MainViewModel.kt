package com.romanik.bigdigitalstestsecond.ui.main

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.romanik.bigdigitalstestsecond.R
import com.romanik.bigdigitalstestsecond.core.Event
import com.romanik.bigdigitalstestsecond.core.SingleLiveEvent
import com.romanik.bigdigitalstestsecond.domain.model.Photo
import com.romanik.bigdigitalstestsecond.domain.repository.IPhotoRepository
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
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

    fun saveImage(image: Bitmap) {
        launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    val filename = "${currentDate.time}.jpg"
                    val path = File("${Environment.getExternalStorageDirectory().path}/BIGDIG/test/B")
                    path.mkdirs()
                    val sdFile = File(path, filename)
                    val stream = FileOutputStream(sdFile)
                    image.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    stream.close()
                    sdFile.exists()
                }
            }.onSuccess {
                successMessage.value =
                    Event(
                        getApplication<Application>()
                            .getString(
                                if (it)
                                    R.string.photo_saved_in_sdcard
                                else
                                    R.string.photo_not_saved_in_sdcard
                            )
                    )
            }.onFailure {
                Log.d("Error sd card", it.localizedMessage)
                error.value = it.localizedMessage
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

}