package com.app.features.home.home.presentation.activity

import androidx.fragment.app.Fragment
import com.app.features.home.home.presentation.fragment.HomeFragment
import com.app.features.home.search.presentation.SearchFragment
import com.app.features.home.watchList.presentation.WatchListFragment

sealed class MainState(open val fragment: Fragment? = null) {
    data class MainHomeState(override val fragment: HomeFragment = HomeFragment.newInstance()) :
        MainState(fragment = fragment)

    data class SearchState(override val fragment: SearchFragment = SearchFragment.newInstance()) :
        MainState(fragment = fragment)

    data class WatchListState(override val fragment: WatchListFragment = WatchListFragment.newInstance()) :
        MainState(fragment = fragment)
}
