package com.romanik.bigdigitalstest.core

import android.app.Application
import com.romanik.bigdigitalstest.di.databaseModule
import com.romanik.bigdigitalstest.di.repositoryModule
import com.romanik.bigdigitalstest.di.viewModelModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(viewModelModule, repositoryModule, databaseModule))
    }

}