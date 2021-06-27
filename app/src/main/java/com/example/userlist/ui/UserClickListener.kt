package com.example.userlist.ui

import com.example.domain.model.UserListItem

interface UserClickListener {
    fun onItemClick(user: UserListItem)
}