package com.app.features.login.splash.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.commons.utils.hideStatusBar
import com.app.features.login.databinding.ActivitySplashBinding
import com.example.navigation.HomeNavigator
import com.example.navigation.LoginNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModel()
    private val collectingScope = CoroutineScope(Dispatchers.Default)
    private val homeNavigator: LoginNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideStatusBar(window)
        setButton()
        actionObserver()
    }

    private fun actionObserver() = collectingScope.launch {
        viewModel.action.collect{ action ->
            when(action) {
                is SplashAction.Navigate -> navigateMainActivity()
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
        homeNavigator.navigateLogin(this)
    }

}

//https://proandroiddev.com/supercharge-android-mvvm-part-1-viewstate-and-actionstate-5816500580ed
//https://medium.com/mobile-app-development-publication/comparing-stateflow-sharedflow-and-callbackflow-2f0d03d48a43