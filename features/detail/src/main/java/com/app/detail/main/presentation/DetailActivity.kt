package com.app.detail.main.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.app.commons.models.Movie
import com.app.commons.utils.hideStatusBar
import com.app.commons.utils.parcelable
import com.app.detail.R
import com.app.detail.cast.presentation.CastFragment
import com.app.detail.databinding.ActivityDetailBinding
import com.app.detail.description.presentation.DescriptionFragment
import com.app.detail.main.domain.domain.UpdateMovie
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModel<DetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideStatusBar(window)
        viewModel.setMovie(intent?.parcelable<Movie>("movie"))
        setViews(viewModel.movie)
        setAdapter(viewModel.movie?.description.orEmpty(), viewModel.movie?.id.toString())
        setTab()
        observer()
    }

    private fun observer() = lifecycleScope.launch {
        viewModel.detailState.collect {
            when(it){
                is DetailState.HasSavedMovie -> setIconSaved(it.hasSaved)
            }
        }
    }
    private fun setIconSaved(hasSaved: Boolean) {
        binding.toolbar.hasSaved(hasSaved, true)
    }

    private fun setViews(movie: Movie?) = with(binding) {
        Glide.with(this@DetailActivity).load("https://image.tmdb.org/t/p/w500${movie?.posterBack}")
            .into(imagePosterBackgroundCard)
        Glide.with(this@DetailActivity).load("https://image.tmdb.org/t/p/w500${movie?.poster}")
            .into(imagePoster)
        titleMovie.text = movie?.title.orEmpty()
        imagePosterBackgroundCard.setBackgroundResource(R.drawable.background_poster)
        textYear.text = movie?.year.orEmpty()
        textGener.text = movie?.gender
        grade.text = movie?.grade
        toolbar.setOnIconListener {  hasSaved ->
            if(hasSaved) viewModel.onClick(UpdateMovie.SaveMovie)
            else viewModel.onClick(UpdateMovie.DeleteMovie)
        }
        toolbar.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun setAdapter(description: String, movieId: String) = with(binding){
        val adapter = DetailMovieAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(DescriptionFragment(description))
        adapter.addFragment(CastFragment(movieId))
        viewPager.adapter = adapter


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