package com.app.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.home.domain.usecase.HomeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _moviesState = MutableSharedFlow<HomeState>(0)
    val movieState = _moviesState.asSharedFlow()

    fun getMovies() = viewModelScope.launch(Dispatchers.IO) {
        homeUseCase()
            .flowOn(Dispatchers.IO)
            .onStart { _moviesState.emit(HomeState.Loading(true)) }
            .onCompletion {
                _moviesState.emit(HomeState.Loading(false))
            }
            .catch {
                _moviesState.emit(HomeState.Error(it.message.orEmpty()))
            }
            .collect {
                _moviesState.emit(HomeState.Success(it))
            }
    }
}