package com.app.features.home.topRated.presentation

import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.upcoming.presentation.UpcomingState

sealed class TopRatedState {
    data class Loading(val isLoading: Boolean) : TopRatedState()
    data class Success(val popularMovies: PopularMovies) : TopRatedState()
    data class Error(val error: String) : TopRatedState()
}