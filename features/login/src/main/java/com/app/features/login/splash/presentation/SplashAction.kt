package com.app.features.login.splash.presentation

sealed class SplashAction {
    object NavigateHome : SplashAction()
    object NavigateLogin : SplashAction()
}