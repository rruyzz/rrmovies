package com.app.detail.main.domain.repository

import com.app.commons.models.Movie
import kotlinx.coroutines.flow.Flow

interface DetailMovieRepository {

    suspend fun deleteMovie(movie: Movie)
    fun hasSaved(id: Int): Flow<Boolean>
    suspend fun upsertMovie(movie: Movie)

}