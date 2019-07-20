package com.romanik.bigdigitalstest.data.dp

import androidx.room.TypeConverter
import com.romanik.bigdigitalstest.domain.model.Status

class StatusConverter {

    @TypeConverter
    fun convertStatusToInt(status: Status): Int = when(status) {
        Status.LOADED -> 1
        Status.ERROR -> 2
        else -> 3
    }

    @TypeConverter
    fun convertIntToStatus(value: Int): Status = when(value) {
        1 -> Status.LOADED
        2 -> Status.ERROR
        else -> Status.UNKNOWN
    }

}