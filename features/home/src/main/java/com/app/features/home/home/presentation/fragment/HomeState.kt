package com.app.features.home.home.presentation.fragment

import com.app.features.home.home.domain.models.PopularMovies

sealed class HomeState {
    data class Loading(val isLoading: Boolean) : HomeState()
    data class Success(val popularMovies: PopularMovies) : HomeState()
    data class Error(val error: String) : HomeState()
}