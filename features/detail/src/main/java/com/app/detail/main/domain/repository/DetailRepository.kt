package com.app.detail.main.domain.repository

import com.app.detail.cast.domain.model.Actor
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    fun getCast(id: String) : Flow<List<Actor>>
}