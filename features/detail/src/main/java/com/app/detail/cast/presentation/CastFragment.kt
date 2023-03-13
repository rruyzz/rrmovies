package com.app.detail.cast.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.app.detail.cast.domain.model.Actor
import com.app.detail.databinding.FragmentCastBinding
import kotlinx.coroutines.launch


class CastFragment(private val movieId: String) : Fragment() {

    private lateinit var binding: FragmentCastBinding

    private val viewModel by viewModel<CastViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCast(movieId)
        actionObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCastBinding.inflate(inflater, container,false)
        return binding.root
    }

    private fun actionObserver() = lifecycleScope.launch {
        viewModel.castMoviesState.collect { state ->
            when (state) {
                is CastState.Loading -> {
                    renderLoading(state.isLoading)
                }
                is CastState.Success -> {
                    renderSuccess(state.cast)
                }
                is CastState.Error -> {
                    renderError(state.error)
                }
            }
        }
    }

    private fun renderError(error: String) {
//        binding.textView.text = error
    }

    private fun renderSuccess(list: List<Actor>) {
        binding.recycler.adapter = CastAdapter(list, requireContext())
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)

    }
    private fun renderLoading(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }
}