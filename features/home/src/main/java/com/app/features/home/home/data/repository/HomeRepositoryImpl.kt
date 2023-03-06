package com.app.features.home.home.data.repository

import com.app.features.home.home.data.mapper.PopularMoviesMapper
import com.app.features.home.home.data.service.HomeService
import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val service: HomeService,
    private val mapper: PopularMoviesMapper
) : HomeRepository {

    override fun getPopularMovies(): Flow<PopularMovies> {
        return flow {
            emit(mapper(service.getPopularMovies().body()))
        }
    }
}