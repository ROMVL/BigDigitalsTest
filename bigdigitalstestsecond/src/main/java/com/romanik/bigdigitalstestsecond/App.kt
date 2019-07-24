package com.romanik.bigdigitalstestsecond

import android.app.Application
import com.romanik.bigdigitalstestsecond.di.modules
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, modules)
    }

}