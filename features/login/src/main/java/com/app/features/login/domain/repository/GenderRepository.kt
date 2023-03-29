package com.app.features.login.domain.repository

import com.app.commons.models.GendersList
import kotlinx.coroutines.flow.Flow

interface GenderRepository {

    fun fetchGender() : Flow<GendersList>
}