package com.app.features.home.home.data.mapper

import com.app.commons.gender.GenderListMapper
import com.app.commons.models.Movie
import com.app.features.home.home.data.model.PopularMoviesResponse
import com.app.features.home.home.domain.models.PopularMovies

class MoviesMapper(private val genderListMapper: GenderListMapper) {

    operator fun invoke(response: PopularMoviesResponse?
    ) : PopularMovies {
        val fiveList = response?.results.orEmpty().subList(0,18).map {
            Movie(
                id = it.id ?: 0,
                poster = it.posterPath.orEmpty(),
                posterBack = it.backdropPath.orEmpty(),
                title = it.titleMovie.orEmpty(),
                description = it.overview.orEmpty(),
                year = it.releaseDate?.take(4).orEmpty(),
                time = it.releaseDate?.take(4).orEmpty(),
                gender = genderListMapper(it.genreIds),
                grade = it.voteAverage.toString()
            )
        }
        return PopularMovies(
            movies = fiveList
        )
    }

}