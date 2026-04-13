package com.projs.allinon_mvvm_diretrofitcoroutinesroom.di

import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.repository.IRepository
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepo(
        repo: Repository
    ): IRepository
}