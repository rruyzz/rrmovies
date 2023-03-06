package com.app.features.home.domain.usecase

import com.app.features.home.domain.models.TopMovies
import com.app.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class HomeUseCase(
    val repository: HomeRepository
) {

    operator fun invoke() : Flow<TopMovies> {
        return repository.getMovies()
    }
}