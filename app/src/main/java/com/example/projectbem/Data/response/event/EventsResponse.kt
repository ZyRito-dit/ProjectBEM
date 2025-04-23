package com.example.projectbem.Data.response.event

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class EventsResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
@Parcelize
data class DataItem(

	@field:SerializedName("gambarCover")
	val gambarCover: String? = null,

	@field:SerializedName("kuotaPeserta")
	val kuotaPeserta: Int? = null,

	@field:SerializedName("gambarLogo")
	val gambarLogo: String? = null,

	@field:SerializedName("jumlahPendaftar")
	val jumlahPendaftar: Int? = null,

	@field:SerializedName("penanggungJawab")
	val penanggungJawab: String? = null,

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("ringkasan")
	val ringkasan: String? = null,

	@field:SerializedName("judulKegiatan")
	val judulKegiatan: String? = null,

	@field:SerializedName("deskripsiLengkap")
	val deskripsiLengkap: String? = null,

	@field:SerializedName("lokasiKegiatan")
	val lokasiKegiatan: String? = null,

	@field:SerializedName("waktuMulai")
	val waktuMulai: String? = null,

	@field:SerializedName("waktuSelesai")
	val waktuSelesai: String? = null,

	@field:SerializedName("linkPendaftaran")
	val linkPendaftaran: String? = null,

	@field:SerializedName("mediaCoverUrl")
	val mediaCoverUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("imageLogoUrl")
	val imageLogoUrl: String? = null
): Parcelable
