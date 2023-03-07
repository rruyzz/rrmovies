package com.app.features.home.upcoming.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.home.upcoming.domain.UpcomingUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UpcomingViewModel(
    private val upcomingUseCase: UpcomingUseCase
) : ViewModel() {

    private val _upcomingMoviesState = MutableSharedFlow<UpcomingState>(0)
    val upcomingMoviesState = _upcomingMoviesState.asSharedFlow()

    init {
        getUpcomingMovies()
    }

    fun getUpcomingMovies() = viewModelScope.launch(Dispatchers.IO) {
        upcomingUseCase()
            .flowOn(Dispatchers.IO)
            .onStart { _upcomingMoviesState.emit(UpcomingState.Loading(true)) }
            .onCompletion {
                _upcomingMoviesState.emit(UpcomingState.Loading(false))
            }
            .catch {
                _upcomingMoviesState.emit(UpcomingState.Error(it.message.orEmpty()))
            }
            .collect {
                _upcomingMoviesState.emit(UpcomingState.Success(it))
            }
    }
}