package com.app.features.home.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }

    private fun setButton() {
        binding.textView.setOnClickListener {
            viewModel.getMovies()
        }
    }
}