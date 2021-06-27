package com.example.data.util

import com.example.data.api.model.UserItem
import com.example.data.api.model.UserListResponse
import com.example.data.database.model.UserEntity

fun createSuccessUserResponse() : UserListResponse {
    val fiedler = UserItem("0F8JIqi4zwvb77FGz6Wt", "mr", "Heinz-Georg", "Fiedler", "heinz-georg.fiedler@example.com", "https://randomuser.me/api/portraits/men/81.jpg")
    val hughes = UserItem("0P6E1d4nr0L1ntW8cjGU", "miss", "Katie", "Hughes", "katie.hughes@example.com", "https://randomuser.me/api/portraits/women/74.jpg")
    val userList = ArrayList<UserItem>()
    userList.add(hughes)
    userList.add(fiedler)

    return UserListResponse(userList, 1, 20, 1, 0)
}

fun createUserEntities() : List<UserEntity> {
    return createSuccessUserResponse().data.map { it.toUserEntity() }
}