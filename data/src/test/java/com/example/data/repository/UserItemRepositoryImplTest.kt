package com.example.data.repository

import com.example.data.api.UserApi
import com.example.data.database.dao.UserDao
import com.example.data.util.*
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

class UserItemRepositoryImplTest {
    private val userId = "id"
    private val userApiMock: UserApi = mock()
    private val userDaoMock: UserDao = mock()
    private val networkStateManagerMock: NetworkStateManager = mock()
    private val userRepository = UserRepositoryImpl (
        userApiMock,
        userDaoMock,
        networkStateManagerMock
    )

    @Test
    fun get_user_list_should_work() {
        runBlocking {
            whenever(networkStateManagerMock.hasNetWorkConnection()).thenReturn(true)
            whenever(userApiMock.getUserList())
                .thenReturn(Response.success(createSuccessUserResponse()))
            whenever(userDaoMock.addUsers(createUserEntities())).then { doNothing() }
            userRepository.getUserList()

            verify(userApiMock, times(1)).getUserList()
            verify(userDaoMock, times(1)).addUsers(createUserEntities())
        }
    }

    @Test
    fun get_user_list_unauthorized_should_fail() {
        runBlocking {
            whenever(networkStateManagerMock.hasNetWorkConnection()).thenReturn(true)
            whenever(userApiMock.getUserList())
                .thenReturn(
                    Response.error(403, "App ID is not exist".toResponseBody("text".toMediaTypeOrNull()))
                )
            userRepository.getUserList()

            verify(userApiMock, times(1)).getUserList()
            verify(userDaoMock, never()).addUsers(createUserEntities())
        }
    }

    @Test
    fun get_user_list_from_db_without_connection_should_work() {
        runBlocking {
            whenever(networkStateManagerMock.hasNetWorkConnection()).thenReturn(false)
            userRepository.getUserList()
            verify(userApiMock, never()).getUserList()
            verify(userDaoMock, times(1)).findAllUsers()
        }
    }

    @Test
    fun get_user_full_profile_should_work() {
        runBlocking {
            whenever(networkStateManagerMock.hasNetWorkConnection()).thenReturn(true)
            whenever(userApiMock.getUserFullProfile(userId))
                .thenReturn(Response.success(createSuccessUserFullProfileResponse()))
            whenever(userDaoMock.addUserFullProfile(createUserEntity())).then { doNothing() }
            userRepository.getUserFullProfile(userId)

            verify(userApiMock, times(1)).getUserFullProfile(userId)
            verify(userDaoMock, times(1)).addUserFullProfile(createUserEntity())
        }
    }

    @Test
    fun get_user_full_profile_unauthorized_should_fail() {
        runBlocking {
            whenever(networkStateManagerMock.hasNetWorkConnection()).thenReturn(true)
            whenever(userApiMock.getUserFullProfile(userId))
                .thenReturn(
                    Response.error(401, "App ID is not exist".toResponseBody("text".toMediaTypeOrNull()))
                )
            userRepository.getUserFullProfile(userId)

            verify(userApiMock, times(1)).getUserFullProfile(userId)
            verify(userDaoMock, never()).addUserFullProfile(createUserEntity())
        }
    }

    @Test
    fun get_user_full_profile_from_db_without_connection_should_work() {
        runBlocking {
            whenever(networkStateManagerMock.hasNetWorkConnection()).thenReturn(false)
            userRepository.getUserFullProfile(userId)
            verify(userApiMock, never()).getUserFullProfile(userId)
            verify(userDaoMock, times(1)).getUserById(userId)
        }
    }
}