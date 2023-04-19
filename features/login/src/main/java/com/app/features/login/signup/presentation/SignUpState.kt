package com.app.features.login.signup.presentation

sealed class SignUpState {
    data class Loading(val isLoading: Boolean) : SignUpState()
    object Success : SignUpState()
    data class Error(val error: String) : SignUpState()
}