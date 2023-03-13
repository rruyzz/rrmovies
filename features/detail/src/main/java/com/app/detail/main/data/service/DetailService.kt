package com.app.detail.main.data.service

import com.app.detail.cast.data.model.ActorResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {

    @GET("movie/{movie_id}/credits")
    suspend fun getCast(@Path("movie_id") movie_id: String) : Response<ActorResponse>
}