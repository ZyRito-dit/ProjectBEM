package com.example.projectbem.Data.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("image")
	val image: Any,

	@field:SerializedName("nim")
	val nim: String,

	@field:SerializedName("role")
	val role: String,

	@field:SerializedName("divisi")
	val divisi: String,

	@field:SerializedName("username")
	val username: String
)
