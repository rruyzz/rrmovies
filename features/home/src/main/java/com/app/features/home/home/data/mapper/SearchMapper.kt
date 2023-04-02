package com.app.features.home.home.data.mapper

import com.app.commons.gender.GenderListMapper
import com.app.commons.models.Movie
import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.search.data.model.SearchMoviesResponse

class SearchMapper(
    private val genderListMapper: GenderListMapper,
) {

    operator fun invoke(response: SearchMoviesResponse?
    ) : PopularMovies {
        val list = response?.results.orEmpty().map {
            Movie(
                id = it.id ?: 0,
                poster = it.posterPath.orEmpty(),
                posterBack = it.backdropPath.orEmpty(),
                title = it.titleName.orEmpty(),
                description = it.overview.orEmpty(),
                year = it.releaseDate?.take(4) ?: it.firstAirDate?.take(4).orEmpty(),
                time = it.releaseDate?.take(4).orEmpty(),
                gender = genderListMapper(it.genreIds),
                grade = it.voteAverage.toString()
            )
        }.filter {
            it.poster.isNotEmpty() && it.title.isNotEmpty()
        }
        return PopularMovies(
            movies = list
        )
    }
}