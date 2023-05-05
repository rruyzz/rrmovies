package com.app.detail.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.commons.models.Movie
import com.app.detail.main.domain.usecase.DeleteMovieUseCase
import com.app.detail.main.domain.usecase.HasSavedMovieUseCase
import com.app.detail.main.domain.usecase.UpsertMovieUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val upsertMovieUseCase: UpsertMovieUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val hasSavedMovieUseCase: HasSavedMovieUseCase
): ViewModel() {

    private val _detailState = MutableSharedFlow<DetailState>(0)
    val detailState = _detailState.asSharedFlow()
    var movie : Movie? = null
        private set


    fun setMovie(_movie: Movie?) {
        movie = _movie
        checkHasSaved(_movie?.id)
    }

    private fun checkHasSaved(id: Int?) = viewModelScope.launch {
        id?.let{
            hasSavedMovieUseCase(it).collect{ hasSaved ->
                _detailState.emit(DetailState.HasSavedMovie(hasSaved))
            }
        }
    }
    fun onClick(click: UpdateMovie){
        when(click){
            is UpdateMovie.SaveMovie -> {
                viewModelScope.launch {
                    movie?.let{
                        upsertMovieUseCase(it).collect{ hasSaved ->
                            _detailState.emit(DetailState.HasSavedMovie(hasSaved))
                        }
                    }
                }
            }
            is UpdateMovie.DeleteMovie -> {
                viewModelScope.launch {
                    movie?.let{
                        deleteMovieUseCase(it).collect{ hasSaved ->
                            _detailState.emit(DetailState.HasSavedMovie(hasSaved))
                        }
                    }
                }
            }
        }
    }

}