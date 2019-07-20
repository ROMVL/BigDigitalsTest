package com.romanik.bigdigitalstest.di

import android.app.Application
import androidx.room.Room
import com.romanik.bigdigitalstest.data.dp.AppDataBase
import com.romanik.bigdigitalstest.data.dp.PhotoDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val databaseModule = module {
    single { providesDatabase(androidApplication()) }
    single { providesPhotoDao(get()) }
}

fun providesDatabase(application: Application): AppDataBase {
    return Room.databaseBuilder(application, AppDataBase::class.java, AppDataBase.DB_NAME).build()
}

fun providesPhotoDao(appDataBase: AppDataBase): PhotoDao = appDataBase.photoDao()