package com.app.features.login.splash.data.repository

import com.app.commons.models.GendersList
import com.app.features.login.splash.data.api.GenderService
import com.app.features.login.splash.data.mapper.GenderMapper
import com.app.features.login.splash.domain.repository.GenderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenderRepositoryImpl(
    private val service: GenderService,
    private val mapper: GenderMapper
) : GenderRepository {

    override fun fetchGender(): Flow<GendersList> {
        return flow {
            emit(mapper(service.getGender().body()))
        }
    }
}