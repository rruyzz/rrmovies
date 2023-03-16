package com.app.features.home.home.data.mapper

import com.app.commons.models.Movie
import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.search.data.model.SearchMoviesResponse

class SearchMapper {

    operator fun invoke(response: SearchMoviesResponse?
    ) : PopularMovies {
        val fiveList = response?.results.orEmpty().map {
            Movie(
                id = it.id.toString(),
                poster = it.posterPath.orEmpty(),
                posterBack = it.backdropPath.orEmpty(),
                title = it.originalName.orEmpty(),
                description = it.overview.orEmpty(),
                year = it.releaseDate?.take(4) ?: it.firstAirDate?.take(4).orEmpty(),
                time = it.releaseDate?.take(4).orEmpty(),
                gener = it.genreIds?.first().toString()
            )
        }
        return PopularMovies(
            movies = fiveList
        )
    }
}