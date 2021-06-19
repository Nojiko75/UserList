package com.example.data.api

import com.example.data.api.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    suspend fun getUserList() : Response<UserResponse>
}