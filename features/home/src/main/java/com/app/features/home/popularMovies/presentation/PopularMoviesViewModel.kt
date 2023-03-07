package com.app.features.home.popularMovies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.home.popularMovies.domain.PopularMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PopularMoviesViewModel(
    private val popularMoviesUseCase: PopularMoviesUseCase
) : ViewModel() {
    private val _popularMoviesState = MutableSharedFlow<PopularMoviesState>(0)
    val popularMoviesState = _popularMoviesState.asSharedFlow()

    init {
        getTopRatedMovies()
    }

    fun getTopRatedMovies() = viewModelScope.launch(Dispatchers.IO) {
        popularMoviesUseCase()
            .flowOn(Dispatchers.IO)
            .onStart { _popularMoviesState.emit(PopularMoviesState.Loading(true)) }
            .onCompletion {
                _popularMoviesState.emit(PopularMoviesState.Loading(false))
            }
            .catch {
                _popularMoviesState.emit(PopularMoviesState.Error(it.message.orEmpty()))
            }
            .collect {
                _popularMoviesState.emit(PopularMoviesState.Success(it))
            }
    }
}