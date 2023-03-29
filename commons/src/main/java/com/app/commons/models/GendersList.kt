package com.app.commons.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class GendersList (
    var genres: List<GenresItem>,
) : Parcelable

@Parcelize
data class GenresItem(
    val name: String,
    val id: Int
) : Parcelable
