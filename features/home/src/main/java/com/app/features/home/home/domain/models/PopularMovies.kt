package com.app.features.home.home.domain.models

import com.app.commons.models.Movie
import com.app.features.home.R

data class PopularMovies (
    val movies: List<Movie>,
    val iconList: List<Int> = iconListDrawable
)

private val iconListDrawable  = listOf(
    R.drawable.ic_first_position,
    R.drawable.ic_second_position,
    R.drawable.ic_thirty_position,
    R.drawable.ic_forty_position,
    R.drawable.ic_fifty_position,
)