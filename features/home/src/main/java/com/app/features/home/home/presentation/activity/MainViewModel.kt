package com.app.features.home.home.presentation.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _fragmentState = MutableSharedFlow<MainState>(0)
    val fragmentState = _fragmentState.asSharedFlow()
    var query = ""
        private set

    init {
        onBottomClick(MainState.MainHomeState())
    }

    fun onBottomClick(fragmentType: MainState) = viewModelScope.launch {
        when (fragmentType) {
            is MainState.MainHomeState -> {
                _fragmentState.emit(fragmentType)
            }
            is MainState.SearchState -> {
                _fragmentState.emit(fragmentType)
            }
            is MainState.WatchListState -> {
                _fragmentState.emit(fragmentType)
            }
        }
    }

    fun setQuery(query: String) {
        this.query = query
    }
}