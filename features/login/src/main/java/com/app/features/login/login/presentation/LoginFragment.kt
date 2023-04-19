package com.app.features.login.login.presentation

import android.content.IntentSender
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.app.features.login.databinding.FragmentLoginBinding
import com.app.features.login.utils.safeNavigate
import com.example.navigation.HomeNavigator
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: FragmentLoginBinding
    private val homeNavigator: HomeNavigator by inject()
    private val oneTapResult =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            try {
                viewModel.authenticate(result.data)
            } catch (e: ApiException) {
                viewModel.catchResultException(e)
            }
        }
    private val navigation get() = findNavController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.cardView.setRenderEffect(
//            RenderEffect.createBlurEffect(40f, 40f, Shader.TileMode.CLAMP)
//        )
        setButton()
        observer()
    }

    private fun observer() = lifecycleScope.launch {
        viewModel.loginState.collect {
            when (it) {
                is LoginAction.NavigateLogin -> navigateHome()
                is LoginAction.Error -> showToastError(it.message)
                is LoginAction.Loading -> renderLoading(it.isLoading)
                is LoginAction.Tap -> handleTap(it.tap)
                is LoginAction.NavigateCreateAccount -> navigateCreateAccount()
                is LoginAction.NavigatePassword -> navigateSignIn()
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

    private fun setButton() = with(binding) {
        buttonGoogle.setOnClickListener {
//            homeNavigator.navigate(requireContext())
            viewModel.signInGoogle()
        }
        signUp.setOnClickListener {
            navigation.safeNavigate(
                LoginFragmentDirections.actionLoginFragmentToSignUpFragment(
                    binding.textInputEmail.text.toString()
                )
            )
        }
        buttonContinue.setOnClickListener {
            viewModel.signUp(binding.textInputEmail.text.toString())
//            navigation.navigate(LoginFragmentDirections.actionLoginFragmentToSignInFragment())
        }
    }

    private fun navigateCreateAccount() {
        navigation.safeNavigate(
            LoginFragmentDirections.actionLoginFragmentToSignInFragment(
                binding.textInputEmail.text.toString()
            )
        )
    }

    private fun navigateSignIn() {
        navigation.safeNavigate(
            LoginFragmentDirections.actionLoginFragmentToSignUpFragment(
                binding.textInputEmail.text.toString()
            )
        )
    }

    private fun showToastError(error: String) {
        Toast.makeText(
            requireContext(),
            error,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun handleTap(intentSender: IntentSender) {
        oneTapResult.launch(IntentSenderRequest.Builder(intentSender).build())
    }
}

//https://github1s.com/arrazyfathan/Lerun/blob/78ff30e862cc167e0fa5055353d099d7f0028802/app/src/main/res/layout/fragment_run.xml#L16
//https://medium.com/android-news/the-blurry-frosted-background-is-a-common-pattern-on-ios-where-they-have-simply-controls-to-cbd0c5843e5f#.z7yusl3ix