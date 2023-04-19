package com.app.features.login.signup.domain.repository

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {
    fun signUp(email: String, password: String): Flow<AuthResult>
}