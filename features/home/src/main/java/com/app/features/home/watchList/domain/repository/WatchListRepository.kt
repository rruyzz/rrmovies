package com.app.features.home.watchList.domain.repository

import com.app.commons.models.Movie
import kotlinx.coroutines.flow.Flow

interface WatchListRepository {

    suspend fun getMovies() : Flow<List<Movie>>
}