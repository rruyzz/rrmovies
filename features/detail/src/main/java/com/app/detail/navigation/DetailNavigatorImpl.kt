package com.app.detail.navigation

import android.content.Context
import android.content.Intent
import com.app.detail.presentation.DetailActivity
import com.example.navigation.DetailNavigator

class DetailNavigatorImpl : DetailNavigator {

    override fun navigate(context: Context) {
        val intent = Intent(context, DetailActivity::class.java)
        context.startActivity(intent)
    }
}