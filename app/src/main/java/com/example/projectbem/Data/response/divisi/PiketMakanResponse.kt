package com.example.projectbem.Data.response.divisi

import com.google.gson.annotations.SerializedName

data class PiketMakanResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("anggota_kelompok")
	val anggotaKelompok: String? = null,

	@field:SerializedName("nama_kelompok")
	val namaKelompok: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
