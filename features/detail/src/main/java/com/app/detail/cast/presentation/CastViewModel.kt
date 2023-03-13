package com.app.detail.cast.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.detail.cast.domain.usecase.CastUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CastViewModel(
    private val castUseCase: CastUseCase
): ViewModel() {

    private val _castMoviesState = MutableSharedFlow<CastState>(0)
    val castMoviesState = _castMoviesState.asSharedFlow()

    fun getCast(id: String) = viewModelScope.launch(Dispatchers.IO) {
        castUseCase(id)
            .flowOn(Dispatchers.IO)
            .onStart { _castMoviesState.emit(CastState.Loading(true)) }
            .onCompletion {
                _castMoviesState.emit(CastState.Loading(false))
            }
            .catch {
                _castMoviesState.emit(CastState.Error(it.message.orEmpty()))
            }
            .collect {
                _castMoviesState.emit(CastState.Success(it))
            }
    }

}