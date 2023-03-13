package com.app.detail.cast.presentation

import com.app.detail.cast.domain.model.Actor

sealed class CastState {
    data class Loading(val isLoading: Boolean) : CastState()
    data class Success(val cast: List<Actor>) : CastState()
    data class Error(val error: String) : CastState()
}