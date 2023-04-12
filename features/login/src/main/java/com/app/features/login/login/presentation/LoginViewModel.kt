package com.app.features.login.login.presentation

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.login.login.domain.useCases.GoogleAuthenticationUseCase
import com.app.features.login.login.domain.useCases.GoogleLoginUseCase
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel(
    private val googleLoginUseCase: GoogleLoginUseCase,
    private val googleAuthenticationUseCase: GoogleAuthenticationUseCase
) : ViewModel() {

    private val _loginState = MutableSharedFlow<LoginAction>(0)
    val loginState = _loginState.asSharedFlow()

    fun signInGoogle() = viewModelScope.launch {
        _loginState.emit(LoginAction.Tap(googleLoginUseCase()))
    }

    fun authenticate(intent: Intent?) = viewModelScope.launch {
        val result = googleAuthenticationUseCase(intent)
        if(result?.data != null) {
            _loginState.emit(LoginAction.NavigateLogin)
        } else {
            _loginState.emit(LoginAction.Error(result?.errorMessage ?: "ERROR"))
        }
    }

    fun catchResultException(e: ApiException) = viewModelScope.launch {
        _loginState.emit(LoginAction.Error(e.localizedMessage ?: "ERROR"))
    }
}