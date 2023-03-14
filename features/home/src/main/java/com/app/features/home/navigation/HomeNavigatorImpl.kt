package com.app.features.home.navigation

import android.content.Context
import android.content.Intent
import com.app.features.home.home.presentation.activity.MainActivity
import com.example.navigation.HomeNavigator

class HomeNavigatorImpl : HomeNavigator {
    override fun navigate(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }
}