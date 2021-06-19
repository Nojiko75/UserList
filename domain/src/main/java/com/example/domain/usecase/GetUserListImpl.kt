package com.example.domain.usecase

import com.example.domain.model.Result
import com.example.domain.model.UserFullProfile
import com.example.domain.repository.UserRepository

class GetUserListImpl (
    private val userRepository: UserRepository
) : GetUserList {

    override suspend fun getUserList(): Result<List<UserFullProfile>> {
        return userRepository.getUserList()
    }
}