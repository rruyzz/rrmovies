package com.app.features.login.signup.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.app.features.login.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {


    private lateinit var binding: FragmentSignUpBinding
    private val args by navArgs<SignUpFragmentArgs>()
    private val email get() = args.email
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textInputEmail.setText(email)
    }
}