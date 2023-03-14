package com.app.features.home.search.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.features.home.R
import com.app.features.home.databinding.FragmentHomeBinding
import com.app.features.home.databinding.FragmentSearchBinding
import com.app.features.home.home.presentation.fragment.HomeFragment


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}