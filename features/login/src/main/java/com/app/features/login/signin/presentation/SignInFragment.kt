package com.app.features.login.signin.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.app.features.login.R
import com.app.features.login.databinding.FragmentLoginBinding
import com.app.features.login.databinding.FragmentSignInBinding
import com.app.features.login.login.presentation.LoginViewModel
import com.app.features.login.signup.presentation.SignUpFragmentArgs
import com.app.features.login.signup.presentation.SignUpState
import com.example.navigation.HomeNavigator
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val args by navArgs<SignInFragmentArgs>()
    private val email get() = args.email
    private val viewModel: SignInViewModel by viewModel()
    private val homeNavigator: HomeNavigator by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButton()
        observer()
    }
    private fun setButton() = with(binding) {
        buttonContinue.setOnClickListener {
            viewModel.signUp(email, textInputPassword.text.toString())
        }
    }
    private fun observer() = lifecycleScope.launch {
        viewModel.signInState.collect {
            when (it) {
                is SignInState.Success -> navigateHome()
                is SignInState.Error -> showToastError(it.error)
                is SignInState.Loading -> renderLoading(it.isLoading)
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