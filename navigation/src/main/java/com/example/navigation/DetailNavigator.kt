package com.example.navigation

import android.content.Context
import com.app.commons.models.Movie

interface DetailNavigator {
    fun navigate(context: Context, movie: Movie)
}