package com.app.features.home.upcoming.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.app.features.home.databinding.FragmentGridRecyclerLayoutBinding
import com.app.features.home.home.domain.models.Movie
import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.home.presentation.HomeState
import com.app.features.home.home.presentation.adapter.GridAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class UpcomingFragment : Fragment() {
    private lateinit var binding: FragmentGridRecyclerLayoutBinding
    private val viewModel: UpcomingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGridRecyclerLayoutBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionObserver()
    }

    private fun actionObserver() = lifecycleScope.launch {
        viewModel.upcomingMoviesState.collect { state ->
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
//        binding.textView.text = error
    }

    private fun renderSuccess(list: PopularMovies) {
        binding.recycler.adapter = GridAdapter( list, requireContext(), ::onClick)
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 3)

    }
    private fun onClick(movie: Movie) {
    }
    private fun renderLoading(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }
}