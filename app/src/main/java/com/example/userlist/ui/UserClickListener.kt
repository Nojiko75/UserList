package com.example.userlist.ui

import com.example.domain.model.UserFullProfile

interface UserClickListener {
    fun onItemClick(user: UserFullProfile)
}