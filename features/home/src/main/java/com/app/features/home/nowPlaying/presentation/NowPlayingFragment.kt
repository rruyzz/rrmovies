package com.app.features.home.nowPlaying.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.features.home.databinding.FragmentNowPlayingBinding


class NowPlayingFragment : Fragment() {

    private lateinit var binding: FragmentNowPlayingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNowPlayingBinding.inflate(inflater, container,false)
        return binding.root
    }
}