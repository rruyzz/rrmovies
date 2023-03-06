package com.app.features.home.upcoming.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.features.home.R
import com.app.features.home.databinding.FragmentNowPlayingBinding
import com.app.features.home.databinding.FragmentUpcomingBinding

class UpcomingFragment : Fragment() {
    private lateinit var binding: FragmentUpcomingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpcomingBinding.inflate(inflater, container,false)
        return binding.root
    }
}