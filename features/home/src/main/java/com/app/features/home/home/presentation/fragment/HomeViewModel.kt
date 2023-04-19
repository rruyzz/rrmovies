package com.app.features.home.home.presentation.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.home.home.domain.usecase.HomeUseCase
import com.app.features.home.search.domain.usecase.SearchUseCase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val popularMoviesUseCase: HomeUseCase,
    private val searchMoviesUseCase: SearchUseCase,
) : ViewModel() {

    private val _popularMoviesState = MutableSharedFlow<HomeState>(0)
    val popularMoviesState = _popularMoviesState.asSharedFlow()

    init {
        getPopularMovies()
    }

    fun signOut() = viewModelScope.launch(Dispatchers.IO) {
        Firebase.auth.signOut()
        _popularMoviesState.emit(HomeState.FinishAffinity)
    }
    fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO) {
        popularMoviesUseCase()
            .flowOn(Dispatchers.IO)
            .onStart { _popularMoviesState.emit(HomeState.Loading(true)) }
            .onCompletion {
                _popularMoviesState.emit(HomeState.Loading(false))
            }
            .catch {
                _popularMoviesState.emit(HomeState.Error(it.message.orEmpty()))
            }
            .collect {
                _popularMoviesState.emit(HomeState.Success(it))
            }
    }
}