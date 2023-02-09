package com.app.features.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.commons.domain.MovieList
import com.app.features.login.domain.LoginUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginResult = MutableSharedFlow<MovieList>(0)
    val loginResult = _loginResult.asSharedFlow()

    fun signIn() = viewModelScope.launch {
        _loginResult.emit(MovieList.Loading(true))
        delay(3000L)
        _loginResult.emit(MovieList.Loading(false))
        _loginResult.emit(MovieList.Success(listOf("rodolfo","rodolfo","rodolfo","rodolfo")))

    }
}