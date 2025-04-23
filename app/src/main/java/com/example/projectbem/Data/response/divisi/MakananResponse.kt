package com.example.projectbem.Data.response.divisi

import com.google.gson.annotations.SerializedName

data class MakananResponse(

	@field:SerializedName("MakananResponse")
	val makananResponse: List<MakananResponseItem?>? = null
)

data class MakananResponseItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("nama_makanan")
	val namaMakanan: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
