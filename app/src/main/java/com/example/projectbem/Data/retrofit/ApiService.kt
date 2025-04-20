package com.example.projectbem.Data.retrofit

import com.example.projectbem.Data.response.UsersResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): UsersResponse
}