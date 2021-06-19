package com.example.data.repository

import android.content.Context
import com.example.data.api.UserApi
import com.example.data.database.dao.UserDao
import com.example.domain.model.*
import com.example.domain.repository.UserRepository
import com.example.data.util.NetworkManager.hasNetworkConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl (
    private val userApi: UserApi,
    private val userDao: UserDao,
    private val context: Context
) : UserRepository {

    override suspend fun getUserList(): Result<List<UserFullProfile>> {
        if (hasNetworkConnection(context)) {
            return try {
                val response = userApi.getUserList()
                if (response.isSuccessful) {
                    response.body()?.let { userResponse ->
                        val userList = userResponse.user
                        withContext(Dispatchers.IO) { userDao.addUsers(userList) }

                        val userFullProfileList = userList.map { it.toUserFullProfile() }

                        return Result.Success(userFullProfileList)
                    } ?: return Result.Failure(ApiError(Exception("error"), "error"))
                } else {
                    return Result.Failure(ApiError(Exception("error"), "error"))
                }
            } catch (e: Exception) {
                return Result.Failure(ApiError(e, e.localizedMessage))
            }
        } else {
            val data = withContext(Dispatchers.IO) { userDao.findAllUsers() }
            return if (data.isNotEmpty()) {
                val userFullProfileList = data.map { it.toUserFullProfile() }
                Result.Success(userFullProfileList)
            } else {
                Result.Failure(ApiError(Exception("error"), "error"))
            }
        }
    }
}