package com.app.features.login.signup.domain.repository

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {
    fun createUser(email: String, password: String): Flow<AuthResult>
    fun loginEmail(email: String, password: String): Flow<AuthResult>
    fun validateEmail(email: String): Flow<Boolean>
}