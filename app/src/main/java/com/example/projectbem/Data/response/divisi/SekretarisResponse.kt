package com.example.projectbem.Data.response.divisi

import com.google.gson.annotations.SerializedName

data class SekretarisResponse(

    @field:SerializedName("notulen")
	val notulen: Notulen? = null,

    @field:SerializedName("message")
	val message: String? = null
)

data class Notulen(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("solusi")
	val solusi: String? = null,

	@field:SerializedName("tgl_terlaksana")
	val tglTerlaksana: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("terlaksana")
	val terlaksana: Int? = null,

	@field:SerializedName("bulan")
	val bulan: String? = null,

	@field:SerializedName("divisi")
	val divisi: String? = null,

	@field:SerializedName("permasalahan")
	val permasalahan: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
