package com.example.projectbem.Data



import com.example.projectbem.Data.response.LoginResponse
import com.example.projectbem.Data.retrofit.ApiService
import com.example.projectbem.Data.room.BemDao
import java.lang.Exception

class BemRepository private constructor(
    private val bemDao: BemDao,
    private val apiService: ApiService
) {


    suspend fun login(username: String, nim: String): LoginResponse {
        try {

            val response = apiService.loginUser(LoginRequest(username, nim))


            if (response.isSuccessful) {
                val body = response.body()
                return body ?: throw Exception("Response kosong dari server")
            } else {

                val errorMessage = response.errorBody()?.string() ?: "Login gagal"
                throw Exception(errorMessage)
            }
        } catch (e: Exception) {

            throw Exception("Terjadi kesalahan: ${e.message}")
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


