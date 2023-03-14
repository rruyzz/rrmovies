package com.app.features.home.home.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.commons.models.Movie
import com.app.commons.utils.hideStatusBar
import com.app.features.home.R
import com.app.features.home.databinding.FragmentHomeBinding
import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.home.presentation.adapter.HomeAdapter
import com.app.features.home.home.presentation.adapter.MoviesListAdapter
import com.app.features.home.nowPlaying.presentation.NowPlayingFragment
import com.app.features.home.popularMovies.presentation.PopularMoviesFragment
import com.app.features.home.topRated.presentation.TopRatedFragment
import com.app.features.home.upcoming.presentation.UpcomingFragment
import com.example.navigation.DetailNavigator
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val detailNavigator: DetailNavigator by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButton()
        actionObserver()
        setAdapter()
        setTab()
    }

    private fun actionObserver() = lifecycleScope.launch {
        viewModel.popularMoviesState.collect { state ->
            when (state) {
                is HomeState.Loading -> {
                    renderLoading(state.isLoading)
                }
                is HomeState.Success -> {
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
        binding.moviesList.adapter = HomeAdapter(::onClick, list, requireContext())
        binding.moviesList.layoutManager
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

    }
    private fun renderLoading(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }
    private fun onClick(movie: Movie) {
        detailNavigator.navigate(requireContext(), movie)
    }

    private fun setButton() {
        binding.textView.setOnClickListener {
            viewModel.getPopularMovies()
        }
    }
    private fun setAdapter() {
        val adapter = MoviesListAdapter(parentFragmentManager, lifecycle)
        adapter.addFragment(NowPlayingFragment())
        adapter.addFragment(UpcomingFragment())
        adapter.addFragment(TopRatedFragment())
        adapter.addFragment(PopularMoviesFragment())
        binding.viewPager.adapter = adapter

    }
    private fun setTab() = with(binding) {
        val tabList = listOf("Now playing",
            "Upcoming",
            "Top rated",
            "Popular")
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabList[position]
        }.attach()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}