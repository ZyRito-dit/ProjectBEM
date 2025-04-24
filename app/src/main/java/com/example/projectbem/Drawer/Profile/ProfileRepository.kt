package com.example.projectbem.Data.repository

import com.example.projectbem.Data.response.ProfileResponse
import com.example.projectbem.Data.retrofit.ApiService
import retrofit2.Response

class ProfileRepository(private val apiService: ApiService) {
    suspend fun getProfile(): Response<ProfileResponse> {
        return apiService.getProfile()
    }
}
