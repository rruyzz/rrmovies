package com.app.features.login.signup.data.repository

import com.app.features.login.signup.data.firebaseSignup.SignUpAuth
import com.app.features.login.signup.domain.repository.SignUpRepository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SignUpRepositoryImpl(
    private val singUpAuth: SignUpAuth
) : SignUpRepository {

    override fun createUser(email: String, password: String): Flow<AuthResult> {
        return flow {
            emit(singUpAuth.createUser(email, password))
        }
    }

    override fun loginEmail(email: String, password: String): Flow<AuthResult> {
        return flow {
            emit(singUpAuth.loginEmail(email, password))
        }
    }

    override fun validateEmail(email: String): Flow<Boolean> {
        return flow {
            if(singUpAuth.validateEmail(email).signInMethods?.contains("google.com") == true) {
                throw Exception("Esse email ja esta cadastrado com a conta Google")
            }
            val isNewEmail = singUpAuth.validateEmail(email).signInMethods?.isEmpty() ?: true
            emit(isNewEmail)
        }
    }
}