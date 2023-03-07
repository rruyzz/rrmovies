package com.app.features.home.upcoming.presentation

import com.app.features.home.home.domain.models.PopularMovies

sealed class UpcomingState {
    data class Loading(val isLoading: Boolean) : UpcomingState()
    data class Success(val popularMovies: PopularMovies) : UpcomingState()
    data class Error(val error: String) : UpcomingState()
}