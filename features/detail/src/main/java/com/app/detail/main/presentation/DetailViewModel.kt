package com.app.detail.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.commons.domain.dao.MovieDao
import com.app.commons.models.Movie
import com.app.detail.main.domain.domain.UpdateMovie
import kotlinx.coroutines.flow.MutableSharedFlow
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
        checkHasSaved(_movie?.id)
    }

    private fun checkHasSaved(id: Int?) = viewModelScope.launch {
        id?.let{
            dao.hasSaved(it).collect{ hasSaved ->
                _detailState.emit(DetailState.HasSavedMovie(hasSaved))
            }
        }
    }
    fun onClick(click: UpdateMovie){
        when(click){
            is UpdateMovie.SaveMovie -> {
                viewModelScope.launch {
                    _movie?.let{
                        dao.upsertMovie(it)
                    }
                }
            }
            is UpdateMovie.DeleteMovie -> {
                viewModelScope.launch {
                    _movie?.let{
                        dao.deleteContact(it)
                    }
                }
            }
        }
    }

}