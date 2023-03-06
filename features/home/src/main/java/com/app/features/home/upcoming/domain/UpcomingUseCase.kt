package com.app.features.home.upcoming.domain

import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class UpcomingUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke(): Flow<PopularMovies> {
        return repository.getUpcomingMovies()
    }
}
