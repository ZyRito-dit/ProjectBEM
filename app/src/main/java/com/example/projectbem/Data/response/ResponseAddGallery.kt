package com.example.projectbem.Data.response

import com.google.gson.annotations.SerializedName

data class ResponseAddGallery(

	@field:SerializedName("galeri")
	val galeri: Galeri,

	@field:SerializedName("message")
	val message: String
)

data class Galeri(

	@field:SerializedName("deskripsi_singkat")
	val deskripsiSingkat: String,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("deskripsi_panjang")
	val deskripsiPanjang: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("judul")
	val judul: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
