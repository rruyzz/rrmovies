package com.app.features.login.login.presentation

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.login.login.domain.useCases.GoogleAuthenticationUseCase
import com.app.features.login.login.domain.useCases.GoogleLoginUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel(
    private val googleLoginUseCase: GoogleLoginUseCase,
    private val googleAuthenticationUseCase: GoogleAuthenticationUseCase
) : ViewModel() {

    private val _loginState = MutableSharedFlow<LoginAction>(0)
    val loginState = _loginState.asSharedFlow()

    fun signInGoogle() = viewModelScope.launch {
        googleLoginUseCase()
            .onStart {
                _loginState.emit(LoginAction.Loading(true))
            }
            .onCompletion {
                _loginState.emit(LoginAction.Loading(false))
            }
            .catch {
                catchResultException(it)
            }
            .collect {
                _loginState.emit(LoginAction.Tap(it))
            }
    }

    fun authenticate(intent: Intent?) = viewModelScope.launch {
        googleAuthenticationUseCase(intent)
            .onStart {
                _loginState.emit(LoginAction.Loading(true))
            }
            .onCompletion {
                _loginState.emit(LoginAction.Loading(false))
            }
            .catch {
                catchResultException(it)
            }
            .collect {
                if (it?.data != null) {
                    _loginState.emit(LoginAction.NavigateLogin)
                } else {
                    _loginState.emit(LoginAction.Error(it?.errorMessage ?: "ERROR"))
                }
            }
    }

    fun catchResultException(e: Throwable) = viewModelScope.launch {
        _loginState.emit(LoginAction.Error(e.localizedMessage ?: "ERROR"))
    }
}