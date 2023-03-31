package com.app.detail.main.presentation

import com.app.commons.models.Movie

sealed class DetailState {
    data class HasSavedMovie(val hasSaved: Boolean): DetailState()
}