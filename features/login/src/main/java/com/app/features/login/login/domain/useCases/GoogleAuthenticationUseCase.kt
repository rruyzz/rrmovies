package com.app.features.login.login.domain.useCases

import android.content.Intent
import com.app.features.login.login.domain.model.UserData
import com.app.features.login.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class GoogleAuthenticationUseCase(private val repository: LoginRepository) {

    suspend operator fun invoke(intent: Intent?): Flow<UserData?> {
        return repository.authenticationToken(intent)
    }
}