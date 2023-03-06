package com.app.features.home.nowPlaying.domain.useCase

import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class NowPlayingUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke(): Flow<PopularMovies> {
        return repository.getNowPlayingMovies()
    }
}