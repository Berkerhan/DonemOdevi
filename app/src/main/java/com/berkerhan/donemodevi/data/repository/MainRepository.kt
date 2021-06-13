package com.berkerhan.donemodevi.data.repository

import com.berkerhan.donemodevi.data.api.RetrofitBuilder

class MainRepository {
    suspend fun getUsers()=RetrofitBuilder.api.getUsers()
}