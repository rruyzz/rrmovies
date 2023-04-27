package com.app.detail.main.domain.usecase

import com.app.commons.models.Movie
import com.app.detail.main.domain.repository.DetailMovieRepository

class UpsertMovieUseCase(
    private val repository: DetailMovieRepository
) {
    suspend operator fun invoke(movie: Movie) {
        repository.upsertMovie(movie)
    }
}