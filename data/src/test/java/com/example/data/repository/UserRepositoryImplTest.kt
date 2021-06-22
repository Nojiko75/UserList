package com.example.data.repository

import com.example.data.api.UserApi
import com.example.data.database.dao.UserDao
import com.example.data.util.NetworkStateManager
import com.example.data.util.createSuccessUserResponse
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

class UserRepositoryImplTest {
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
            whenever(userApiMock.getUserList()).thenReturn(Response.success(createSuccessUserResponse()))
            whenever(userDaoMock.addUsers(createSuccessUserResponse().user)).then { doNothing() }
            userRepository.getUserList()
            verify(userApiMock, times(1)).getUserList()
            verify(userDaoMock, times(1)).addUsers(createSuccessUserResponse().user)
        }
    }

    @Test
    fun get_user_list_unauthorized_should_fail() {
        runBlocking {
            whenever(networkStateManagerMock.hasNetWorkConnection()).thenReturn(true)
            whenever(userApiMock.getUserList())
                .thenReturn(
                    Response.error(401, "unauthorized".toResponseBody("text".toMediaTypeOrNull())))
                            userRepository.getUserList()

            verify(userApiMock, times(1)).getUserList()
            verify(userDaoMock, never()).addUsers(createSuccessUserResponse().user)
        }
    }

    @Test
    fun get_user_list_without_connection_should_fail() {
        runBlocking {
            whenever(networkStateManagerMock.hasNetWorkConnection()).thenReturn(true)
            whenever(userApiMock.getUserList())
                .thenReturn(
                    Response.error(500, "no network connection".toResponseBody("text".toMediaTypeOrNull())))
            userRepository.getUserList()

            verify(userApiMock, times(1)).getUserList()
            verify(userDaoMock, never()).addUsers(createSuccessUserResponse().user)
        }
    }
}