package com.example.userlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Result
import com.example.domain.model.UserFullProfile
import com.example.domain.model.UserListItem
import com.example.domain.usecase.GetUserFullProfile
import com.example.domain.usecase.GetUserList
import kotlinx.coroutines.launch

class UserViewModel (
    private val getUserList: GetUserList,
    private val getUserFullProfile: GetUserFullProfile
) : ViewModel() {

    val userList = MutableLiveData<List<UserListItem>>()
    var userFullProfile = MutableLiveData<UserFullProfile>()

    fun getUserList() {
        viewModelScope.launch {
            when (val result = getUserList.getUserList()) {
                is Result.Success -> userList.value = result.successData
                is Result.Failure -> result.exception.localizedMessage
            }
        }
    }

    fun getUserFullProfile(userId: String) {
        viewModelScope.launch {
            when (val result = getUserFullProfile.getUserFullProfile(userId)) {
                is Result.Success -> userFullProfile.value = result.successData
                is Result.Failure -> result.exception.localizedMessage
            }
        }
    }
}