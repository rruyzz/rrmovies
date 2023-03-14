package com.app.features.home.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.home.home.presentation.fragment.HomeState
import com.app.features.home.search.domain.usecase.SearchUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchMoviesUseCase: SearchUseCase,
    ): ViewModel() {

    private val _searchMoviesState = MutableSharedFlow<HomeState>(0)
    val searchMoviesState = _searchMoviesState.asSharedFlow()

    fun searchMovies(movieName: String) = viewModelScope.launch(Dispatchers.IO) {
        searchMoviesUseCase(movieName)
            .flowOn(Dispatchers.IO)
            .onStart { _searchMoviesState.emit(HomeState.Loading(true)) }
            .onCompletion {
                _searchMoviesState.emit(HomeState.Loading(false))
            }
            .catch {
                _searchMoviesState.emit(HomeState.Error(it.message.orEmpty()))
            }
            .collect {
                _searchMoviesState.emit(HomeState.Success(it))
            }
    }
}