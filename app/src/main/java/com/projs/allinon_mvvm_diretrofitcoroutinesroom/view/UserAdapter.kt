package com.projs.allinon_mvvm_diretrofitcoroutinesroom.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.R
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.local.User

class UserAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var users: List<User> = emptyList()
    private val VIEW_TYPE_USER = 0
    private val VIEW_TYPE_EMPTY = 1

    fun submitList(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (users.isEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_USER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_empty_state, parent, false)
                EmptyStateViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_user, parent, false)
                UserViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> holder.bind(users[position], position)
            is EmptyStateViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int = if (users.isEmpty()) 1 else users.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvUserId: TextView = itemView.findViewById(R.id.tvUserId)
        private val tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
        private val tvUserEmail: TextView = itemView.findViewById(R.id.tvUserEmail)
        private val tvAvatarInitial: TextView = itemView.findViewById(R.id.tvAvatarInitial)

        fun bind(user: User, position: Int) {
            tvUserId.text = "ID: ${user.id}"
            tvUserName.text = user.name
            tvUserEmail.text = user.email

            // Generate avatar initial from first letter of name
            tvAvatarInitial.text = user.name.firstOrNull()?.toString()?.uppercase() ?: "U"

            // Add staggered animation with delay based on position
            itemView.alpha = 0f
            itemView.translationY = 50f
            itemView.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(500)
                .setStartDelay(position * 100L)
                .start()

            // Add subtle scale animation on click
            itemView.setOnClickListener {
                it.animate()
                    .scaleX(0.95f)
                    .scaleY(0.95f)
                    .setDuration(100)
                    .withEndAction {
                        it.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(100)
                            .start()
                    }
                    .start()
            }
        }
    }

    class EmptyStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivEmptyState: ImageView = itemView.findViewById(R.id.ivEmptyState)

        fun bind() {
            // Add floating animation to the empty state icon
            ivEmptyState.animate()
                .translationY(-10f)
                .setDuration(1000)
                .setStartDelay(500)
                .withEndAction {
                    ivEmptyState.animate()
                        .translationY(10f)
                        .setDuration(1000)
                        .withEndAction {
                            bind() // Loop the animation
                        }
                        .start()
                }
                .start()
        }
    }
}
