package com.app.features.login.signup.domain.usecase

import com.app.features.login.signup.domain.repository.SignUpRepository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

class CreateUserUseCase(
    private val repository: SignUpRepository
) {

    operator fun invoke(email: String, password: String): Flow<AuthResult> {
        return repository.createUser(email, password)
    }

}