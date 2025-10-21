package com.movieexplorer.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Cliente Retrofit para consumir a API OMDb
 */
object RetrofitClient {
    
    private const val BASE_URL = "https://www.omdbapi.com/"
    private const val API_KEY = "357576b4"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val authInterceptor = Interceptor { chain ->
        val original = chain.request()
        val httpUrl = original.url.newBuilder()
            .addQueryParameter("apikey", API_KEY)
            .build()

        val requestBuilder = original.newBuilder()
            .url(httpUrl)

        chain.proceed(requestBuilder.build())
    }
    
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .build()
    
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    val movieApi: MovieApi = retrofit.create(MovieApi::class.java)
}