package com.berkerhan.donemodevi.data.api

import com.berkerhan.donemodevi.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<MutableList<User>>
}