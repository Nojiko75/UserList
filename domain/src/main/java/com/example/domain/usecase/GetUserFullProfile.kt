package com.example.domain.usecase

import com.example.domain.model.Result
import com.example.domain.model.UserFullProfile

interface GetUserFullProfile {
    suspend fun getUserFullProfile(userId: String) : Result<UserFullProfile>
}