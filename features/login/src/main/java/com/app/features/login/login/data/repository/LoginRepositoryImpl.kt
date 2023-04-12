package com.app.features.login.login.data.repository

import android.content.Intent
import android.content.IntentSender
import com.app.features.login.login.data.google.GoogleAuth
import com.app.features.login.login.domain.model.SignInResult
import com.app.features.login.login.domain.repository.LoginRepository

class LoginRepositoryImpl(private val googleAuth: GoogleAuth): LoginRepository {

    override suspend fun loginGoogle() : IntentSender? {
        return googleAuth.signInGoogleTap()
    }

    override suspend fun authenticationToken(intent: Intent?): SignInResult? {
        return googleAuth.signInWithIntent(intent)
    }
}