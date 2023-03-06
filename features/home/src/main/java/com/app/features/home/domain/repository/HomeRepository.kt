package com.app.features.home.domain.repository

import com.app.features.home.domain.models.PopularMovies
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getPopularMovies(): Flow<PopularMovies>

}