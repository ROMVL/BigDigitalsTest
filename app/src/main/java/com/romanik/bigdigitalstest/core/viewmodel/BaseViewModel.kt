package com.romanik.bigdigitalstest.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main

    val error by lazy { MutableLiveData<String>() }

    val errorHandler = { e: Throwable ->
        if (e !is CancellationException) {
            error.postValue(e.localizedMessage)
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
        coroutineContext.cancel()
    }

}