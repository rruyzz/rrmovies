package com.app.features.login.login.domain.useCases

import com.app.features.login.signup.domain.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow

class ValidateEmailUseCase(
    private val repository: SignUpRepository

) {
    operator fun invoke(email: String): Flow<Boolean> {
        return repository.validateEmail(email)
    }
}