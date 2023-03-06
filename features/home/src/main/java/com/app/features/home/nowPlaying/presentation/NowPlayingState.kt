package com.app.features.home.nowPlaying.presentation

import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.home.presentation.HomeState

sealed class NowPlayingState {
    data class Loading(val isLoading: Boolean) : NowPlayingState()
    data class Success(val nowPlayingMovies: PopularMovies) : NowPlayingState()
    data class Error(val error: String) : NowPlayingState()}