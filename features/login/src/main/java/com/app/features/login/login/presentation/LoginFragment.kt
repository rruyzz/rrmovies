package com.app.features.login.login.presentation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.app.features.login.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {


    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

}

//https://github1s.com/arrazyfathan/Lerun/blob/78ff30e862cc167e0fa5055353d099d7f0028802/app/src/main/res/layout/fragment_run.xml#L16
//https://medium.com/android-news/the-blurry-frosted-background-is-a-common-pattern-on-ios-where-they-have-simply-controls-to-cbd0c5843e5f#.z7yusl3ix