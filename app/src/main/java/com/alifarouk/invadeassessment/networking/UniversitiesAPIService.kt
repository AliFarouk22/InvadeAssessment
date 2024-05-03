package com.alifarouk.invadeassessment.networking

import com.alifarouk.invadeassessment.model.University
import com.alifarouk.invadeassessment.model.UniversityList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversitiesAPIService {

    @GET("search")
    suspend fun getUniversitiesList(@Query("country") country: String): Response<List<University>>
//    @GET()
//    suspend fun getUniversitiesList(): Response<UniversityList>
}