package com.example.domain.model

import java.io.Serializable

data class UserFullProfile (
    val name: String,
    val email: String,
    val picture: String
) : Serializable