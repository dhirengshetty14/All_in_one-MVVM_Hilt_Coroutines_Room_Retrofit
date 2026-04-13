package com.projs.allinon_mvvm_diretrofitcoroutinesroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.local.User
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: IRepository
): ViewModel() {
    
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users
    
    fun loadUsers(){
        viewModelScope.launch(Dispatchers.IO) { 
            try {
                val data: List<User> = repo.getUsers().map {
                    User(id = it.id, name = it.name, email = it.email)
                }
                _users.postValue(data)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
    
}