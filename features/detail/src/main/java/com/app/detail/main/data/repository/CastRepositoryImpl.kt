package com.app.detail.main.data.repository

import com.app.detail.cast.domain.model.Actor
import com.app.detail.cast.data.mapper.CastMapper
import com.app.detail.main.data.service.DetailService
import com.app.detail.main.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CastRepositoryImpl(
    private val service: DetailService,
    private val mapper: CastMapper
    ) : DetailRepository {
    override fun getCast(id: String) : Flow<List<Actor>> {
        return flow {
            emit(mapper(service.getCast(id).body()))
        }
    }

}