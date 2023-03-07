package com.app.rrmovies

import android.app.Application
import com.app.features.home.home.data.mapper.MoviesMapper
import com.app.features.home.home.data.mapper.PopularMoviesMapper
import com.app.features.home.home.domain.repository.HomeRepository
import com.app.features.home.home.data.repository.HomeRepositoryImpl
import com.app.features.home.home.data.service.HomeService
import com.app.features.home.home.domain.usecase.HomeUseCase
import com.app.features.home.navigation.HomeNavigatorImpl
import com.app.features.home.home.presentation.HomeViewModel
import com.app.features.home.nowPlaying.domain.useCase.NowPlayingUseCase
import com.app.features.home.nowPlaying.presentation.NowPlayingViewModel
import com.app.features.home.popularMovies.domain.PopularMoviesUseCase
import com.app.features.home.popularMovies.presentation.PopularMoviesState
import com.app.features.home.popularMovies.presentation.PopularMoviesViewModel
import com.app.features.home.topRated.domain.TopRatedUseCase
import com.app.features.home.topRated.presentation.TopRatedViewModel
import com.app.features.home.upcoming.domain.UpcomingUseCase
import com.app.features.home.upcoming.presentation.UpcomingViewModel
import com.app.features.login.data.google.LoginGoogle
import com.app.features.login.data.repository.LoginRepository
import com.app.features.login.domain.LoginUseCase
import com.app.features.login.navigation.LoginNavigatorImpl
import com.app.features.login.presentation.LoginViewModel
import com.app.network.utils.createHttpClient
import com.app.network.utils.retrofitClient
import com.example.navigation.HomeNavigator
import com.example.navigation.LoginNavigator
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(loginModule, navigationModule, repositoryModule, retrofitModule, apiModule))
        }
    }
    private val navigationModule = module {
        single<LoginNavigator> { LoginNavigatorImpl() }
        single<HomeNavigator> { HomeNavigatorImpl() }
    }
    private val repositoryModule = module {
        single<LoginRepository> { LoginGoogle() }
        single<HomeRepository> { HomeRepositoryImpl(get(), PopularMoviesMapper(), MoviesMapper()) }
    }
    private val loginModule = module {
        viewModel { LoginViewModel(loginUseCase = LoginUseCase(repository = get())) }
        viewModel { HomeViewModel(popularMoviesUseCase = HomeUseCase(repository = get())) }
        viewModel { NowPlayingViewModel(nowPlayingUseCase = NowPlayingUseCase(repository = get())) }
        viewModel { UpcomingViewModel(upcomingUseCase = UpcomingUseCase(repository = get())) }
        viewModel { TopRatedViewModel(topRatedUseCase = TopRatedUseCase(repository = get())) }
        viewModel { PopularMoviesViewModel(popularMoviesUseCase = PopularMoviesUseCase(repository = get())) }
    }
    private val retrofitModule = module{
        single { createHttpClient() }
        single { retrofitClient(get())}
    }
    private val apiModule = module {
        single(createdAtStart = false) { get<Retrofit>().create(HomeService::class.java) }
    }

}