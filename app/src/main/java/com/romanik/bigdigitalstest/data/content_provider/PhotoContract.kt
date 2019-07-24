package com.romanik.bigdigitalstest.data.content_provider

import android.content.ContentValues
import android.net.Uri
import com.romanik.bigdigitalstest.domain.model.Photo
import com.romanik.bigdigitalstest.domain.model.Status
import java.util.*

class PhotoContract {

    companion object {
        const val AUTHORITY = "com.romanik.bigdigitalstest.providers"
        const val PHOTO_PATH = "photo"
        val PHOTO_CONTENT_URI = Uri.parse("content://$AUTHORITY/$PHOTO_PATH")
    }

    class Photo private constructor() {
        companion object {

            const val PHOTO_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.$AUTHORITY.$PHOTO_PATH"
            const val PHOTO_CONTENT_TYPE = "vnd.android.cursor.dir/vnd.$AUTHORITY.$PHOTO_PATH"
            const val PHOTO_TABLE_NAME = "photo"
            const val PHOTO_ID_COLUMN = "id"
            const val PHOTO_LINK_COLUMN = "link"
            const val PHOTO_STATUS_COLUMN = "status"
            const val PHOTO_DATE_COLUMN = "date"

            val DEFAULT_PROJECTION = arrayOf(
                PHOTO_ID_COLUMN,
                PHOTO_LINK_COLUMN,
                PHOTO_STATUS_COLUMN,
                PHOTO_DATE_COLUMN
            )

            fun fromContentValues(contentValues: ContentValues?): com.romanik.bigdigitalstest.domain.model.Photo {
                val id = if (contentValues?.containsKey(PHOTO_ID_COLUMN) == true) contentValues.getAsLong(PHOTO_ID_COLUMN) else 0L
                val link = if (contentValues?.containsKey(PHOTO_LINK_COLUMN) == true) contentValues.getAsString(
                    PHOTO_LINK_COLUMN
                ) else ""
                val status = if (contentValues?.containsKey(PHOTO_STATUS_COLUMN) == true) contentValues.getAsInteger(
                    PHOTO_STATUS_COLUMN
                ) else 0
                val date = if (contentValues?.containsKey(PHOTO_DATE_COLUMN) == true) contentValues.getAsLong(
                    PHOTO_DATE_COLUMN
                ) else 0L
                return Photo(id, link, Status.getStatus(status), Date(date))
            }

        }
    }

}