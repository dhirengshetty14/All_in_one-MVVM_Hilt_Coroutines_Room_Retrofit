package com.projs.allinon_mvvm_diretrofitcoroutinesroom.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.R
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        observeData()

        viewModel.loadUsers()


    }

    private fun observeData() {
        viewModel.users.observe(this){
            users ->
            Log.d("MainActivity", "Users: $users ")
        }
    }
}