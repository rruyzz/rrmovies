package com.app.features.login.login.domain.useCases

import android.content.IntentSender
import com.app.features.login.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class GoogleLoginUseCase(private val repository: LoginRepository) {

    suspend operator fun invoke() : Flow<IntentSender?> {
        return repository.loginGoogle()
    }
}