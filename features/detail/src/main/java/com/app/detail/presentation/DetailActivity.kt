package com.app.detail.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.commons.models.Movie
import com.app.commons.utils.hideStatusBar
import com.app.commons.utils.parcelable
import com.app.detail.R
import com.app.detail.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideStatusBar(window)
        val movie = intent?.parcelable<Movie>("movie")
        Glide.with(this).load("https://image.tmdb.org/t/p/w500${movie?.posterBack}")
            .into(binding.imagePosterBackgroundCard)
        Glide.with(this).load("https://image.tmdb.org/t/p/w500${movie?.poster}")
            .into(binding.imagePoster)
        binding.imagePosterBackgroundCard.setBackgroundResource(R.drawable.background_poster)
        binding.toolbar.setOnClickListener {
            finish()
        }
    }
}