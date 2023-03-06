package com.app.features.home.data.repository

import com.app.features.home.data.mapper.TopMoviesMapper
import com.app.features.home.data.service.HomeService
import com.app.features.home.domain.models.Movie
import com.app.features.home.domain.models.TopMovies
import com.app.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val service: HomeService,
    private val mapper: TopMoviesMapper
) : HomeRepository {

    override fun getMovies(): Flow<TopMovies> {
        return flow {
            emit(mapper(service.getPopularMovies().body()))
        }
    }
}