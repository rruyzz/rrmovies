package com.app.network.utils

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val CONNECT_TIMEOUT = 15L
const val WRITE_TIMEOUT = 15L
const val READ_TIMEOUT = 15L

var clientInterceptor = Interceptor { chain ->
    val request = chain.request()
    val url = request.url.newBuilder()
        .addQueryParameter("api_key", "e591023d8d396231d3045ea6341a6fd2")
        .addQueryParameter("language", "pt-BR").build()
//    request = request.newBuilder().url(url).build()
    chain.proceed(request)
}

fun retrofitClient(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .client(client)
    .baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun createHttpClient(): OkHttpClient {
    val loggin = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().apply{
        addInterceptor(loggin)
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        addNetworkInterceptor(clientInterceptor)
        readTimeout(5 * 60, TimeUnit.SECONDS)
    }
    return client.build()
}