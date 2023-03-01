package com.app.features.login.presentation

sealed class LoginAction {
    object Navigate : LoginAction()
}