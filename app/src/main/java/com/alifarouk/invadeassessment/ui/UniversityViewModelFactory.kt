package com.alifarouk.invadeassessment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alifarouk.invadeassessment.repository.Repository

class UniversityViewModelFactory (var repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UniversityListingViewModel::class.java)) {
            return UniversityListingViewModel(repository) as T
        }else {
            throw IllegalArgumentException("Unknown ViewModel !!")
        }
    }
}