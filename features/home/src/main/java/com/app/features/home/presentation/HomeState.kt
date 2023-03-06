package com.app.features.home.presentation

import com.app.features.home.domain.models.TopMovies

sealed class HomeState {
    data class Loading(val isLoading: Boolean) : HomeState()
    data class Success(val success: TopMovies) : HomeState()
    data class Error(val error: String) : HomeState()
}