package com.app.features.login.signin.domain

import com.app.features.login.signup.domain.repository.SignUpRepository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

class SignInUseCase(
    private val repository: SignUpRepository
) {

    operator fun invoke(email: String, password: String): Flow<AuthResult> {
        return repository.loginEmail(email, password)
    }

}