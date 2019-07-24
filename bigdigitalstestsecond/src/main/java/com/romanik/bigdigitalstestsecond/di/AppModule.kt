package com.romanik.bigdigitalstestsecond.di

import android.content.ContentResolver
import android.content.Context
import com.romanik.bigdigitalstestsecond.data.repository.PhotoRepository
import com.romanik.bigdigitalstestsecond.domain.repository.IPhotoRepository
import com.romanik.bigdigitalstestsecond.ui.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { MainViewModel(androidApplication(), get()) }
}

val repositoryModule = module {
    single { PhotoRepository(get()) as IPhotoRepository }
}

val providerModule = module {
    single { providesContentResolver(androidApplication()) }
}

fun providesContentResolver(context: Context): ContentResolver = context.contentResolver

val modules = listOf(viewModelModule, repositoryModule, providerModule)