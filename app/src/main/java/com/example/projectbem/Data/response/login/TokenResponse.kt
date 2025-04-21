package com.example.projectbem.Data.response.login

import com.google.gson.annotations.SerializedName

data class TokenResponse(

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
