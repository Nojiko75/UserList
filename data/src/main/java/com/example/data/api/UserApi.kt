package com.example.data.api

import com.example.data.api.model.UserFullProfileResponse
import com.example.data.api.model.UserListResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    suspend fun getUserList() : Response<UserListResponse>

    @GET("user/{userId}")
    suspend fun getUserFullProfile(userId: String) : Response<UserFullProfileResponse>
}