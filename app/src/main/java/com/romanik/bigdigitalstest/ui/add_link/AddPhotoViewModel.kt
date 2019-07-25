package com.romanik.bigdigitalstest.ui.add_link

import androidx.databinding.ObservableField
import com.romanik.bigdigitalstest.core.lifecycle.Event
import com.romanik.bigdigitalstest.core.lifecycle.SingleLiveEvent
import com.romanik.bigdigitalstest.core.viewmodel.BaseViewModel

class AddPhotoViewModel : BaseViewModel() {

    enum class AddPhotoEvent {
        OPEN_SECOND_APP
    }

    val photoLink by lazy { ObservableField<String>("") }
    val eventPhotoViewModel by lazy { SingleLiveEvent<Event<AddPhotoEvent>>() }

    fun savePhotoInDb() {
        eventPhotoViewModel.value = Event(AddPhotoEvent.OPEN_SECOND_APP)
    }

}
