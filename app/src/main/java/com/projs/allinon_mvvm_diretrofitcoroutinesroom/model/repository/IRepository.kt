package com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.repository

import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.local.UserEntity

interface IRepository {
    suspend fun getUsers(): List<UserEntity>
}

