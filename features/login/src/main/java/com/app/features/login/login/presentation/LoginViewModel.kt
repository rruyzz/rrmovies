package com.app.features.login.login.presentation

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.login.login.domain.useCases.GoogleAuthenticationUseCase
import com.app.features.login.login.domain.useCases.GoogleLoginUseCase
import com.app.features.login.login.domain.useCases.ValidateEmailUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel(
    private val googleLoginUseCase: GoogleLoginUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val googleAuthenticationUseCase: GoogleAuthenticationUseCase
) : ViewModel() {

    private val _loginState = MutableSharedFlow<LoginAction>(0)
    val loginState = _loginState.asSharedFlow()

    fun validateEmail(email: String) = viewModelScope.launch {
        validateEmailUseCase(email)
            .onStart {
                _loginState.emit(LoginAction.Loading(true))
            }
            .onCompletion {
                _loginState.emit(LoginAction.Loading(false))
            }
            .catch {
                catchResultException(it)
            }
            .collect { isEmailLogged ->
                handleSuccess(isEmailLogged)
            }
    }

    private fun handleSuccess(isEmailLogged: Boolean) = viewModelScope.launch {
        if (isEmailLogged) _loginState.emit(LoginAction.NavigatePassword)
        else _loginState.emit(LoginAction.NavigateCreateAccount)
    }

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
                _loginState.emit(LoginAction.NavigateLogin)
            }
    }

    fun catchResultException(e: Throwable) = viewModelScope.launch {
        _loginState.emit(LoginAction.Error(e.message ?: "ERROR"))
    }
}