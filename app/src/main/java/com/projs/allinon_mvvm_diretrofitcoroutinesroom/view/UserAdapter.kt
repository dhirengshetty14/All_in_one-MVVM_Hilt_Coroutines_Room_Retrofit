package com.projs.allinon_mvvm_diretrofitcoroutinesroom.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.R
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.local.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var users: List<User> = emptyList()

    fun submitList(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvUserId: TextView = itemView.findViewById(R.id.tvUserId)
        private val tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
        private val tvUserEmail: TextView = itemView.findViewById(R.id.tvUserEmail)
        private val ivIdBadge: ImageView = itemView.findViewById(R.id.ivIdBadge)

        fun bind(user: User) {
            tvUserId.text = "ID: ${user.id}"
            tvUserName.text = "Name: ${user.name}"
            tvUserEmail.text = user.email

            // Add subtle animation on bind
            itemView.alpha = 0.5f
            itemView.animate()
                .alpha(1.0f)
                .setDuration(300)
                .start()
        }
    }
}
