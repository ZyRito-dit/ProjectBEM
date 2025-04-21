package com.example.projectbem.Data

import com.example.projectbem.Data.response.LoginResponse
import com.example.projectbem.Data.retrofit.ApiService
import com.example.projectbem.Data.room.BemDao
import org.json.JSONObject

class BemRepository private constructor(
    private val bemDao: BemDao,
    private val apiService: ApiService
) {

    suspend fun login(username: String, nim: String): Result<LoginResponse> {
        return try {
            val response = apiService.loginUser(LoginRequest(username, nim))
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.Success(body)
                } else {
                    Result.Error("Response kosong dari server")
                }
            } else {
                val errorMessage = try {
                    val errorJson = response.errorBody()?.string()
                    JSONObject(errorJson ?: "").getString("message")
                } catch (e: Exception) {
                    "Terjadi kesalahan saat membaca pesan error"
                }
                Result.Error(errorMessage)
            }
        } catch (e: Exception) {
            Result.Error("Terjadi kesalahan: ${e.message}")
        }
    }

    companion object {
        @Volatile
        private var instance: BemRepository? = null

        fun getInstance(
            bemDao: BemDao,
            apiService: ApiService
        ): BemRepository =
            instance ?: synchronized(this) {
                instance ?: BemRepository(bemDao, apiService)
            }.also { instance = it }
    }
}
