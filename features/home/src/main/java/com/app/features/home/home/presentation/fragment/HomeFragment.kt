package com.app.features.home.home.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.commons.models.Movie
import com.app.features.home.databinding.FragmentHomeBinding
import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.home.presentation.activity.MainActivity
import com.app.features.home.home.presentation.adapter.HomeAdapter
import com.app.features.home.home.presentation.adapter.MoviesListAdapter
import com.app.features.home.nowPlaying.presentation.NowPlayingFragment
import com.app.features.home.popularMovies.presentation.PopularMoviesFragment
import com.app.features.home.topRated.presentation.TopRatedFragment
import com.app.features.home.upcoming.presentation.UpcomingFragment
import com.example.navigation.DetailNavigator
import com.example.navigation.LoginNavigator
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val detailNavigator: DetailNavigator by inject()
    private val loginNavigator: LoginNavigator by inject()

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
                is HomeState.FinishAffinity -> {
                    requireActivity().finishAffinity()
                    loginNavigator.navigate(requireContext())
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
        setAdapter()
        setTab()
    }

    private fun renderLoading(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
        binding.search.isVisible = isLoading.not()
        binding.textView.isVisible = isLoading.not()
        binding.tabLayout.isVisible = isLoading.not()
        binding.viewPager.isVisible = isLoading.not()
    }
    private fun onClick(movie: Movie) {
        detailNavigator.navigate(requireContext(), movie)
    }

    private fun setButton() {
        binding.textView.setOnClickListener {
            viewModel.getPopularMovies()
        }
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                (activity as MainActivity?)?.navigateSearch(query)
                return false
            }
        })
        binding.textView.setOnClickListener {
            viewModel.signOut()
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
        val tabList = listOf(
            "Now playing",
            "Upcoming",
            "Top rated",
            "Popular"
        )
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabList[position]
        }.attach()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}