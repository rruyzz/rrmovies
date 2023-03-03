package com.app.features.home.data.repository

import com.app.features.home.data.model.MovieResultResponse
import com.app.features.home.data.service.HomeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    val service: HomeService
): HomeRepository {

    override fun getMovies(): Flow<MovieResultResponse> {
        return flow {
            service.getPopularMovies()
        }
    }
}