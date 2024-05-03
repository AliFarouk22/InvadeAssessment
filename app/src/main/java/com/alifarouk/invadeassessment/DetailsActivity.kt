package com.alifarouk.invadeassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.alifarouk.invadeassessment.databinding.ActivityDetailsBinding
import com.alifarouk.invadeassessment.model.University

class DetailsActivity : AppCompatActivity() {

    protected lateinit var binding: ActivityDetailsBinding

    protected lateinit var university: University

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@DetailsActivity, R.layout.activity_details)

        university = intent.getParcelableExtra("selected_UNI")!!
//        Toast.makeText(this@DetailsActivity, university.name, Toast.LENGTH_LONG).show()

        binding.apply {
            uniNameDetailsTV.text = university.name

            nuiStateDetailsTV.text = "Not Available"
            university.stateProvince?.let {
                nuiStateDetailsTV.text = university.stateProvince
            }

            countryTV.text = university.country
            countryCodeTV.text = university.alpha_two_code
            webPageTV.text = university.web_pages[0]

            refereshImgBtn.setOnClickListener {
                ListingActivity.universityListingViewModel.updateUniversityList()
                this@DetailsActivity.finish()

            }
        }
    }
}