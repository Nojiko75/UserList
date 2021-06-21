package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.api.model.Location
import com.example.domain.model.UserFullProfile

@Entity(tableName = "Users")
data class UserEntity (
    @PrimaryKey val id: String,
    val lastName: String,
    val firstName: String,
    val email: String,
    val title: String,
    val picture: String
) {
    fun toUserFullProfile() = UserFullProfile (
        name = "$title $lastName $firstName",
        email = email,
        picture = picture
    )
}