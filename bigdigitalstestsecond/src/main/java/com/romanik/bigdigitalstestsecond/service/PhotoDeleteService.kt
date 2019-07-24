package com.romanik.bigdigitalstestsecond.service

import android.app.IntentService
import android.content.Intent
import android.widget.Toast
import com.romanik.bigdigitalstestsecond.R
import com.romanik.bigdigitalstestsecond.core.Constants
import com.romanik.bigdigitalstestsecond.domain.repository.IPhotoRepository
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

class PhotoDeleteService(private val name: String = PhotoDeleteService::class.java.name) : IntentService(name) {

    private val photoRepository: IPhotoRepository by inject()

    override fun onHandleIntent(intent: Intent?) {
        intent?.getLongExtra(Constants.PHOTO_ID, 0L)?.let { photoId ->
            GlobalScope.launch {
                delay(15000)
                photoRepository.deletePhoto(photoId)
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, R.string.photo_deleted, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}