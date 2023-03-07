package com.app.features.home.topRated.domain

import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class TopRatedUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke(): Flow<PopularMovies> {
        return repository.getTopRatedMovies()
    }
}
