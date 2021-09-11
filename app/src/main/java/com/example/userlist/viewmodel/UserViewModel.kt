package com.example.userlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Result
import com.example.domain.model.UserFullProfile
import com.example.domain.usecase.GetUserFullProfile
import com.example.domain.usecase.GetUserList
import com.example.userlist.ui.base.Success
import com.example.userlist.ui.base.ViewState
import com.example.userlist.ui.base.Error
import com.example.userlist.ui.base.Loading
import kotlinx.coroutines.launch

class UserViewModel (
    private val getUserList: GetUserList,
    private val getUserFullProfile: GetUserFullProfile
) : ViewModel() {

    var userFullProfile = MutableLiveData<UserFullProfile>()
    val viewState = MutableLiveData<ViewState<Any>>()

    fun getUserList() {
        viewModelScope.launch {
            viewState.value = Loading()
            when (val result = getUserList.getUserList()) {
                is Result.Success -> viewState.value = Success(result.successData)
                is Result.Failure -> viewState.value = Error(result.exception)
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