package com.app.features.login.domain.usecases

import com.app.features.login.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val repository: LoginRepository
) {

    operator fun invoke() : Flow<Boolean> {
        return repository.googleLogin()
    }
}