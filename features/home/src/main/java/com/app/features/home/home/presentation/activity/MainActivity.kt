package com.app.features.home.home.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.commons.utils.hideStatusBar
import com.app.features.home.R
import com.app.features.home.databinding.ActivityMainBinding
import com.app.features.home.home.presentation.fragment.HomeFragment
import com.app.features.home.search.presentation.SearchFragment
import com.app.features.home.watchList.presentation.WatchListFragment
import com.google.android.material.navigation.NavigationBarView
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideStatusBar(window)
        loadFragment(HomeFragment.newInstance())
        setBottomDialog()
    }

    private fun setBottomDialog() {
        binding.navigationBar.setOnItemSelectedListener(navigationItemSelectedListener)
    }
    private val navigationItemSelectedListener =
        NavigationBarView.OnItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {
                R.id.homeApp -> {
                    fragment = HomeFragment.newInstance()
                    loadFragment(fragment)
                }
                R.id.search -> {
                    fragment = SearchFragment.newInstance()
                    loadFragment(fragment)
                }
                R.id.watchList -> {
                    fragment = WatchListFragment.newInstance()
                    loadFragment(fragment)
                }
            }
            false
        }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}