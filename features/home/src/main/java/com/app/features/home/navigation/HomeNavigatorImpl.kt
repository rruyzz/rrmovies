package com.app.features.home.navigation

import android.content.Context
import android.content.Intent
import com.app.features.home.presentation.HomeActivity
import com.example.navigation.HomeNavigator

class HomeNavigatorImpl : HomeNavigator {
    override fun navigate(context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)
    }
}