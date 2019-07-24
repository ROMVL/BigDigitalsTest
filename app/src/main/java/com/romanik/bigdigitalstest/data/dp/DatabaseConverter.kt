package com.romanik.bigdigitalstest.data.dp

import androidx.room.TypeConverter
import com.romanik.bigdigitalstest.domain.model.Status
import java.util.*

class DatabaseConverter {

    @TypeConverter
    fun convertStatusToInt(status: Status): Int = Status.convertStatusToInt(status)

    @TypeConverter
    fun convertIntToStatus(value: Int): Status = Status.getStatus(value)

    @TypeConverter
    fun dateToTimeStamp(date: Date): Long = date.time

    @TypeConverter
    fun timeStampToDate(timeStamp: Long): Date = Date(timeStamp)

}