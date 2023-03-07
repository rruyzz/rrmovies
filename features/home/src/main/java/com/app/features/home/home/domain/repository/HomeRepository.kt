package com.app.features.home.home.domain.repository

import com.app.features.home.home.domain.models.PopularMovies
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getPopularMovies(): Flow<PopularMovies>
    fun getNowPlayingMovies(): Flow<PopularMovies>
    fun getUpcomingMovies(): Flow<PopularMovies>
    fun getTopRatedMovies(): Flow<PopularMovies>

}