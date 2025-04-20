package com.example.projectbem.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.projectbem.Data.response.UserItem
import com.example.projectbem.Data.retrofit.ApiService
import com.example.projectbem.Data.room.BemDao
import com.example.projectbem.Data.room.BemEntity
import kotlinx.coroutines.Dispatchers

class BemRepository private constructor(
    private val bemDao: BemDao,
    private val apiService: ApiService
    ){
    suspend fun login(username: String, password: String): LiveData<Result<UserItem>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val response = apiService.getUsers()
            val listUser = response.user

            val userlist = listUser?.find { user ->
                user?.username == username && user.nim == password
            }

            if (userlist != null) {
                val bemEntity = BemEntity(
                    id = userlist.id ?: 0,
                    username = userlist.username ?: "",
                    image = userlist.image ?: "",
                    statusOnline = userlist.statusOnline ?: false,
                    nim = userlist.nim ?: "",
                    role = userlist.role ?: "",
                    createdAt = userlist.createdAt ?: "",
                    updatedAt = userlist.updatedAt ?: "",
                    divisi = userlist.divisi ?: ""
                )
                bemDao.clearUser()
                bemDao.insertUser(bemEntity)
                emit(Result.Success(userlist))
            } else{
                emit(Result.Error("User not found"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
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
