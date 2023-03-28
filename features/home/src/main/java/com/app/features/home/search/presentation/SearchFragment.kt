package com.app.features.home.search.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.commons.models.Movie
import com.app.commons.utils.hideKeyboard
import com.app.features.home.databinding.FragmentSearchBinding
import com.app.features.home.home.domain.models.PopularMovies
import com.app.features.home.home.presentation.activity.MainActivity
import com.app.features.home.home.presentation.adapter.HomeAdapter
import com.app.features.home.home.presentation.fragment.HomeState
import com.example.navigation.DetailNavigator
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModel()
    private val detailNavigator: DetailNavigator by inject()

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
        setButton()
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }
    private fun setButton() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchMovies(query)
                return false
            }
        })
    }

    private fun actionObserver() = lifecycleScope.launch {
        viewModel.searchMoviesState.collect { state ->
            when (state) {
                is SearchState.Loading -> {
                    renderLoading(state.isLoading)
                }
                is SearchState.Success -> {
                    renderSuccess(state.popularMovies)
                }
                is SearchState.Error -> {
                    renderError(state.error)
                }
            }
        }
    }

    private fun renderError(error: String) {
        binding.textView.text = error
        binding.iconLayout.isVisible = true
        binding.moviesList.adapter = SearchAdapter(::onClick, PopularMovies(emptyList()), requireContext())
    }

    private fun renderSuccess(list: PopularMovies) {
        binding.moviesList.isVisible = true
        binding.iconLayout.isVisible = list.movies.isEmpty()
        binding.moviesList.adapter = SearchAdapter(::onClick, list, requireContext())
        binding.moviesList.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    private fun renderLoading(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
        binding.moviesList.isVisible = isLoading.not()
        if(isLoading){
            binding.iconLayout.isVisible = false
        }
    }

    private fun observesFlows() = lifecycleScope.launch {
        viewModel.query.collect {
            binding.search.setQuery(it, true)
        }
    }

    private fun onClick(movie: Movie) {
        detailNavigator.navigate(requireContext(), movie)
    }

    companion object {
        fun newInstance(searchText: String? = null) = SearchFragment().apply {
            arguments = bundleOf(Pair("text", searchText))
        }
    }
}