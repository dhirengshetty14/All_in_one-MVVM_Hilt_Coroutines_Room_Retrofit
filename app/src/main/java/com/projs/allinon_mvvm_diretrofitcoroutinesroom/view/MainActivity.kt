package com.projs.allinon_mvvm_diretrofitcoroutinesroom.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.R
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: UserViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnLoadUsers: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupViews()
        setupRecyclerView()
        observeData()

        // Initially load users (optional - can be removed if you only want to load on button click)
        // viewModel.loadUsers()
    }

    private fun setupViews() {
        btnLoadUsers = findViewById(R.id.btnLoadUsers)
        recyclerView = findViewById(R.id.recyclerViewUsers)

        btnLoadUsers.setOnClickListener {
            viewModel.loadUsers()
        }
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
    }

    private fun observeData() {
        viewModel.users.observe(this) { users ->
            Log.d("MainActivity", "Users: $users")
            users?.let {
                userAdapter.submitList(it)
            }
        }
    }
}