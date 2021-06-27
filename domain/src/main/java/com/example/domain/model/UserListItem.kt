package com.example.domain.model

import java.io.Serializable

data class UserListItem (
    val id: String,
    val name: String,
    val email: String,
    val picture: String
) : Serializable