package com.example.projectbem.Data.response

import com.google.gson.annotations.SerializedName



data class ResponseGalleryItem(

	@SerializedName("deskripsi_singkat")
	val deskripsiSingkat: String,

	@SerializedName("image")
	val image: String,

	@SerializedName("createdAt")
	val createdAt: String,

	@SerializedName("imageUrl")
	val imageUrl: String,

	@SerializedName("deskripsi_panjang")
	val deskripsiPanjang: String,

	@SerializedName("id")
	val id: Int,

	@SerializedName("judul")
	val judul: String,

	@SerializedName("updatedAt")
	val updatedAt: String
)
