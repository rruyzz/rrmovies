package com.app.features.home.home.presentation.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.home.home.domain.usecase.HomeUseCase
import com.app.features.home.home.domain.usecase.SearchUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val popularMoviesUseCase: HomeUseCase,
    private val searchMoviesUseCase: SearchUseCase,
) : ViewModel() {

    private val _popularMoviesState = MutableSharedFlow<HomeState>(0)
    val popularMoviesState = _popularMoviesState.asSharedFlow()

    init {
        getPopularMovies()
    }
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

    fun searchMovies(movieName: String) = viewModelScope.launch(Dispatchers.IO) {
        searchMoviesUseCase(movieName)
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