package com.app.features.home.home.data.mapper

import com.app.features.home.home.data.model.PopularMoviesResponse
import com.app.features.home.home.domain.models.Movie
import com.app.features.home.home.domain.models.PopularMovies

class MoviesMapper {

    operator fun invoke(response: PopularMoviesResponse?
    ) : PopularMovies {
        val fiveList = response?.results.orEmpty().subList(0,18).map {
            Movie(it.posterPath)
        }
        return PopularMovies(
            movies = fiveList
        )
    }

}