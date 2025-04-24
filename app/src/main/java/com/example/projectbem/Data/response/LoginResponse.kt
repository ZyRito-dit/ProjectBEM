package com.example.projectbem.Data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("role")
	val role: String,

	@field:SerializedName("token")
	val token: String
)
