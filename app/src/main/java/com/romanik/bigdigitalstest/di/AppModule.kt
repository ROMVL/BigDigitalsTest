package com.romanik.bigdigitalstest.di

import com.romanik.bigdigitalstest.data.repository.PhotoRepository
import com.romanik.bigdigitalstest.ui.add_link.AddPhotoViewModel
import com.romanik.bigdigitalstest.ui.list_links.PhotoLinksViewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    single { AddPhotoViewModel() }
    single { PhotoLinksViewModel() }
}

val repositoryModule = module {
    single { PhotoRepository(get()) }
}
