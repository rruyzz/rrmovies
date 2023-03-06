package com.app.features.home.data.mapper

import com.app.features.home.data.model.PopularMoviesResponse
import com.app.features.home.domain.models.Movie
import com.app.features.home.domain.models.PopularMovies

class PopularMoviesMapper {
    operator fun invoke(response: PopularMoviesResponse?
    ) : PopularMovies {
        val fiveList = response?.results.orEmpty().subList(0,5).map {
            Movie(it.posterPath)
        }
        return PopularMovies(
            movies = fiveList
        )
    }
}