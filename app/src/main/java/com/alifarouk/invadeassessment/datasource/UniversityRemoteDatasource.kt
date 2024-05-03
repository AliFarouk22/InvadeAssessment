package com.alifarouk.invadeassessment.datasource

import com.alifarouk.invadeassessment.model.University
import com.alifarouk.invadeassessment.model.UniversityList
import com.alifarouk.invadeassessment.networking.UniversitiesAPIService
import retrofit2.Response

class UniversityRemoteDatasource(protected val universitiesAPIService: UniversitiesAPIService) {

    suspend fun getUniversitiesRemote(): Response<List<University>> {
        return universitiesAPIService.getUniversitiesList("United Arab Emirates")
    }
}