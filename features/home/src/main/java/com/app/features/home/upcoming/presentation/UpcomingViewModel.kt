package com.app.features.home.upcoming.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.home.home.presentation.HomeState
import com.app.features.home.upcoming.domain.UpcomingUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UpcomingViewModel(
    private val upcomingUseCase: UpcomingUseCase
) : ViewModel() {

    private val _upcomingMoviesState = MutableSharedFlow<HomeState>(0)
    val upcomingMoviesState = _upcomingMoviesState.asSharedFlow()


    init {
        getPopularMovies()
    }

    fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO) {
        upcomingUseCase()
            .flowOn(Dispatchers.IO)
            .onStart { _upcomingMoviesState.emit(HomeState.Loading(true)) }
            .onCompletion {
                _upcomingMoviesState.emit(HomeState.Loading(false))
            }
            .catch {
                _upcomingMoviesState.emit(HomeState.Error(it.message.orEmpty()))
            }
            .collect {
                _upcomingMoviesState.emit(HomeState.Success(it))
            }
    }
}