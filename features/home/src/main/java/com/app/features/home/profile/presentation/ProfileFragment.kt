package com.app.features.home.profile.presentation

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.app.features.home.databinding.FragmentProfileBinding
import com.app.features.home.home.presentation.fragment.HomeState
import com.bumptech.glide.Glide
import com.example.navigation.LoginNavigator
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModel<ProfileViewModel>()
    private val loginNavigator: LoginNavigator by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stateObserver()
        setButton()
    }
    private fun stateObserver() = lifecycleScope.launch {
        viewModel.profileState.collect { state ->
            when (state) {
                is ProfileState.Success -> {
                    renderSuccess(state.profileUri)
                }
                is ProfileState.FinishAffinity -> {
                    requireActivity().finishAffinity()
                    loginNavigator.navigate(requireContext())
                }
            }
        }
    }

    private fun setButton() = with(binding) {
        signOut.setOnClickListener {
            viewModel.signOut()
        }
    }
    private fun renderSuccess(profile: FirebaseUser?) {
        Glide.with(requireActivity()).load(profile?.photoUrl).into(binding.profileImageView)
        binding.progress.isVisible = false
        binding.textNameInput.text = profile?.displayName
        binding.textEmailInput.text = profile?.email
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}