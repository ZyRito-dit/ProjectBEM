package com.example.projectbem.Data.response.divisi

import com.google.gson.annotations.SerializedName

data class SakitResponse(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("jenis_penyakit")
	val jenisPenyakit: String? = null,

	@field:SerializedName("penanganan")
	val penanganan: String? = null,

	@field:SerializedName("kelas")
	val kelas: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("status_penyembuhan")
	val statusPenyembuhan: String? = null,

	@field:SerializedName("petugas")
	val petugas: String? = null
)
