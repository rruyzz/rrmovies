package com.app.features.home.presentation

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.app.features.home.databinding.ActivityHomeBinding
import com.example.navigation.LoginNavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val loginNavigator: LoginNavigator by inject()
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setButton()
        hideStatusBar()
    }

    private fun setButton() {
//        binding.textView.setOnClickListener {
//            viewModel.getMovies()
//        }
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