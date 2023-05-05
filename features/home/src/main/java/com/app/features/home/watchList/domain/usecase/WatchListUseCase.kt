package com.app.features.home.watchList.domain.usecase

import com.app.commons.models.Movie
import com.app.features.home.watchList.domain.repository.WatchListRepository
import kotlinx.coroutines.flow.Flow

class WatchListUseCase(
    private val repository: WatchListRepository
) {
    suspend operator fun invoke() : Flow<List<Movie>>{
        return repository.getMovies()
    }
}