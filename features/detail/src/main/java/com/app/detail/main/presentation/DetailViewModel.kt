package com.app.detail.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.commons.domain.dao.MovieDao
import com.app.commons.models.Movie
import com.app.detail.main.domain.domain.UpdateMovie
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val dao: MovieDao,
): ViewModel() {

    private val _detailState = MutableSharedFlow<DetailState>(0)
    val detailState = _detailState.asSharedFlow()
    var _movie : Movie? = null
        private set


    fun setMovie(movie: Movie?) {
        _movie = movie
    }

    fun onClick(click: UpdateMovie){
        when(click){
            is UpdateMovie.SaveMovie -> {
                viewModelScope.launch {
                    _movie?.let{
                        dao.upsertMovie(it)
                        _detailState.emit(DetailState.HasSavedMovie(true))
                    }
                }
            }
            is UpdateMovie.DeleteMovie -> {
                viewModelScope.launch {
                    _movie?.let{
                        dao.deleteContact(it)
                        _detailState.emit(DetailState.HasSavedMovie(false))
                    }
                }
            }
        }
    }

}