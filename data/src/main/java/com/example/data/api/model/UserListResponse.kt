package com.example.data.api.model

import com.example.data.database.model.UserEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserListResponse (
    @SerializedName("data")
    @Expose
    val data: List<UserItem>,
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

data class UserItem (
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
    @SerializedName("picture")
    @Expose
    val picture: String
) {
    fun toUserEntity() = UserEntity (
        id = id,
        title = title,
        firstName = firstName,
        lastName = lastName,
        gender = null,
        email = null,
        dateOfBirth = null,
        registerDate = null,
        phone = null,
        picture = picture,
        location = null,
        updatedAt = null
    )
}