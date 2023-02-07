package com.app.features.login.data.google

import com.app.features.login.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LoginGoogle : LoginRepository {

    override fun googleLogin() : Flow<Boolean> {
        return flowOf(true)
    }
}