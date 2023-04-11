package com.app.commons.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenderResponse(
	@SerializedName("genres") val genres: List<GenresItemResponse>
) : Parcelable

@Parcelize
data class GenresItemResponse(
	@SerializedName("name") val name: String,
	@SerializedName("id") val id: Int
) : Parcelable
