package com.app.features.home.home.presentation.activity

import com.app.features.home.home.presentation.fragment.HomeFragment
import com.app.features.home.search.presentation.SearchFragment

sealed class MainState {
    data class MainHomeState(val fragment: HomeFragment) : MainState()
    data class MainSearchState(val fragment: SearchFragment) : MainState()
    data class MainWatchListState(val fragment: HomeFragment) : MainState()
}