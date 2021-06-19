package com.example.domain.usecase

import com.example.domain.model.Result
import com.example.domain.model.UserFullProfile

interface GetUserList {
    suspend fun getUserList() : Result<List<UserFullProfile>>
}