package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.UserFullProfile

@Entity(tableName = "Users")
data class UserEntity (
    @PrimaryKey val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val picture: String
) {
    fun toUserFullProfile() = UserFullProfile (
        name = "$title $firstName $lastName",
        email = email,
        picture = picture
    )
}