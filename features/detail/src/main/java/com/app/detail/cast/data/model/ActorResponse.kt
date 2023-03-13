package com.app.detail.cast.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ActorResponse(
    @SerializedName("cast") val cast: List<ActorsItem>,
    @SerializedName("id") val id: Int,
    @SerializedName("crew") val crew: List<CrewItem>
) : Parcelable

@Parcelize
data class CrewItem(
    @SerializedName("gender") val gender: Int,
    @SerializedName("credit_id") val creditId: String,
    @SerializedName("known_for_department") val knownForDepartment: String,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val profilePath: String,
    @SerializedName("id") val id: Int,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("department") val department: String,
    @SerializedName("job") val job: String
) : Parcelable

@Parcelize
data class ActorsItem(
    @SerializedName("character") val character: String?,
    @SerializedName("gender") val gender: Int?,
    @SerializedName("credit_id") val creditId: String?,
    @SerializedName("known_for_department") val knownForDepartment: String?,
    @SerializedName("original_name") val originalName: String?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("name") val name: String?,
    @SerializedName("profile_path") val profilePath: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("order") val order: Int?
) : Parcelable
