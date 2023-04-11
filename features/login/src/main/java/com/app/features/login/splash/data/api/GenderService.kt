package com.app.features.login.splash.data.api

import com.app.commons.models.GenderResponse
import retrofit2.Response
import retrofit2.http.GET

interface GenderService {

    @GET("genre/movie/list")
    suspend fun getGender() : Response<GenderResponse>
}