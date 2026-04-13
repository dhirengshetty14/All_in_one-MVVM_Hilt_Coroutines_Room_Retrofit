package com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.repository

import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.local.UserDao
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.local.UserEntity
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.network.ApiService
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ApiService,
    private val dao: UserDao
): IRepository {
    override suspend fun getUsers(): List<UserEntity> {
        val local = dao.getUsers()

        return if (local.isNotEmpty()) {
            local
        } else {
            val remote = api.getUsers()
            val mapped = remote.map {
                UserEntity(it.id, it.name, it.email)
            }

            dao.insertUsers(mapped)
            mapped
        }
    }

}