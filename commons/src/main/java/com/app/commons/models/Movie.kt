package com.app.commons.models

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Movie (
    val poster: String,
    val posterBack: String,
) : Parcelable