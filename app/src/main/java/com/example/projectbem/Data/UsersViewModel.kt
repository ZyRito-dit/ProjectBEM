package com.example.projectbem.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectbem.Data.response.LoginResponse
import com.example.projectbem.Data.response.UserItem
import kotlinx.coroutines.launch


class UsersViewModel(private val repository: BemRepository) : ViewModel() {

    private val _users = MutableLiveData<Result<LoginResponse>>()
    val users: LiveData<Result<LoginResponse>> = _users

    fun login(username: String, nim: String) {
        _users.value = Result.Loading

        viewModelScope.launch {
            try {
                val response = repository.login(username, nim)
                _users.value = Result.Success(response)
            } catch (e: Exception) {
                _users.value = Result.Error(e.message ?: "Unknown Error")
            }
        }
    }
}
