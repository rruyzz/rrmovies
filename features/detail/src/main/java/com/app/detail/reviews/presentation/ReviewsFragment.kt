package com.app.detail.reviews.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.app.detail.databinding.FragmentReviewsBinding


class ReviewsFragment : Fragment() {

    private lateinit var binding: FragmentReviewsBinding
    private val viewModel by viewModel<ReviewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewsBinding.inflate(inflater, container,false)
        return binding.root
    }
}