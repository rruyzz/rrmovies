package com.app.detail.main.domain.usecase

import com.app.commons.models.Movie
import com.app.commons.room.MovieDao

class DeleteMovieUseCase(
    private val dao: MovieDao
) {
    suspend operator fun invoke(movie: Movie) {
        dao.deleteContact(movie)
    }
}