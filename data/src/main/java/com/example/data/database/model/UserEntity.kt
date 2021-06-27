package com.example.data.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Location
import com.example.domain.model.UserFullProfile
import com.example.domain.model.UserListItem

@Entity(tableName = "Users")
data class UserEntity (
    @PrimaryKey val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val gender: String?,
    val email: String,
    val dateOfBirth: String?,
    val registerDate: String?,
    val phone: String?,
    val picture: String,
    @Embedded val location: Location?,
    val updatedAt: String?
) {
    fun toUserListItem() = UserListItem (
        name = "$title $firstName $lastName",
        email = email,
        picture = picture
    )

    fun toUserFullProfile() = UserFullProfile (
        name = "$title $firstName $lastName",
        gender = gender,
        email = email,
        dateOfBirth = dateOfBirth,
        registerDate = registerDate,
        phone = phone,
        picture = picture,
        location = null,
        updatedAt = updatedAt
    )
}