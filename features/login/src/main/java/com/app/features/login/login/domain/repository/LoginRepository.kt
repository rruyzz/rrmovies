package com.app.features.login.login.domain.repository

import android.content.Intent
import android.content.IntentSender
import com.app.features.login.login.domain.model.SignInResult

interface LoginRepository {
    suspend fun loginGoogle() : IntentSender?
    suspend fun authenticationToken(intent: Intent?) : SignInResult?
}