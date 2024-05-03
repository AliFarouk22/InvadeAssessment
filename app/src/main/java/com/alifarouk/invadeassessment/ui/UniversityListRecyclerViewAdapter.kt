package com.alifarouk.invadeassessment.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alifarouk.invadeassessment.DetailsActivity
import com.alifarouk.invadeassessment.R
import com.alifarouk.invadeassessment.databinding.RecyclerviewItemBinding
import com.alifarouk.invadeassessment.model.University

class UniversityListRecyclerViewAdapter(protected var context: Context) : RecyclerView.Adapter<UniversityListRecyclerViewAdapter.ViewHolder> () {

    protected var universityList: MutableList<University> = ArrayList<University>()

    fun setUniversitiesList(universities: List<University>){
        universityList.clear()
        universityList.addAll(universities)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RecyclerviewItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recyclerview_item, parent, false)
        return ViewHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return universityList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(universityList.get(position))
    }

    class ViewHolder (protected var recyclerviewItemBinding: RecyclerviewItemBinding, protected var context: Context) : RecyclerView.ViewHolder(recyclerviewItemBinding.root) {

        fun bind(university: University){
            recyclerviewItemBinding.uniNameTV.text = university.name

            recyclerviewItemBinding.uniStateTV.text = "Not Available"

            university.stateProvince?.let {
                recyclerviewItemBinding.uniStateTV.text = it.toString()
            }

            recyclerviewItemBinding.nextImgBtn.setOnClickListener {
                var intent: Intent = Intent(context, DetailsActivity::class.java)


                intent.putExtra("selected_UNI", university)
                context.startActivity(intent)

            }

            recyclerviewItemBinding.root.setOnClickListener {
                var intent: Intent = Intent(context, DetailsActivity::class.java)

                intent.putExtra("selected_UNI", university)
                context.startActivity(intent)
            }

        }

    }

}