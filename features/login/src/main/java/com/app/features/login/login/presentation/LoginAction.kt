package com.app.features.login.login.presentation

import android.content.IntentSender

sealed class LoginAction {
    object NavigateLogin : LoginAction()
    object NavigatePassword : LoginAction()
    object NavigateCreateAccount : LoginAction()
    data class Loading(val isLoading: Boolean): LoginAction()
    data class Tap(val tap: IntentSender): LoginAction()
    data class Error(val message: String): LoginAction()
}