package com.example.domain.usecase

import com.example.domain.model.Result
import com.example.domain.model.UserFullProfile
import com.example.domain.repository.UserRepository

class GetUserFullProfileImpl (
    private val userRepository: UserRepository
) : GetUserFullProfile {

    override suspend fun getUserFullProfile(userId: String): Result<UserFullProfile> {
        return userRepository.getUserFullProfile(userId)
    }
}