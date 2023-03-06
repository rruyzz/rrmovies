package com.app.features.home.domain.models

import androidx.annotation.DrawableRes
import com.app.features.home.R

data class TopMovies (
    val movies: List<Movie>,
    val iconList: List<Int> = iconListDrawable
)

data class Movie (
    val poster: String
)
private val iconListDrawable  = listOf(
    R.drawable.ic_first_position,
    R.drawable.ic_second_position,
    R.drawable.ic_thirty_position,
    R.drawable.ic_forty_position,
    R.drawable.ic_fifty_position,
)