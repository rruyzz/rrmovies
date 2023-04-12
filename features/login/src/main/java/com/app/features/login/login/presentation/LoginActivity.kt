package com.app.features.login.login.presentation

import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.app.commons.utils.hideStatusBar
import com.app.features.login.databinding.ActivityLoginBinding
import com.example.navigation.HomeNavigator
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()
    private val homeNavigator: HomeNavigator by inject()
    private val oneTapResult =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            try {
                viewModel.authenticate(result.data)
            } catch (e: ApiException) {
                viewModel.catchResultException(e)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        hideStatusBar(window)
        setContentView(binding.root)
        observer()
        binding.button.setOnClickListener {
            viewModel.signInGoogle()
        }
    }

    private fun observer() = lifecycleScope.launch {
        viewModel.loginState.collect {
            when (it) {
                is LoginAction.NavigateLogin -> homeNavigator.navigate(this@LoginActivity)
                is LoginAction.Error -> Toast.makeText(
                    this@LoginActivity,
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
                is LoginAction.Loading -> binding.progress.isVisible = it.isLoading
                is LoginAction.Tap -> handleTap(it.tap)
            }
        }
    }

    private fun handleTap(intentSender: IntentSender?) {
        intentSender?.let {
            oneTapResult.launch(IntentSenderRequest.Builder(it).build())
        }
    }
}