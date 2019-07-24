package com.romanik.bigdigitalstest.data.dp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.romanik.bigdigitalstest.domain.model.Photo

@Dao
interface PhotoDao {

    @Insert
    suspend fun savePhotoAsync(photo: Photo)

    @Query("SELECT * FROM Photo")
    fun fetchAllPhoto(): LiveData<List<Photo>>

    @Query("SELECT * FROM Photo ORDER BY date DESC")
    fun fetchAllPhotoSortedByDateDesc(): LiveData<List<Photo>>

    @Query("DELETE FROM Photo WHERE id = :id")
    fun deleteById(id: Long): Int

    @Insert
    fun savePhoto(photo: Photo): Long

    @Update
    fun updatePhoto(photo: Photo): Int
}