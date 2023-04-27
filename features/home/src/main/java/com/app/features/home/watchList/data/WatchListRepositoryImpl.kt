package com.app.features.home.watchList.data

import com.app.commons.models.Movie
import com.app.commons.room.MovieDao
import com.app.features.home.watchList.domain.repository.WatchListRepository
import kotlinx.coroutines.flow.Flow

class WatchListRepositoryImpl(
    private val dao: MovieDao
) : WatchListRepository {
    override fun getMovies(): Flow<List<Movie>> {
        return dao.getMovies()
    }
}