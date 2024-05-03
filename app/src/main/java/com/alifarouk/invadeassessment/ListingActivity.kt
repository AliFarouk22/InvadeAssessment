package com.alifarouk.invadeassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alifarouk.invadeassessment.database.UniversityDao
import com.alifarouk.invadeassessment.database.UniversityDatabase
import com.alifarouk.invadeassessment.databinding.ActivityListingBinding
import com.alifarouk.invadeassessment.datasource.UniversityLocalDBDatasource
import com.alifarouk.invadeassessment.datasource.UniversityRemoteDatasource
import com.alifarouk.invadeassessment.networking.RetrofitInstance
import com.alifarouk.invadeassessment.networking.UniversitiesAPIService
import com.alifarouk.invadeassessment.repository.Repository
import com.alifarouk.invadeassessment.ui.UniversityListRecyclerViewAdapter
import com.alifarouk.invadeassessment.ui.UniversityListingViewModel
import com.alifarouk.invadeassessment.ui.UniversityViewModelFactory
import com.google.android.material.snackbar.Snackbar

class ListingActivity : AppCompatActivity() {

    protected lateinit var binding: ActivityListingBinding

    protected lateinit var snackbar: Snackbar

    companion object{
        lateinit var universityListingViewModel: UniversityListingViewModel
    }

    protected lateinit var universityViewModelFactory: UniversityViewModelFactory
    protected lateinit var universityListRecyclerViewAdapter: UniversityListRecyclerViewAdapter

    protected lateinit var universityLocalDBDatasource: UniversityLocalDBDatasource
    protected lateinit var universityRemoteDatasource: UniversityRemoteDatasource

    protected lateinit var universityDao: UniversityDao

    protected lateinit var universitiesAPIService: UniversitiesAPIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_listing)

        binding = DataBindingUtil.setContentView(this@ListingActivity, R.layout.activity_listing)

        universityDao = UniversityDatabase.getInstance(this@ListingActivity).universityDao()

        universitiesAPIService = RetrofitInstance.getRetrofitInstance().create(UniversitiesAPIService::class.java)

        universityLocalDBDatasource = UniversityLocalDBDatasource(universityDao)
        universityRemoteDatasource = UniversityRemoteDatasource(universitiesAPIService)

        universityViewModelFactory = UniversityViewModelFactory(
            Repository(
                universityLocalDBDatasource,
                universityRemoteDatasource
            )
        )

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@ListingActivity)
            setHasFixedSize(true)
        }

        universityListingViewModel = ViewModelProvider(this@ListingActivity, universityViewModelFactory).get(UniversityListingViewModel::class.java)
        loadData()

        binding.firstLoadingBtn.setOnClickListener {
            snackbar.dismiss()
            loadData()
        }


    }

    fun loadData(){
        universityListingViewModel.getUniversityList().observe(this@ListingActivity) {

            universityListRecyclerViewAdapter = UniversityListRecyclerViewAdapter(this@ListingActivity)
            universityListRecyclerViewAdapter.setUniversitiesList(it)
            Log.d("Data___", ""+it)

            if(it.size == 0){
                snackbar = Snackbar.make(binding.recyclerview, "No Data stored in the DB, Please check your internet connection and try again.", Snackbar.LENGTH_INDEFINITE)
                snackbar.setAction("Ok") {
                    snackbar.dismiss()
                }
                snackbar.show()
            }else {
                binding.firstLoadingBtn.visibility = View.GONE
                binding.recyclerview.visibility = View.VISIBLE
            }


            binding.recyclerview.adapter = universityListRecyclerViewAdapter

        }
    }
}