package com.app.features.login.presentation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.commons.utils.hideStatusBar
import com.app.features.login.databinding.ActivityLoginBinding
import com.example.navigation.HomeNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()
    private val collectingScope = CoroutineScope(Dispatchers.Default)
    private val homeNavigator: HomeNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideStatusBar(window)
        setButton()
        actionObserver()
    }

    private fun actionObserver() = collectingScope.launch {
        viewModel.action.collect{ action ->
            when(action) {
                is LoginAction.Navigate -> navigateMainActivity()
            }
        }
    }
    private fun setButton() = with(binding) {
        icSplash.setOnClickListener {
            viewModel.onButtonClick()
        }
    }

    private fun navigateMainActivity() {
        finishAffinity()
        homeNavigator.navigate(this)
    }

}

//https://proandroiddev.com/supercharge-android-mvvm-part-1-viewstate-and-actionstate-5816500580ed
//https://medium.com/mobile-app-development-publication/comparing-stateflow-sharedflow-and-callbackflow-2f0d03d48a43