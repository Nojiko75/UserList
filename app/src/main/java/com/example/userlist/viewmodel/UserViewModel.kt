package com.example.userlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Result
import com.example.domain.model.UserFullProfile
import com.example.domain.model.UserListItem
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val userList = MutableLiveData<List<UserListItem>>()

    fun getUserList() {
        viewModelScope.launch {
            when (val result = userRepository.getUserList()) {
                is Result.Success -> userList.value = result.successData
                is Result.Failure -> result.exception.localizedMessage
            }
        }
    }
}