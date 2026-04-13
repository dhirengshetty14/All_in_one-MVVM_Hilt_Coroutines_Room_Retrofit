package com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.network

import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.local.User
import retrofit2.http.GET

interface ApiService{

    @GET("users")
    suspend fun getUsers(): List<User>
}