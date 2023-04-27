package com.app.detail.main.presentation

sealed interface UpdateMovie {
    object SaveMovie: UpdateMovie
    object DeleteMovie: UpdateMovie
}