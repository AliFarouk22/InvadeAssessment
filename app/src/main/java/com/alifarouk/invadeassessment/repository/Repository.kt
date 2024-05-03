package com.alifarouk.invadeassessment.repository

import android.util.Log
import com.alifarouk.invadeassessment.datasource.UniversityLocalDBDatasource
import com.alifarouk.invadeassessment.datasource.UniversityRemoteDatasource
import com.alifarouk.invadeassessment.model.University

class Repository (
    protected val universityLocalDBDatasource: UniversityLocalDBDatasource,
    protected val universityRemoteDatasource: UniversityRemoteDatasource) {

    suspend fun getUniversityList(): List<University>{
        return getUniversitiesFromLocalDB()
    }

    suspend fun updateUniversityList(): List<University> {
        val newUniversityList = getUniversityListFromAPI()

        universityLocalDBDatasource.deleteAllUniversities()
        universityLocalDBDatasource.saveUniversitiesToLocalDB(newUniversityList)

        return newUniversityList
    }

    private suspend fun getUniversitiesFromLocalDB(): List<University>{
        var universityList : List<University> = ArrayList<University>()

        try {
            universityList = universityLocalDBDatasource.getUniversitiesFromLocalBD()

        }catch (exception: Exception){
            exception.message?.let { Log.d("TAGY_DB", it) }
        }

        if (universityList.size > 0){
            return universityList
        }else {
            universityList = getUniversityListFromAPI()
            universityLocalDBDatasource.saveUniversitiesToLocalDB(universityList)
        }
        return universityList
    }

    private suspend fun getUniversityListFromAPI(): List<University> {
        var universityList : List<University> = ArrayList<University>()

        try {
            val response = universityRemoteDatasource.getUniversitiesRemote()
            response.body()?.let {

                universityList = it
//                Log.d("response___", "" + it[0])
//                Log.d("response___", "" + it.listIterator().next())
            }


        }catch (exception:Exception){
            exception.message?.let { Log.d("TAGY_API", it) }
        }

        return universityList
    }
}