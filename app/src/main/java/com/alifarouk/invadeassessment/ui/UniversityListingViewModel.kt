package com.alifarouk.invadeassessment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.alifarouk.invadeassessment.repository.Repository

class UniversityListingViewModel (var repository: Repository) : ViewModel() {

    fun getUniversityList() = liveData {
        val universityList = repository.getUniversityList()
        emit(universityList)
    }

    fun updateUniversityList() = liveData {
        emit(repository.updateUniversityList())
    }
}