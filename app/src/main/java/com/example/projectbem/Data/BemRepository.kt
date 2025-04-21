package com.example.projectbem.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.projectbem.Data.response.login.LoginResponse
import com.example.projectbem.Data.response.login.TokenResponse
import com.example.projectbem.Data.retrofit.ApiService
import com.example.projectbem.Data.room.BemDao
import com.example.projectbem.Data.room.BemEntity
import kotlinx.coroutines.Dispatchers

class BemRepository private constructor(
    private val bemDao: BemDao,
    private val apiService: ApiService
    ){
    fun login(username: String, nim: String): LiveData<Result<TokenResponse>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val request = LoginResponse(username = username, nim = nim)
            val response = apiService.getLogin(request)

            val user = BemEntity(
                username = username,
                password = nim,
                role = response.role ?: "",
                token = response.token ?: ""
            )

            bemDao.clearUser()
            bemDao.insertUser(user)

            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Terjadi kesalahan"))
        }
    }

    suspend fun getLogin(): BemEntity {
        return bemDao.getUser()
    }

    suspend fun logoutUser() {
        bemDao.clearUser()
    }

    companion object {
        @Volatile
        private var INSTANCE: BemRepository? = null

        fun getInstance(bemDao: BemDao, apiService: ApiService): BemRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: BemRepository(bemDao, apiService).also { INSTANCE = it }
            }
        }
    }
}
