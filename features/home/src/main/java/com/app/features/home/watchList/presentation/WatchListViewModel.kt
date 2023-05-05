package com.app.features.home.watchList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.home.watchList.domain.usecase.WatchListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WatchListViewModel(
    private val useCase: WatchListUseCase
): ViewModel() {

    private val _watchListMoviesState = MutableSharedFlow<WatchListState>(0)
    val watchListMoviesState = _watchListMoviesState.asSharedFlow()


    init {
        getPopularMovies()
    }

    fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO) {
        useCase()
            .onCompletion {
                _watchListMoviesState.emit(WatchListState.Loading(false))
            }
            .catch {
                _watchListMoviesState.emit(WatchListState.Error(it.toString()))

            }
            .collect {
                _watchListMoviesState.emit(WatchListState.Success(it))
            }
    }

}