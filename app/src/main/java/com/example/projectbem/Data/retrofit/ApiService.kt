package com.example.projectbem.Data.retrofit

import com.example.projectbem.Data.LoginRequest
import com.example.projectbem.Data.response.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("user/profile")
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginResponse>

}


