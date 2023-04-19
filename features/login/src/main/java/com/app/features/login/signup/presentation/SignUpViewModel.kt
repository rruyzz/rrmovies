package com.app.features.login.signup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.login.signup.domain.usecase.SignUpUseCase
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _signUpState = MutableSharedFlow<SignUpState>(0)
    val signUpState = _signUpState.asSharedFlow()

    fun signUp(email: String, password: String) = viewModelScope.launch {
        signUpUseCase(email, password)
            .onStart {
                _signUpState.emit(SignUpState.Loading(true))
            }
            .onCompletion {
                _signUpState.emit(SignUpState.Loading(false))
            }
            .catch {
                _signUpState.emit(SignUpState.Error(it.message ?: "ERROR"))
            }
            .collect {
                handleSuccess(it)
            }
    }

    private fun handleSuccess(authResult: AuthResult) = viewModelScope.launch {
        if(authResult.user != null) {
            _signUpState.emit(SignUpState.Success)
        } else {
            _signUpState.emit(SignUpState.Error("ERROR"))
        }
    }
}