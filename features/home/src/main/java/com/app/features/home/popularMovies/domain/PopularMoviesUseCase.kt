package com.app.features.home.popularMovies.domain

import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class PopularMoviesUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke(): Flow<PopularMovies> {
        return repository.getTopRatedMoviesComplete()
    }
}