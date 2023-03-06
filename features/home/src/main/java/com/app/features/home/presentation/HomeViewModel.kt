package com.app.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.home.domain.usecase.HomeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val popularMoviesUseCase: HomeUseCase
) : ViewModel() {

    private val _popularMoviesState = MutableSharedFlow<HomeState>(0)
    val popularMoviesState = _popularMoviesState.asSharedFlow()

    fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO) {
        popularMoviesUseCase()
            .flowOn(Dispatchers.IO)
            .onStart { _popularMoviesState.emit(HomeState.Loading(true)) }
            .onCompletion {
                _popularMoviesState.emit(HomeState.Loading(false))
            }
            .catch {
                _popularMoviesState.emit(HomeState.Error(it.message.orEmpty()))
            }
            .collect {
                _popularMoviesState.emit(HomeState.Success(it))
            }
    }
}