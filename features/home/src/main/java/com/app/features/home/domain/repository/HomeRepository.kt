package com.app.features.home.domain.repository

import com.app.features.home.data.model.MovieResultResponse
import com.app.features.home.domain.models.TopMovies
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getMovies(): Flow<TopMovies>

}