package com.app.detail.main.data.repository

import com.app.commons.models.Movie
import com.app.commons.room.MovieDao
import com.app.detail.main.domain.repository.DetailMovieRepository
import kotlinx.coroutines.flow.Flow

class DetailMovieRepositoryImpl(
    private val dao: MovieDao
) : DetailMovieRepository {
    override suspend fun deleteMovie(movie: Movie) {
        dao.deleteContact(movie)
    }

    override fun hasSaved(id: Int): Flow<Boolean> {
        return dao.hasSaved(id)
    }

    override suspend fun upsertMovie(movie: Movie) {
        dao.upsertMovie(movie)
    }
}