package com.example.projectbem.Data.retrofit

import com.example.projectbem.Data.response.event.EventsResponse
import com.example.projectbem.Data.response.user.LoginRequest
import com.example.projectbem.Data.response.divisi.Notulen
import com.example.projectbem.Data.response.divisi.PendidikanResponseItem
import com.example.projectbem.Data.response.user.TokenResponse
import com.example.projectbem.Data.response.user.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<TokenResponse>

    @GET("user/profile")
    suspend fun getProfile(@Header("Cookie") token: String): Response<UserResponse>

    @GET("event")
    suspend fun getAllEvents(): EventsResponse

    @GET("sekretaris/notulen")
    suspend fun getNotulenByBulan(
        @Query("bulan") bulan: String
    ): List<Notulen>

    @GET("pendidikan/barang")
    suspend fun getAllBarang(@Header("Cookie") token: String): Response<PendidikanResponseItem>
}