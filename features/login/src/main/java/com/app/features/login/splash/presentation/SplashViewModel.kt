package com.app.features.login.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.commons.gender.Genders
import com.app.features.login.splash.domain.usecases.GenderUseCase
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

    init {
        fetchGenders()
    }
    private fun fetchGenders() = viewModelScope.launch(Dispatchers.IO) {
        genderUseCase()
            .flowOn(Dispatchers.IO)
            .onStart { _fetchResult.emit(true) }
            .onCompletion {
                action.emit(SplashAction.Navigate)
            }
            .collect {
                Genders.setList(it)
            }
    }
    fun onButtonClick() = emittingScope.launch {
        action.emit(SplashAction.Navigate)
    }
}
