package com.app.features.home.home.data.service

import com.app.features.home.home.data.model.PopularMoviesResponse
import com.app.features.home.search.data.model.SearchMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("movie/popular")
    suspend fun getPopularMovies() : Response<PopularMoviesResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies() : Response<PopularMoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies() : Response<PopularMoviesResponse>
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies() : Response<PopularMoviesResponse>

    @GET("search/multi")
    suspend fun getSearchMovies(@Query("query") movieName: String) : Response<SearchMoviesResponse>


}