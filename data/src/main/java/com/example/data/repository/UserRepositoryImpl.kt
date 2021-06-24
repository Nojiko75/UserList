package com.example.data.repository

import android.util.Log
import com.example.data.api.UserApi
import com.example.data.api.util.handleFailure
import com.example.data.database.dao.UserDao
import com.example.domain.model.*
import com.example.domain.repository.UserRepository
import com.example.data.util.NetworkStateManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl (
    private val userApi: UserApi,
    private val userDao: UserDao,
    private val networkStateManager: NetworkStateManager
) : UserRepository {

    override suspend fun getUserList(): Result<List<UserFullProfile>> {
        if (networkStateManager.hasNetWorkConnection()) {
            return try {
                val response = userApi.getUserList()
                if (response.isSuccessful) {
                    Log.d("REPO", "get users from api")
                    response.body()?.let { userResponse ->
                        Log.d("REPO", "response:$response")
                        val userList = userResponse.data
                        val entities = userList.map { it.toUserEntity() }
                        withContext(Dispatchers.IO) { userDao.addUsers(entities) }

                        val userFullProfileList = entities.map { it.toUserFullProfile() }

                        return Result.Success(userFullProfileList)
                    } ?: handleFailure(response)
                } else {
                    handleFailure(response)
                }
            } catch (e: Exception) {
                return Result.Failure(e, e.localizedMessage)
            }
        } else {
            val data = withContext(Dispatchers.IO) { userDao.findAllUsers() }
            return if (data.isNotEmpty()) {
                Log.d("REPO", "get users from db")
                val userFullProfileList = data.map { it.toUserFullProfile() }
                Result.Success(userFullProfileList)
            } else {
                Result.Failure(Exception("error"), "no network connection")
            }
        }
    }
}