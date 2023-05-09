package com.app.features.home.home.presentation.activity

import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.app.commons.utils.hideStatusBar
import com.app.features.home.R
import com.app.features.home.databinding.ActivityMainBinding
import com.app.features.home.profile.presentation.ProfileFragment
import com.app.features.home.search.presentation.SearchFragment
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideStatusBar(window)
        setBottomDialog()
        observer()
    }

    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        return super.getOnBackInvokedDispatcher()
    }

    override fun onBackPressed() {
        if (binding.navigationBar.selectedItemId != R.id.homeApp) {
            binding.navigationBar.selectedItemId = R.id.homeApp
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun observer() = lifecycleScope.launch {
        viewModel.fragmentState.collect {
            loadFragment(it.fragment)
        }
    }
//    android:layout_gravity="top"

    private fun setBottomDialog() {
        binding.navigationBar.selectedItemId = R.id.homeApp
        binding.navigationBar.setOnItemSelectedListener(navigationItemSelectedListener)
    }
    private val navigationItemSelectedListener =
        NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeApp -> {
                    viewModel.onBottomClick(MainState.MainHomeState())
                }
                R.id.search -> {
                    viewModel.onBottomClick(MainState.SearchState(SearchFragment.newInstance(viewModel.query)))
                    viewModel.setQuery("")
                }
                R.id.watchList -> {
                    viewModel.onBottomClick(MainState.WatchListState())
                }
                R.id.profile -> {
                    viewModel.onBottomClick(MainState.ProfileState())
                }
            }
            true
        }

    private fun loadFragment(fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, it)
                .commitNow()
        }
    }

    fun navigateSearch(query: String) {
        viewModel.setQuery(query)
        binding.navigationBar.selectedItemId = R.id.search
    }
}