package com.app.features.login.login.presentation

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.login.login.domain.useCases.GoogleAuthenticationUseCase
import com.app.features.login.login.domain.useCases.GoogleLoginUseCase
import com.app.features.login.signup.domain.usecase.CreateUserUseCase
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel(
    private val googleLoginUseCase: GoogleLoginUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val googleAuthenticationUseCase: GoogleAuthenticationUseCase
) : ViewModel() {

    private val _loginState = MutableSharedFlow<LoginAction>(0)
    val loginState = _loginState.asSharedFlow()

    fun signUp(email: String, password: String = "123456") = viewModelScope.launch {
        createUserUseCase(email, password)
            .onStart {
                _loginState.emit(LoginAction.Loading(true))
            }
            .onCompletion {
                _loginState.emit(LoginAction.Loading(false))
            }
            .catch {
                handleError(it)
            }
            .collect {
                _loginState.emit(LoginAction.NavigateLogin)
            }
    }

    private fun handleError(it: Throwable) = viewModelScope.launch {
        if (it.isEmailAlreadyInUse()) _loginState.emit(LoginAction.NavigatePassword)
        else _loginState.emit(LoginAction.NavigateCreateAccount)
    }

    private fun Throwable.isEmailAlreadyInUse(): Boolean {
        return try {
            this == (this as FirebaseAuthUserCollisionException).zzc("ERROR_EMAIL_ALREADY_IN_USE")
        } catch (e: Exception) {
            false
        }
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