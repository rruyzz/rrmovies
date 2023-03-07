package com.app.features.home.popularMovies.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.app.features.home.databinding.FragmentGridRecyclerLayoutBinding
import com.app.features.home.home.domain.models.Movie
import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.home.presentation.adapter.GridAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularMoviesFragment : Fragment() {
    private lateinit var binding: FragmentGridRecyclerLayoutBinding
    private val viewModel: PopularMoviesViewModel by viewModel()

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
        viewModel.popularMoviesState.collect { state ->
            when (state) {
                is PopularMoviesState.Loading -> {
                    renderLoading(state.isLoading)
                }
                is PopularMoviesState.Success -> {
                    renderSuccess(state.popularMovies)
                }
                is PopularMoviesState.Error -> {
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