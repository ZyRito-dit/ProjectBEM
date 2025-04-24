package com.example.projectbem.Data.retrofit

import com.example.projectbem.Data.response.LoginRequest
import com.example.projectbem.Data.response.LoginResponse
import com.example.projectbem.Data.response.ProfileResponse
import com.example.projectbem.Data.response.ResponseAddGallery
import com.example.projectbem.Data.response.ResponseGalleryItem
import com.example.projectbem.Data.response.ResponseUpdateProfile
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {



    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("user/profile")
    fun getProfileCall(): Call<ProfileResponse>

    @GET("user/profile")
    suspend fun getProfile(): Response<ProfileResponse>

    @GET("galeri")
    suspend fun getGallery(): List<ResponseGalleryItem>

    @DELETE("galeri/{id}")
    suspend fun deleteGallery(@Path("id") id: String): Response<Unit>

    @Multipart
    @POST("galeri")
    suspend fun uploadImageGallery(
        @Part image: MultipartBody.Part,
        @Part("judul") judul: RequestBody,
        @Part("deskripsi_singkat") deskripsiSingkat: RequestBody,
        @Part("deskripsi_panjang") deskripsiPanjang: RequestBody
    ): Response<ResponseBody>

    @Multipart
    @POST("user/profile") // ganti kalau endpoint-nya beda
    fun updateProfileWithImage(
        @Part("username") username: RequestBody,
        @Part image: MultipartBody.Part
    ): Call<ResponseUpdateProfile>


}