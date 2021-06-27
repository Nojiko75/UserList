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

    override suspend fun getUserList(): Result<List<UserListItem>> {
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

                        val userItemList = entities.map { it.toUserListItem() }

                        return Result.Success(userItemList)
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
                val userItemList = data.map { it.toUserListItem() }
                Result.Success(userItemList)
            } else {
                Result.Failure(Exception("error"), "no network connection")
            }
        }
    }

    override suspend fun getUserFullProfile(userId: String): Result<UserFullProfile> {
        if (networkStateManager.hasNetWorkConnection()) {
            return try {
                val response = userApi.getUserFullProfile(userId)
                if (response.isSuccessful) {
                    Log.d("REPO", "get users from api")
                    response.body()?.let { userResponse ->
                        Log.d("REPO", "response:$userResponse")

                        val userEntity = userResponse.toUserEntity()
                        withContext(Dispatchers.IO) { userDao.addUserFullProfile(userEntity) }

                        val user = userEntity.toUserFullProfile()

                        return Result.Success(user)
                    } ?: handleFailure(response)
                } else {
                    handleFailure(response)
                }
            } catch (e: Exception) {
                return Result.Failure(e, e.localizedMessage)
            }
        } else {
            val data = withContext(Dispatchers.IO) { userDao.getUserById(userId) }
            return run {
                Log.d("REPO", "get users from db")
                val user = data.toUserFullProfile()
                Result.Success(user)
            }
        }
    }
}