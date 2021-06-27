package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class UserFullProfile(
    val id: String,
    val name: String,
    val gender: String?,
    val email: String,
    val dateOfBirth: String?,
    val registerDate: String?,
    val phone: String?,
    val picture: String,
    val location: String?,
    val updatedAt: String?
) : Serializable, Parcelable