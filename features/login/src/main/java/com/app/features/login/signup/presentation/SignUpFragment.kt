package com.app.features.login.signup.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.app.features.login.databinding.FragmentSignUpBinding
import com.example.navigation.HomeNavigator
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val args by navArgs<SignUpFragmentArgs>()
    private val email get() = args.email
    private val shouldEnable get() = args.shouldEnable
    private val viewModel: SignUpViewModel by viewModel()
    private val homeNavigator: HomeNavigator by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButton()
        observer()
    }

    private fun setButton() = with(binding) {
        textInputEmail.setText(email)
        textFieldEmail.isEnabled = shouldEnable
        buttonContinue.setOnClickListener {
            viewModel.signUp(textInputEmail.text.toString(), textInputPassword.text.toString())
        }
    }

    private fun observer() = lifecycleScope.launch {
        viewModel.signUpState.collect {
            when (it) {
                is SignUpState.Success -> navigateHome()
                is SignUpState.Error -> showToastError(it.error)
                is SignUpState.Loading -> renderLoading(it.isLoading)
            }
        }
    }
    private fun navigateHome() {
        requireActivity().finishAffinity()
        homeNavigator.navigate(requireContext())
    }

    private fun renderLoading(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }

    private fun showToastError(error: String) {
        Toast.makeText(
            requireContext(),
            error,
            Toast.LENGTH_SHORT
        ).show()
    }
}