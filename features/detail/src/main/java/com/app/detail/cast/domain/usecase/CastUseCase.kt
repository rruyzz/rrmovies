package com.app.detail.cast.domain.usecase

import com.app.detail.cast.domain.model.Actor
import com.app.detail.main.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow

class CastUseCase(
    private val repository: DetailRepository
) {

    operator fun invoke(id: String) : Flow<List<Actor>> {
        return repository.getCast(id)
    }
}