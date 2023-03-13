package com.app.detail.main.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.commons.models.Movie
import com.app.commons.utils.hideStatusBar
import com.app.commons.utils.parcelable
import com.app.detail.R
import com.app.detail.cast.presentation.CastFragment
import com.app.detail.databinding.ActivityDetailBinding
import com.app.detail.description.presentation.DescriptionFragment
import com.app.detail.reviews.presentation.ReviewsFragment
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator

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
        binding.titleMovie.text = movie?.title.orEmpty()
        binding.imagePosterBackgroundCard.setBackgroundResource(R.drawable.background_poster)
        binding.toolbar.setOnClickListener {
            finish()
        }
        binding.textYear.text = movie?.year.orEmpty()
        binding.textGener.text = movie?.gener.orEmpty()
        setAdapter(movie?.description.orEmpty(), movie?.id.orEmpty())
        setTab()
    }

    private fun setAdapter(description: String, movieId: String) {
        val adapter = DetailMovieAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(DescriptionFragment(description))
        adapter.addFragment(CastFragment(movieId))
        binding.viewPager.adapter = adapter

    }
    private fun setTab() = with(binding) {
        val tabList = listOf(
            "About Movie",
            "Cast",
        )
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabList[position]
        }.attach()
    }
}