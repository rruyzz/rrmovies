package com.app.features.home.data.repository

import com.app.features.home.data.model.MovieResultResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getMovies(): Flow<MovieResultResponse>

}