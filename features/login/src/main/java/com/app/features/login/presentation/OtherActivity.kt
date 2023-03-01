package com.app.features.login.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.features.login.databinding.ActivityOtherBinding

class OtherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtherBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}