package com.app.commons.models

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Parcelize
data class Movie (
    val poster: String,
    val posterBack: String,
    val title: String,
    val description: String,
    val year: String,
    val time: String,
    val gener: String,
    val grade: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) : Parcelable