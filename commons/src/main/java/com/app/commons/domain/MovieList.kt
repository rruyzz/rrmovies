package com.app.commons.domain

sealed class MovieList {
    data class Success(val list: List<String>) : MovieList()
    object Error : MovieList()
}