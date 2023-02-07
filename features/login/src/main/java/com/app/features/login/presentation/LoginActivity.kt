package com.app.features.login.presentation

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.app.features.login.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideStatusBar()
        setButton()
        observerViewModel()
    }

    private fun setButton() = with(binding) {
        buttonStart.setOnClickListener {
            viewModel.signIn()
        }
    }
    private fun observerViewModel() {
        lifecycleScope.launch {
            viewModel.loginResult.collect {
                Toast.makeText(this@LoginActivity, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun hideStatusBar() {
        with(window) {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            attributes.flags = window.attributes.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
            statusBarColor = Color.TRANSPARENT
        }
    }
}