package com.app.features.home.home.presentation.activity

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MainViewModel: ViewModel() {
    private val _fragmentsState = MutableSharedFlow<MainState>(0)
    val fragmentsState = _fragmentsState.asSharedFlow()


}