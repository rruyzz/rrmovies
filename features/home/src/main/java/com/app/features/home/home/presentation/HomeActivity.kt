package com.app.features.home.home.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.features.home.R
import com.app.features.home.databinding.ActivityHomeBinding
import com.app.features.home.home.presentation.fragment.HomeFragment
import com.app.features.home.nowPlaying.presentation.NowPlayingFragment
import com.google.android.material.navigation.NavigationBarView


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
                    fragment = HomeFragment()
                    loadFragment(fragment)
                }
                R.id.search -> {
                }
                R.id.watchList -> {
                    fragment = NowPlayingFragment()
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