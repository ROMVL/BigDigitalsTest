package com.romanik.bigdigitalstest.data.dp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.romanik.bigdigitalstest.domain.model.Photo

@Database(
    entities = [ Photo::class ],
    version = AppDataBase.VERSION,
    exportSchema = true
)
@TypeConverters(DatabaseConverter::class)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        const val DB_NAME = "BigDigitalsTest.db"
        const val VERSION = 1
    }

    abstract fun photoDao(): PhotoDao

}