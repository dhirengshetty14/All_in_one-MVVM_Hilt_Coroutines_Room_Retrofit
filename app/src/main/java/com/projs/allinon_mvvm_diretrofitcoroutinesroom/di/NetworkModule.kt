package com.projs.allinon_mvvm_diretrofitcoroutinesroom.di

import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesApiClient(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesapiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }
}