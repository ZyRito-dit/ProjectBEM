package com.example.projectbem.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectbem.Data.response.login.TokenResponse
import com.example.projectbem.Data.room.BemEntity
import kotlinx.coroutines.launch

class UsersViewModel(private val repository: BemRepository) : ViewModel() {
    fun login(username: String, nim: String): LiveData<Result<TokenResponse>> {
        return repository.login(username, nim)
    }

    fun getUser(onResult: (BemEntity?) -> Unit) {
        viewModelScope.launch {
            try {
                val user = repository.getLogin()
                onResult(user)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logoutUser()
        }
    }
}