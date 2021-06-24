package com.example.data.api.model

import com.example.data.database.model.UserEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("data")
    @Expose
    val data: List<User>,
    @SerializedName("total")
    @Expose
    val total: Int,
    @SerializedName("page")
    @Expose
    val page: Int,
    @SerializedName("limit")
    @Expose
    val limit: Int,
    @SerializedName("offset")
    @Expose
    val offset: Int
)

data class User (
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("firstName")
    @Expose
    val firstName: String,
    @SerializedName("lastName")
    @Expose
    val lastName: String,
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("picture")
    @Expose
    val picture: String
) {
    fun toUserEntity() = UserEntity(
        id = id,
        title = title,
        firstName = firstName,
        lastName = lastName,
        email = email,
        picture = picture
    )
}

data class Location (
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val timezone: String
)