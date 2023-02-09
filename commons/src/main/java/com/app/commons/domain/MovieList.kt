package com.app.commons.domain

sealed class MovieList {
    data class Success(val list: List<String>) : MovieList()
    data class Loading(val isLoading: Boolean) : MovieList()
    object Error : MovieList()
}