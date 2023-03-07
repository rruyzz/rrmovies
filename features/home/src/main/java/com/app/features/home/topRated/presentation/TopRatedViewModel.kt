package com.app.features.home.topRated.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.home.home.presentation.HomeState
import com.app.features.home.topRated.domain.TopRatedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TopRatedViewModel(
    private val topRatedUseCase: TopRatedUseCase
) : ViewModel() {

    private val _topRatedMoviesState = MutableSharedFlow<TopRatedState>(0)
    val topRatedMoviesState = _topRatedMoviesState.asSharedFlow()

    init {
        getTopRatedMovies()
    }

    fun getTopRatedMovies() = viewModelScope.launch(Dispatchers.IO) {
        topRatedUseCase()
            .flowOn(Dispatchers.IO)
            .onStart { _topRatedMoviesState.emit(TopRatedState.Loading(true)) }
            .onCompletion {
                _topRatedMoviesState.emit(TopRatedState.Loading(false))
            }
            .catch {
                _topRatedMoviesState.emit(TopRatedState.Error(it.message.orEmpty()))
            }
            .collect {
                _topRatedMoviesState.emit(TopRatedState.Success(it))
            }
    }
}