package com.example.projectbem.Data.response.divisi

import com.google.gson.annotations.SerializedName

data class AlatResponse(

	@field:SerializedName("AlatResponse")
	val alatResponse: List<AlatResponseItem?>? = null
)

data class AlatResponseItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("kondisi")
	val kondisi: String? = null,

	@field:SerializedName("nama_alat")
	val namaAlat: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("stok")
	val stok: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
