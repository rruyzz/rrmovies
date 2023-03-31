package com.app.features.home.watchList.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.app.commons.models.Movie
import com.app.features.home.databinding.FragmentWatchListBinding
import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.home.presentation.adapter.GridAdapter
import com.example.navigation.DetailNavigator
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class WatchListFragment : Fragment() {

    private lateinit var binding: FragmentWatchListBinding
    private val detailNavigator: DetailNavigator by inject()
    private val viewModel: WatchListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWatchListBinding.inflate(inflater, container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionObserver()
    }

    private fun actionObserver() = lifecycleScope.launch {
        viewModel.watchListMoviesState.collect { state ->
            when (state) {
                is WatchListState.Success -> {
                    renderSuccess(PopularMovies(state.movies))
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

    companion object {
        fun newInstance() = WatchListFragment()
    }
}