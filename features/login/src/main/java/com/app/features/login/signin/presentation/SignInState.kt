package com.app.features.login.signin.presentation

import com.app.features.login.signup.presentation.SignUpState

sealed class SignInState {
    data class Loading(val isLoading: Boolean) : SignInState()
    object Success : SignInState()
    data class Error(val error: String) : SignInState()
}