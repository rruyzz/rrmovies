package com.app.detail.main.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
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
        setViews(viewModel._movie)
        setAdapter(viewModel._movie?.description.orEmpty(), viewModel._movie?.id.toString())
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
        val iconResource = if(hasSaved) R.drawable.ic_saved else R.drawable.ic_not_saved
        val icon = ContextCompat.getDrawable(this, iconResource)
        binding.toolbar.setIcon(icon)
    }

    private fun setViews(movie: Movie?) {
        Glide.with(this).load("https://image.tmdb.org/t/p/w500${movie?.posterBack}")
            .into(binding.imagePosterBackgroundCard)
        Glide.with(this).load("https://image.tmdb.org/t/p/w500${movie?.poster}")
            .into(binding.imagePoster)
        binding.titleMovie.text = movie?.title.orEmpty()
        binding.imagePosterBackgroundCard.setBackgroundResource(R.drawable.background_poster)
        binding.textYear.text = movie?.year.orEmpty()
        binding.textGener.text = movie?.gener
        binding.grade.text = movie?.grade
        binding.toolbar.setOnIconListener {  hasSaved ->
            if(hasSaved) viewModel.onClick(UpdateMovie.SaveMovie)
            else viewModel.onClick(UpdateMovie.DeleteMovie)
        }
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