package com.app.features.login.login.domain.useCases

import android.content.IntentSender
import com.app.features.login.login.domain.repository.LoginRepository

class GoogleLoginUseCase(private val repository: LoginRepository) {

    suspend operator fun invoke() : IntentSender? {
        return repository.loginGoogle()
    }
}