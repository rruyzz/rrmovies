package com.app.features.home.home.domain.usecase

import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class HomeUseCase(
    val repository: HomeRepository
) {

    operator fun invoke() : Flow<PopularMovies> {
        return repository.getPopularMovies()
    }
}