package com.app.features.login.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.commons.gender.Genders
import com.app.features.login.splash.domain.usecases.GenderUseCase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SplashViewModel(
    private val genderUseCase: GenderUseCase
) : ViewModel() {

    private val _fetchResult = MutableSharedFlow<Boolean>(0)
    val fetchResult = _fetchResult.asSharedFlow()
    private val emittingScope = CoroutineScope(Dispatchers.IO)
    val action = MutableSharedFlow<SplashAction>()
    private val user = Firebase.auth.currentUser

    init {
        fetchGenders()
    }

    private fun fetchGenders() = viewModelScope.launch(Dispatchers.IO) {
        genderUseCase()
            .flowOn(Dispatchers.IO)
            .onStart { _fetchResult.emit(true) }
            .onCompletion {
                handleSuccess()
            }
            .collect {
                Genders.setList(it)
            }
    }

    private fun handleSuccess() = emittingScope.launch {
        if (user != null) action.emit(SplashAction.NavigateHome)
        else action.emit(SplashAction.NavigateLogin)
    }

    fun onButtonClick() = emittingScope.launch {
        if (user != null) action.emit(SplashAction.NavigateHome)
        else action.emit(SplashAction.NavigateLogin)
    }
}
