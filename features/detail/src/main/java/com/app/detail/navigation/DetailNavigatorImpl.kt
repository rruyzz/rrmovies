package com.app.detail.navigation

import android.content.Context
import android.content.Intent
import com.app.commons.models.Movie
import com.app.detail.main.presentation.DetailActivity
import com.example.navigation.DetailNavigator

class DetailNavigatorImpl : DetailNavigator {

    override fun navigate(context: Context, movie: Movie) {
        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra("movie", movie)
        }
        context.startActivity(intent)
    }
}