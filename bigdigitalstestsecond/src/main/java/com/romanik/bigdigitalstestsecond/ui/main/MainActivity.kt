package com.romanik.bigdigitalstestsecond.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.romanik.bigdigitalstestsecond.R
import com.romanik.bigdigitalstestsecond.core.Constants
import com.romanik.bigdigitalstestsecond.databinding.ActivityMainBinding
import com.romanik.bigdigitalstestsecond.domain.model.Photo
import com.romanik.bigdigitalstestsecond.domain.model.Status
import com.romanik.bigdigitalstestsecond.service.PhotoDeleteService
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var dataBinding: ActivityMainBinding
    private val REQUEST_WRITE_EXTERNAL_STORAGE = 18086

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.closingMessage = viewModel.closingMessage
        dataBinding.isShowMessage = viewModel.showCloseMessage
        dataBinding.lifecycleOwner = this@MainActivity
        with(viewModel) {
            events.observe(this@MainActivity, Observer { finishApp() })
            error.observe(this@MainActivity, Observer { notify(it) })
            successMessage.observe(this@MainActivity, Observer { notify(it.content) })
        }
        with(intent) {
            val id = getLongExtra(Constants.PHOTO_ID, 0L)
            val link = getStringExtra(Constants.LINK) ?: ""
            val status = getIntExtra(Constants.STATUS, 3)
            val date = getLongExtra(Constants.DATE, Date().time)
            viewModel.photo = Photo(id, link, Status.getStatus(status), Date(date))
            when (getStringExtra(Constants.WHERE_FROM)) {
                Constants.ADD_LINK_SCREEN -> {
                    loadPhoto(
                        onSuccess = {
                            viewModel.photo.status = Status.LOADED
                            viewModel.savePhotoInDb()
                            if (checkWriteExternalStoragePermission()) saveImageInSDCard() else requestPermission()
                        },
                        onError = {
                            viewModel.photo.status = Status.ERROR
                            viewModel.savePhotoInDb()
                        }
                    )
                }
                Constants.LIST_OF_LINKS_SCREEN -> {
                    loadPhoto(
                        onSuccess = {
                            if (viewModel.photo.status == Status.LOADED) {
                                startDeleteServiceIntent()
                            } else {
                                viewModel.photo.status = Status.LOADED
                                viewModel.updatePhotoInDb()
                                if (checkWriteExternalStoragePermission()) saveImageInSDCard() else requestPermission()
                            }
                        },
                        onError = {
                            viewModel.photo.status = Status.ERROR
                            viewModel.updatePhotoInDb()
                        }
                    )
                }
                else -> {
                    viewModel.closeApp()
                    return
                }
            }
        }

    }

    private fun loadPhoto(onSuccess: () -> Unit, onError: () -> Unit) {
        Picasso.get()
            .load(viewModel.photo.link)
            .into(dataBinding.ivPhoto, object : Callback {
                override fun onError(e: Exception?) {
                    onError.invoke()
                }

                override fun onSuccess() {
                    onSuccess.invoke()
                }
            })
    }

    private fun startDeleteServiceIntent() {
        val deleteServiceIntent = Intent(this, PhotoDeleteService::class.java).apply {
            putExtra(Constants.PHOTO_ID, viewModel.photo.id)
        }
        startService(deleteServiceIntent)
    }

    private fun saveImageInSDCard() {
        viewModel.saveImage(dataBinding.ivPhoto.drawable.toBitmap())
    }

    private fun checkWriteExternalStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_WRITE_EXTERNAL_STORAGE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_WRITE_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    saveImageInSDCard()
                }
                return
            }
        }
    }

    fun notify(message: String) =
        Snackbar.make(dataBinding.root, message, Snackbar.LENGTH_LONG).show()

    private fun finishApp() {
        finish()
        moveTaskToBack(true)
    }
}
