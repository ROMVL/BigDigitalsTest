package com.romanik.bigdigitalstest.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class Photo(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val link: String,
    val status: Status,
    val date: Date
) {
    fun converDateToStringFormat(): String = SimpleDateFormat("dd.MM.yyyy H:mm:ss", Locale.US).format(date)
}