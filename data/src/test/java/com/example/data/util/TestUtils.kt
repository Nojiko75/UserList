package com.example.data.util

import com.example.data.api.model.UserResponse
import com.example.data.database.model.UserEntity

fun createSuccessUserResponse() : UserResponse {
    val fiedler = UserEntity("0F8JIqi4zwvb77FGz6Wt", "Fiedler", "Heinz-Georg", "heinz-georg.fiedler@example.com", "mr", "https://randomuser.me/api/portraits/men/81.jpg")
    val hughes = UserEntity("0P6E1d4nr0L1ntW8cjGU", "Hughes", "Katie", "katie.hughes@example.com", "miss", "https://randomuser.me/api/portraits/women/74.jpg")
    val userList = ArrayList<UserEntity>()
    userList.add(hughes)
    userList.add(fiedler)

    return UserResponse(userList)
}