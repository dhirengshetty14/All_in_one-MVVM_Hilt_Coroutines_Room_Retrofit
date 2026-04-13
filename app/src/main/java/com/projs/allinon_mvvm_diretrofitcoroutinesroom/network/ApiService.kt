package com.projs.allinon_mvvm_diretrofitcoroutinesroom.network

import retrofit2.http.GET

interface ApiService{

    @GET("users")
    suspend fun getUsers(): List<User>
}