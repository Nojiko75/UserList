package com.example.userlist.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.UserListItem
import com.example.userlist.R
import com.example.userlist.databinding.UserItemBinding
import com.squareup.picasso.Picasso

class UserRecyclerAdapter(
    private val context: Context?,
    val clickListener: UserClickListener
) : RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {

    var userList : List<UserListItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflatedView: UserItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.user_item, parent, false)
        return UserViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindUser(position)
    }

    override fun getItemCount() = userList.size

    fun setUsers(users: List<UserListItem>) {
        this.userList = users
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val v: UserItemBinding) : RecyclerView.ViewHolder(v.root) {

        fun bindUser(position: Int) {
            val item = userList[position]
            v.user = item
            Picasso.get()
                .load(item.picture)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(v.picture)
            v.userClickInterface = clickListener

            v.root.setOnClickListener {
                clickListener.onItemClick(item)
            }
        }
    }

}