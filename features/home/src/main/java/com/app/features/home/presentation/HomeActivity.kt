package com.app.features.home.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.features.home.R
import com.app.features.home.databinding.ActivityHomeBinding
import com.example.navigation.HomeNavigator
import com.example.navigation.LoginNavigator
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val loginNavigator: LoginNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setButton()
    }

    private fun setButton() {
        binding.textView.setOnClickListener {
            loginNavigator.navigate(this)
        }
    }
}