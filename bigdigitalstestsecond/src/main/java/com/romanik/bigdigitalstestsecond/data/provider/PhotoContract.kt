package com.romanik.bigdigitalstestsecond.data.provider

import android.net.Uri

object PhotoContract {
    const val AUTHORITY = "com.romanik.bigdigitalstest.providers"
    const val PHOTO_PATH = "photo"
    val PHOTO_CONTENT_URI = Uri.parse("content://$AUTHORITY/$PHOTO_PATH")
    const val PHOTO_TABLE_NAME = "photo"
    const val PHOTO_ID_COLUMN = "id"
    const val PHOTO_LINK_COLUMN = "link"
    const val PHOTO_STATUS_COLUMN = "status"
    const val PHOTO_DATE_COLUMN = "date"
}
