package com.app.features.login.presentation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.features.login.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()
    private val collectingScope = CoroutineScope(Dispatchers.Default)

    // ...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideStatusBar()
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
        buttonStart.setOnClickListener {
            viewModel.onButtonClick()
        }
    }

    private fun navigateMainActivity() {
        startActivity(Intent(this@LoginActivity, OtherActivity::class.java))
    }

    private fun hideStatusBar() {
        with(window) {
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            attributes.flags =
                window.attributes.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
            statusBarColor = Color.TRANSPARENT
        }
    }
}

//https://proandroiddev.com/supercharge-android-mvvm-part-1-viewstate-and-actionstate-5816500580ed