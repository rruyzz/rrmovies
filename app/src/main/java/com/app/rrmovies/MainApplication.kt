package com.app.rrmovies

import android.app.Application
import com.app.features.login.data.google.LoginGoogle
import com.app.features.login.data.repository.LoginRepository
import com.app.features.login.domain.LoginUseCase
import com.app.features.login.presentation.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(loginModule, navigationModule))
        }
    }

    private val navigationModule = module {
        single<LoginRepository> { LoginGoogle() }
    }
    private val loginModule = module {
        viewModel { LoginViewModel(loginUseCase = LoginUseCase(repository = get())) }
    }
}