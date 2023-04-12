package com.app.features.login.login.domain.useCases

import android.content.Intent
import com.app.features.login.login.domain.model.SignInResult
import com.app.features.login.login.domain.repository.LoginRepository

class GoogleAuthenticationUseCase(private val repository: LoginRepository) {

    suspend operator fun invoke(intent: Intent?): SignInResult? {
        return repository.authenticationToken(intent)
    }
}