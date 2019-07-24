package com.romanik.bigdigitalstestsecond.domain.model

import java.util.*


data class Photo(
    var id: Long,
    var link: String,
    var status: Status,
    var date: Date
)