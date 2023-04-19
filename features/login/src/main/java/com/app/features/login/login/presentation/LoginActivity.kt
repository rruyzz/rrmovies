package com.app.features.login.login.presentation

import android.content.IntentSender
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        hideStatusBar(window)
        setContentView(binding.root)
        observer()
        setButton()
        binding.icSplash.setRenderEffect(
            RenderEffect.createBlurEffect(40f, 40f, Shader.TileMode.MIRROR)
        )
//        setBlur()
    }

    private fun setButton() = with(binding) {
//        button.setOnClickListener {
//            viewModel.signInGoogle()
//        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setBlur() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            binding.icSplash.setRenderEffect(
                RenderEffect.createBlurEffect(40f, 40f, Shader.TileMode.REPEAT)
            )
        } else {
            binding.icSplash.isVisible = false
        }
    }

    private fun observer() = lifecycleScope.launch {
        viewModel.loginState.collect {
            when (it) {
                is LoginAction.NavigateLogin -> homeNavigator.navigate(this@LoginActivity)
                is LoginAction.Error -> showToastError(it.message)
                is LoginAction.Loading -> {}
                is LoginAction.Tap -> handleTap(it.tap)
                is LoginAction.NavigateCreateAccount -> {}
                is LoginAction.NavigatePassword -> {}
            }
        }
    }

    private fun showToastError(error: String) {
        Toast.makeText(
            this@LoginActivity,
            error,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun handleTap(intentSender: IntentSender) {
        oneTapResult.launch(IntentSenderRequest.Builder(intentSender).build())
    }
}