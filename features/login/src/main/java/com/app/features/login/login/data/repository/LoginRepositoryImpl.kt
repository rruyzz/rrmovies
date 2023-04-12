package com.app.features.login.login.data.repository

import android.content.Intent
import android.content.IntentSender
import com.app.features.login.login.data.google.GoogleAuth
import com.app.features.login.login.domain.model.SignInResult
import com.app.features.login.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl(private val googleAuth: GoogleAuth) : LoginRepository {

    override suspend fun loginGoogle(): Flow<IntentSender?> {
        return flow {
            emit(googleAuth.signInGoogleTap())
        }
    }

    override suspend fun authenticationToken(intent: Intent?): Flow<SignInResult?> {
        return flow {
            emit(googleAuth.signInWithIntent(intent))
        }
    }
}