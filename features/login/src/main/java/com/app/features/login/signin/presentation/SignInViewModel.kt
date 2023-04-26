package com.app.features.login.signin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.login.signin.domain.SignInUseCase
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase
): ViewModel() {

    private val _signInState = MutableSharedFlow<SignInState>(0)
    val signInState = _signInState.asSharedFlow()

    fun signIn(email: String, password: String) = viewModelScope.launch {
        signInUseCase(email, password)
            .onStart {
                _signInState.emit(SignInState.Loading(true))
            }
            .onCompletion {
                _signInState.emit(SignInState.Loading(false))
            }
            .catch {
                _signInState.emit(SignInState.Error(it.message ?: "ERROR"))
            }
            .collect {
                handleSuccess(it)
            }
    }

    private fun handleSuccess(authResult: AuthResult) = viewModelScope.launch {
        if(authResult.user != null) {
            _signInState.emit(SignInState.Success)
        } else {
            _signInState.emit(SignInState.Error("ERROR"))
        }
    }
}