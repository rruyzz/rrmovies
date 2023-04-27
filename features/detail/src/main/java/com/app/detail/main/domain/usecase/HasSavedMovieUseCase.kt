package com.app.detail.main.domain.usecase

import com.app.commons.room.MovieDao
import kotlinx.coroutines.flow.Flow

class HasSavedMovieUseCase(
    private val dao: MovieDao
) {
    operator fun invoke(movieId: Int): Flow<Boolean> {
        return dao.hasSaved(movieId)
    }
}