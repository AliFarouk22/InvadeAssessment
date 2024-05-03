package com.alifarouk.invadeassessment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.alifarouk.invadeassessment.model.University

@Database(entities = [University::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)

abstract class UniversityDatabase : RoomDatabase() {

    abstract fun universityDao() : UniversityDao

    companion object{
        @Volatile
        private var instance: UniversityDatabase? = null

        fun getInstance(context: Context): UniversityDatabase{
            synchronized(this){
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UniversityDatabase::class.java,
                        "university"
                    ).build()
                }
            }

            return instance as UniversityDatabase
        }
    }

}