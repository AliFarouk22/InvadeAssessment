package com.alifarouk.invadeassessment.datasource

import com.alifarouk.invadeassessment.database.UniversityDao
import com.alifarouk.invadeassessment.model.University
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UniversityLocalDBDatasource (protected var universityDao: UniversityDao) {

    suspend fun getUniversitiesFromLocalBD(): List<University>{
        return universityDao.getAllUniversities()
    }

    suspend fun saveUniversitiesToLocalDB(universities: List<University>){
        CoroutineScope(Dispatchers.IO).launch {
            universityDao.saveUniversities(universities)
        }
    }

    suspend fun deleteAllUniversities(){
        CoroutineScope(Dispatchers.IO).launch {
            universityDao.deleteAll()
        }
    }

}