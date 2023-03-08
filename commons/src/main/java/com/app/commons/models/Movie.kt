package com.app.commons.models

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Movie (
    val poster: String,
    val posterBack: String,
    val title: String,
    val description: String,
    val year: String,
    val time: String,
    val gener: String,
) : Parcelable