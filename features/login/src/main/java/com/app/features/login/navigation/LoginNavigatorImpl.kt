package com.app.features.login.navigation

import android.content.Context
import android.content.Intent
import com.app.features.login.presentation.LoginActivity
import com.example.navigation.LoginNavigator

class LoginNavigatorImpl: LoginNavigator {

    override fun navigate(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }

}