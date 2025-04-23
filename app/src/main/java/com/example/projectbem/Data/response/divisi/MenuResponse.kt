package com.example.projectbem.Data.response.divisi

import com.google.gson.annotations.SerializedName

data class MenuResponse(

	@field:SerializedName("data")
	val data: List<DataMenu?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataMenu(

	@field:SerializedName("hari")
	val hari: String? = null,

	@field:SerializedName("status_kesesuaian")
	val statusKesesuaian: String? = null,

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("waktu")
	val waktu: String? = null,

	@field:SerializedName("pekan")
	val pekan: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("menu")
	val menu: String? = null,

	@field:SerializedName("bulan")
	val bulan: String? = null
)
