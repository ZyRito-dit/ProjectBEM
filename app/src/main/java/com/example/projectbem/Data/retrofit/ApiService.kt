package com.example.projectbem.Data.retrofit

import com.example.projectbem.Data.response.login.LoginResponse
import com.example.projectbem.Data.response.login.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun getLogin(
        @Body request: LoginResponse
    ): TokenResponse
}