package com.app.features.home.data.service

import com.app.features.home.data.model.MovieResultResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeService {

    @GET("movie/popular")
    suspend fun getPopularMovies() : Response<MovieResultResponse>
}