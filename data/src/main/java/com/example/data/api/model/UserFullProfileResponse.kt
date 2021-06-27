package com.example.data.api.model

import com.example.data.database.model.UserEntity
import com.example.domain.model.Location

data class UserFullProfileResponse (
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val email: String,
    val dateOfBirth: String,
    val phone: String,
    val picture: String,
    val location: Location,
    val registerDate: String,
    val updatedAt: String

){
    fun toUserEntity() = UserEntity (
        id = id,
        title = title,
        firstName = firstName,
        lastName = lastName,
        gender = null,
        email = email,
        dateOfBirth = dateOfBirth,
        registerDate = registerDate,
        phone = phone,
        picture = picture,
        location = location,
        updatedAt = updatedAt
    )
}