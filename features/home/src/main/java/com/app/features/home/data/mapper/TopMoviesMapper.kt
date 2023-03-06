package com.app.features.home.data.mapper

import com.app.features.home.data.model.MovieResultResponse
import com.app.features.home.domain.models.Movie
import com.app.features.home.domain.models.TopMovies

class TopMoviesMapper {
    operator fun invoke(response: MovieResultResponse?
    ) : TopMovies {
        val fiveList = response?.results.orEmpty().subList(0,5).map {
            Movie(it.posterPath)
        }
        return TopMovies(
            movies = fiveList
        )
    }
}