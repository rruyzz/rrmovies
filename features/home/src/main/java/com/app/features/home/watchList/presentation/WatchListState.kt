package com.app.features.home.watchList.presentation

import com.app.commons.models.Movie

sealed class WatchListState {
    data class Success(val movies: List<Movie>) : WatchListState()
}