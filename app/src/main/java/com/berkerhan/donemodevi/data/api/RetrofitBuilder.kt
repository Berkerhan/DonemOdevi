package com.berkerhan.donemodevi.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object {
        private const val BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/"
        private val retrofit by lazy {

            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                        .method(original.method, original.body)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json")
                        .build()

                    chain.proceed(request)
                }
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(ApiService::class.java)
        }
    }
}