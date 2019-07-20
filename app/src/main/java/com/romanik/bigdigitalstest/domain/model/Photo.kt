package com.romanik.bigdigitalstest.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val link: String = "",
    val status: Status = Status.UNKNOWN
)