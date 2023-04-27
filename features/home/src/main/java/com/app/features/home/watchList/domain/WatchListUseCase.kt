package com.app.features.home.watchList.domain

import com.app.commons.models.Movie
import com.app.commons.room.MovieDao
import kotlinx.coroutines.flow.Flow

class WatchListUseCase(
    private val dao: MovieDao
) {
    operator fun invoke() : Flow<List<Movie>>{
        return dao.getMovies()
    }
}