package com.app.features.home.search.presentation

import com.app.features.home.home.domain.models.PopularMovies

sealed class SearchState {
    data class Loading(val isLoading: Boolean) : SearchState()
    data class Success(val popularMovies: PopularMovies) : SearchState()
    data class Error(val error: String) : SearchState()
}