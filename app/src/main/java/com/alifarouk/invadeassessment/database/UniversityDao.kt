package com.alifarouk.invadeassessment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alifarouk.invadeassessment.model.University

@Dao
interface UniversityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUniversities(universities: List<University>)

    @Query("SELECT * FROM university")
    suspend fun getAllUniversities(): List<University>

    @Query("DELETE FROM university")
    suspend fun deleteAll()
}