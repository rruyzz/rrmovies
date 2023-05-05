package com.app.detail.main.domain.usecase

import com.app.commons.models.Movie
import com.app.detail.main.domain.repository.DetailMovieRepository
import kotlinx.coroutines.flow.Flow

class DeleteMovieUseCase(
    private val repository: DetailMovieRepository
) {
    suspend operator fun invoke(movie: Movie): Flow<Boolean> {
        return repository.deleteMovie(movie)
    }
}