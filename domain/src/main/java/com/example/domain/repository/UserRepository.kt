package com.example.domain.repository

import com.example.domain.model.Result
import com.example.domain.model.UserFullProfile
import com.example.domain.model.UserListItem

interface UserRepository {
    suspend fun getUserList() : Result<List<UserListItem>>

    suspend fun getUserFullProfile(userId: String) : Result<UserFullProfile>
}