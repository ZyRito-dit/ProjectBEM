package com.example.projectbem.Home.dashboard_item.Gallery

import com.example.projectbem.Data.response.ResponseAddGallery
import com.example.projectbem.Data.response.ResponseGalleryItem
import com.example.projectbem.Data.retrofit.ApiService
import okhttp3.MultipartBody
import retrofit2.Response

class GalleryRepository(private val apiService: ApiService) {

    suspend fun fetchGallery(): List<ResponseGalleryItem> {
        return apiService.getGallery()
    }

}

