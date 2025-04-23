package com.example.projectbem.Data.response.divisi

import com.google.gson.annotations.SerializedName

data class PendidikanResponse(

	@field:SerializedName("PendidikanResponse")
	val pendidikanResponse: List<PendidikanResponseItem?>? = null
)

data class PendidikanResponseItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("kondisi")
	val kondisi: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("nama_barang")
	val namaBarang: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("stok")
	val stok: Int? = null
)
