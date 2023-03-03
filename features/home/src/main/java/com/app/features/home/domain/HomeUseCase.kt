package com.app.features.home.domain

import com.app.features.home.data.model.MovieResultResponse
import com.app.features.home.data.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class HomeUseCase(
    val repository: HomeRepository
) {

    operator fun invoke() : Flow<MovieResultResponse> {
        return repository.getMovies()
    }
}