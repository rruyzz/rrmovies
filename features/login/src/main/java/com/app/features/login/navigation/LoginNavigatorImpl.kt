package com.app.features.login.navigation

import android.content.Context
import android.content.Intent
import com.app.features.login.splash.presentation.SplashActivity
import com.example.navigation.LoginNavigator

class LoginNavigatorImpl: LoginNavigator {

    override fun navigate(context: Context) {
        val intent = Intent(context, SplashActivity::class.java)
        context.startActivity(intent)
    }

}