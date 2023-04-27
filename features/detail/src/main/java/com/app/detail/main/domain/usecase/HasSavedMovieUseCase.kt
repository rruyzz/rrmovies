package com.app.detail.main.domain.usecase

import com.app.detail.main.domain.repository.DetailMovieRepository
import kotlinx.coroutines.flow.Flow

class HasSavedMovieUseCase(
    private val repository: DetailMovieRepository
) {
    operator fun invoke(movieId: Int): Flow<Boolean> {
        return repository.hasSaved(movieId)
    }
}