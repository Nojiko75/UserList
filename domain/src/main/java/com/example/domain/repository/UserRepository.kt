package com.example.domain.repository

import com.example.domain.model.Result
import com.example.domain.model.UserFullProfile

interface UserRepository {
    suspend fun getUserList() : Result<List<UserFullProfile>>
}