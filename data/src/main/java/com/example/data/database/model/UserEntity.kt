package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.api.model.Location
import com.example.domain.model.UserFullProfile

@Entity(tableName = "Users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val email: String,
    val dateOfBirth: String,
    val registerDate: String,
    val phone: String,
    val picture: String,
    val location: Location
) {
    fun toUserFullProfile() = UserFullProfile (
        name = "$title $firstName $lastName",
        gender = gender,
        email = email,
        dateOfBirth = dateOfBirth,
        registerDate = registerDate,
        phone = phone,
        picture = picture
    )
}