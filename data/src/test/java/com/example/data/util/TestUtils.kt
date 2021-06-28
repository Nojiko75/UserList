package com.example.data.util

import com.example.data.api.model.UserFullProfileResponse
import com.example.data.api.model.UserItem
import com.example.data.api.model.UserListResponse
import com.example.data.database.model.UserEntity
import com.example.domain.model.Location

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

fun createSuccessUserFullProfileResponse() : UserFullProfileResponse {
    val location = Location("Timesquare", "New York", "New York", "United States", "009")
    return UserFullProfileResponse("id", "mr", "John", "Doe", "male", "john.doe@example.com", "1996-04-30T19:26:49.610Z", "555410", "https://picture.png", location, "1996-04-30T19:26:49.610Z", "1996-04-30T19:26:49.610Z")
}

fun createUserEntity() : UserEntity {
    return createSuccessUserFullProfileResponse().toUserEntity()
}