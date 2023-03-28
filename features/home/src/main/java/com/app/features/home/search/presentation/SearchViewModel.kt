package com.app.features.home.search.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.home.home.presentation.fragment.HomeState
import com.app.features.home.search.domain.usecase.SearchUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchMoviesUseCase: SearchUseCase,
    ): ViewModel() {

    private val _searchMoviesState = MutableSharedFlow<SearchState>(0)
    val searchMoviesState = _searchMoviesState.asSharedFlow()

    private val _query = MutableStateFlow<String?>(null)
    val query = _query.asSharedFlow()

    fun getQuery(bundle: Bundle?) {
        val text = bundle?.getString("text")
        text?.let {
            _query.value = it
            searchMovies(it)
        }
    }
    fun searchMovies(movieName: String) = viewModelScope.launch(Dispatchers.IO) {
        searchMoviesUseCase(movieName)
            .flowOn(Dispatchers.IO)
            .onStart { _searchMoviesState.emit(SearchState.Loading(true)) }
            .onCompletion {
                _searchMoviesState.emit(SearchState.Loading(false))
            }
            .catch {
                _searchMoviesState.emit(SearchState.Error(it.message.orEmpty()))
            }
            .collect {
                _searchMoviesState.emit(SearchState.Success(it))
            }
    }
}