package com.example.domain.model

import java.io.Serializable

data class UserFullProfile(
    val name: String,
    val gender: String?,
    val email: String,
    val dateOfBirth: String?,
    val registerDate: String?,
    val phone: String?,
    val picture: String,
    val location: Location?,
    val updatedAt: String?
) : Serializable