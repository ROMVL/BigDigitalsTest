package com.romanik.bigdigitalstest.data.dp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.romanik.bigdigitalstest.domain.model.Photo

@Dao
interface PhotoDao {

    @Insert
    suspend fun savePhoto(photo: Photo): Int

    @Query("SELECT * FROM Photo")
    fun fetchAllPhoto(): LiveData<List<Photo>>

}