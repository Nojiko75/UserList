package com.example.domain.usecase

import com.example.domain.model.Result
import com.example.domain.model.UserFullProfile
import com.example.domain.model.UserListItem

interface GetUserList {
    suspend fun getUserList() : Result<List<UserListItem>>
}