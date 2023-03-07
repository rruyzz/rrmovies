package com.app.features.home.popularMovies.presentation

import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.topRated.presentation.TopRatedState

sealed class PopularMoviesState {
    data class Loading(val isLoading: Boolean) : PopularMoviesState()
    data class Success(val popularMovies: PopularMovies) : PopularMoviesState()
    data class Error(val error: String) : PopularMoviesState()
}