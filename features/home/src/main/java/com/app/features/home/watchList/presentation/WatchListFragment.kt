package com.app.features.home.watchList.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.features.home.R
import com.app.features.home.databinding.FragmentHomeBinding
import com.app.features.home.databinding.FragmentWatchListBinding

class WatchListFragment : Fragment() {

    private lateinit var binding: FragmentWatchListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWatchListBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = WatchListFragment()
    }
}