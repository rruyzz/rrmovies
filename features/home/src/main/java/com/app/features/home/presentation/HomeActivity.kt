package com.app.features.home.presentation

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.features.home.databinding.ActivityHomeBinding
import com.app.features.home.domain.models.Movie
import com.app.features.home.domain.models.PopularMovies
import com.app.features.home.presentation.adapter.HomeAdapter
import com.example.navigation.LoginNavigator
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val loginNavigator: LoginNavigator by inject()
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getPopularMovies()
        setButton()
        actionObserver()
        hideStatusBar()
    }

    private fun actionObserver() = lifecycleScope.launch {
        viewModel.popularMoviesState.collect { state ->
            when (state) {
                is HomeState.Loading -> {
                    renderLoading(state.isLoading)
                }
                is HomeState.Success ->{
                    renderSuccess(state.popularMovies)
                }
                is HomeState.Error -> {
                    renderError(state.error)
                }
            }
        }
    }

    private fun renderError(error: String) {
        binding.textView.text = error
    }
    private fun renderSuccess(list: PopularMovies) {
        binding.moviesList.apply {
            adapter = homeAdapter(list)
            layoutManager = linearLayoutManeger()
        }
    }

    private fun homeAdapter(list: PopularMovies) = HomeAdapter(::onClick, list, this)
    private fun linearLayoutManeger() =
        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    private fun renderLoading(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }

    private fun onClick(movie: Movie) {
    }

    private fun setButton() {
        binding.textView.setOnClickListener {
            viewModel.getPopularMovies()
        }
    }

    private fun hideStatusBar() {
        with(window) {
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            attributes.flags =
                window.attributes.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
            statusBarColor = Color.TRANSPARENT
        }
    }
}