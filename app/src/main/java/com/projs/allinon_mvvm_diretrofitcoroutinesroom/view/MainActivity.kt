package com.projs.allinon_mvvm_diretrofitcoroutinesroom.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
            // Add creative button animation
            btnLoadUsers.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction {
                    btnLoadUsers.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100)
                        .start()
                }
                .start()

            // Show loading state
            btnLoadUsers.isEnabled = false
            btnLoadUsers.text = "🔄 Loading..."

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

            // Reset button state
            btnLoadUsers.isEnabled = true
            btnLoadUsers.text = getString(R.string.load_users)

            users?.let {
                userAdapter.submitList(it)
            }
        }
    }
}