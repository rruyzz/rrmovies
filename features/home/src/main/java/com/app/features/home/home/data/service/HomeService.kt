package com.app.features.home.home.data.service

import com.app.features.home.home.data.model.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeService {

    @GET("movie/popular")
    suspend fun getPopularMovies() : Response<PopularMoviesResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies() : Response<PopularMoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies() : Response<PopularMoviesResponse>

}