package com.app.features.home.nowPlaying.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.app.commons.models.Movie
import com.app.features.home.databinding.FragmentGridRecyclerLayoutBinding
import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.home.presentation.adapter.GridAdapter
import com.example.navigation.DetailNavigator
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class NowPlayingFragment : Fragment() {

    private lateinit var binding: FragmentGridRecyclerLayoutBinding
    private val viewModel: NowPlayingViewModel by viewModel()
    private val detailNavigator: DetailNavigator by inject()

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
        viewModel.nowPlayingMoviesState.collect { state ->
            when (state) {
                is NowPlayingState.Loading -> {
                    renderLoading(state.isLoading)
                }
                is NowPlayingState.Success -> {
                    renderSuccess(state.nowPlayingMovies)
                }
                is NowPlayingState.Error -> {
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
        detailNavigator.navigate(requireContext(), movie)
    }
    private fun renderLoading(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }
}