package com.alifarouk.invadeassessment.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.alifarouk.invadeassessment.database.Converters
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "university")
data class University (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "country_code")
    val alpha_two_code: String,

    @ColumnInfo(name = "country")
    val country: String,

    @ColumnInfo(name = "domains")
    @SerializedName("domains")
    @TypeConverters(Converters::class)
    val domains: List<String>,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "state")
    @SerializedName("state-province")
    val stateProvince: String?,

    @ColumnInfo(name = "web_pages")
    @SerializedName("web_pages")
    @TypeConverters(Converters::class)
    val web_pages: List<String>
) : Parcelable
