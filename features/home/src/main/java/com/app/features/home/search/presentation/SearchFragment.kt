package com.app.features.home.search.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.commons.models.Movie
import com.app.features.home.databinding.FragmentSearchBinding
import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.home.presentation.adapter.HomeAdapter
import com.app.features.home.home.presentation.fragment.HomeState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getQuery(arguments)
        observesFlows()
        actionObserver()
    }

    private fun actionObserver() = lifecycleScope.launch {
        viewModel.searchMoviesState.collect { state ->
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
        binding.moviesList.adapter = SearchAdapter(::onClick, list, requireContext())
        binding.moviesList.layoutManager = GridLayoutManager(requireContext(), 3)
//        binding.moviesList.layoutManager =
//            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.iconLayout.isVisible = list.movies.isNotEmpty()
    }

    private fun renderLoading(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
        binding.iconLayout.visibility = View.GONE
    }

    private fun observesFlows() = lifecycleScope.launch {
        viewModel.query.collect {
            binding.search.setQuery(it, true)
        }
    }

    private fun onClick(movie: Movie) {
//        detailNavigator.navigate(requireContext(), movie)
    }

    private fun getQuery(bundle: Bundle?) {
        val text = bundle?.getString("text")
        text?.let {
            binding.search.setQuery(text, true)
        }
    }

    companion object {
        fun newInstance(searchText: String? = null) = SearchFragment().apply {
            arguments = bundleOf(Pair("text", searchText))
        }
    }
}