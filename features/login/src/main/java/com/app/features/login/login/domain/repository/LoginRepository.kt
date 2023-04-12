package com.app.features.login.login.domain.repository

import android.content.Intent
import android.content.IntentSender
import com.app.features.login.login.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun loginGoogle() : Flow<IntentSender>
    suspend fun authenticationToken(intent: Intent?) : Flow<UserData>
}