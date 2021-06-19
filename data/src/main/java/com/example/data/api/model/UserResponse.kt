package com.example.data.api.model

import com.example.data.database.model.UserEntity

data class UserResponse (
    val user: ArrayList<UserEntity>
)

data class Location (
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val timezone: String
)