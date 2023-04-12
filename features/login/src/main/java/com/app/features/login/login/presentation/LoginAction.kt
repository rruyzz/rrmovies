package com.app.features.login.login.presentation

import android.content.IntentSender
import com.app.features.login.splash.presentation.SplashAction

sealed class LoginAction {
    object NavigateLogin : LoginAction()
    data class Loading(val isLoading: Boolean): LoginAction()
    data class Tap(val tap: IntentSender?): LoginAction()
    data class Error(val message: String): LoginAction()
}