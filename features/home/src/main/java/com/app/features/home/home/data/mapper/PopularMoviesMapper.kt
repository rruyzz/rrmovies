package com.app.features.home.home.data.mapper

import com.app.commons.models.Movie
import com.app.features.home.home.data.model.PopularMoviesResponse
import com.app.features.home.home.domain.models.PopularMovies

class PopularMoviesMapper {
    operator fun invoke(response: PopularMoviesResponse?
    ) : PopularMovies {
        val fiveList = response?.results.orEmpty().subList(0,5).map {
            Movie(
                poster = it.posterPath,
                posterBack = it.backdropPath,
                title = it.titleMovie,
                description = it.overview,
                year = it.releaseDate.take(4),
                time = it.releaseDate.take(4),
                gener = it.genreIds.first().toString()
            )
        }
        return PopularMovies(
            movies = fiveList
        )
    }
}