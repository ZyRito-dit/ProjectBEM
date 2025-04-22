package com.example.projectbem.Data

import com.example.projectbem.Data.response.user.LoginRequest
import com.example.projectbem.Data.retrofit.ApiService
import com.example.projectbem.Data.room.BemDao
import com.example.projectbem.Data.room.BemEntity

class BemRepository private constructor(
    private val bemDao: BemDao,
    private var apiService: ApiService
){
    suspend fun login(request: LoginRequest) = apiService.login(request)

    suspend fun getProfile(token: String) = apiService.getProfile("token=$token")

    suspend fun saveUserData(entity: BemEntity) = bemDao.insertUser(entity)

    suspend fun getUserData(): BemEntity? = bemDao.getUser()

    suspend fun logout() = bemDao.deleteUser()

    suspend fun getAllEvents() = apiService.getAllEvents()

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