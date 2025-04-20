package com.example.projectbem.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectbem.Data.response.UserItem
import kotlinx.coroutines.launch

class UsersViewModel(private val bemRepository: BemRepository) : ViewModel() {
    private val _users = MutableLiveData<Result<UserItem>>()
    val users: LiveData<Result<UserItem>> get() = _users

    fun login(username: String, password: String) {
        viewModelScope.launch {
            bemRepository.login(username, password).observeForever{
                _users.value = it
            }
        }
    }
}