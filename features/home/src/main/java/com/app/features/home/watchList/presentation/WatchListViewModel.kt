package com.app.features.home.watchList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.commons.domain.dao.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WatchListViewModel(
    private val dao: MovieDao
): ViewModel() {

    private val _watchListMoviesState = MutableSharedFlow<WatchListState>(0)
    val watchListMoviesState = _watchListMoviesState.asSharedFlow()


    init {
        getPopularMovies()
    }

    private fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO) {
        dao.getMovies()
            .flowOn(Dispatchers.IO)
            .collect {
                _watchListMoviesState.emit(WatchListState.Success(it))
            }
    }

}