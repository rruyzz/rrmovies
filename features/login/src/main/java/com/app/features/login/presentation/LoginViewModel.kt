package com.app.features.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.login.domain.LoginUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

//    private val _loginResult = MutableSharedFlow<Boolean>(0)
//    val loginResult = _loginResult.asSharedFlow()
    private val emittingScope = CoroutineScope(Dispatchers.IO)
    val action = MutableSharedFlow<LoginAction>()

    fun onButtonClick() = emittingScope.launch {
        action.emit(LoginAction.Navigate)
    }
}
