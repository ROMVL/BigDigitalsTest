package com.romanik.bigdigitalstest.data.dp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.romanik.bigdigitalstest.domain.model.Photo

@Dao
interface PhotoDao {

    @Insert
    suspend fun savePhoto(photo: Photo)

    @Query("SELECT * FROM Photo")
    fun fetchAllPhoto(): LiveData<List<Photo>>

    @Query("SELECT * FROM Photo ORDER BY date DESC")
    fun fetchAllPhotoSortedByDateDesc(): LiveData<List<Photo>>

}