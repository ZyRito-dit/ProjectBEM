package com.example.projectbem.Data.response.divisi

import com.google.gson.annotations.SerializedName

data class ObatResponse(

	@field:SerializedName("ObatResponse")
	val obatResponse: List<ObatResponseItem?>? = null
)

data class ObatResponseItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("tgl_kadaluarsa")
	val tglKadaluarsa: String? = null,

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("nama_obat")
	val namaObat: String? = null,

	@field:SerializedName("stok")
	val stok: Int? = null
)
