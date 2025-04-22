package com.example.projectbem.Data.response.user

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("nim")
	val nim: String? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("divisi")
	val divisi: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
