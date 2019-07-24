package com.romanik.bigdigitalstestsecond.domain.model

enum class Status(val value: Int) {

    LOADED(1),
    ERROR(2),
    UNKNOWN(3);

    companion object {
        fun getStatus(value: Int): Status {
            for (tutorial in values()) {
                if (tutorial.value == value) return tutorial
            }
            return UNKNOWN
        }

        fun convertStatusToInt(status: Status): Int = when(status) {
            LOADED -> 1
            ERROR -> 2
            else -> 3
        }
    }

}