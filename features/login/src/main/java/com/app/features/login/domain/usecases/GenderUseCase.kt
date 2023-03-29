package com.app.features.login.domain.usecases

import com.app.commons.models.GendersList
import com.app.features.login.domain.repository.GenderRepository
import kotlinx.coroutines.flow.Flow

class GenderUseCase(private val repository: GenderRepository) {

    operator fun invoke() : Flow<GendersList> {
        return repository.fetchGender()
    }
}