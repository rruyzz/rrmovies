package com.app.features.home.nowPlaying.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.home.home.presentation.HomeState
import com.app.features.home.nowPlaying.domain.useCase.NowPlayingUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NowPlayingViewModel(
    private val nowPlayingUseCase: NowPlayingUseCase
) : ViewModel() {

    private val _nowPlayingMoviesState = MutableSharedFlow<HomeState>(0)
    val nowPlayingMoviesState = _nowPlayingMoviesState.asSharedFlow()


    init {
        getPopularMovies()
    }

    fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO) {
        nowPlayingUseCase()
            .flowOn(Dispatchers.IO)
            .onStart { _nowPlayingMoviesState.emit(HomeState.Loading(true)) }
            .onCompletion {
                _nowPlayingMoviesState.emit(HomeState.Loading(false))
            }
            .catch {
                _nowPlayingMoviesState.emit(HomeState.Error(it.message.orEmpty()))
            }
            .collect {
                _nowPlayingMoviesState.emit(HomeState.Success(it))
            }
    }
}