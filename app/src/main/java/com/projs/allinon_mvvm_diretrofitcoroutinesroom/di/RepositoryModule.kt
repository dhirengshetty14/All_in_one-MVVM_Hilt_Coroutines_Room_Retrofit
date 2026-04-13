package com.projs.allinon_mvvm_diretrofitcoroutinesroom.di

import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.repository.IRepository
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
abstract class RepositoryModule {

@Binds
abstract fun bindRepo(
    repo: Repository
): IRepository
}