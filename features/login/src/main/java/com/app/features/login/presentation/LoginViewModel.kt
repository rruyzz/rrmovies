package com.app.features.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.login.domain.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginResult = MutableSharedFlow<Boolean>(0)
    val loginResult = _loginResult.asSharedFlow()

    fun signIn() = viewModelScope.launch {
        loginUseCase.invoke().collect {
            _loginResult.emit(it)
        }
    }
}