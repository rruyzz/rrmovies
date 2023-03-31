package com.app.detail.main.domain.domain

import com.app.commons.models.Movie

sealed interface UpdateMovie {
    object SaveMovie: UpdateMovie
    object DeleteMovie: UpdateMovie
}