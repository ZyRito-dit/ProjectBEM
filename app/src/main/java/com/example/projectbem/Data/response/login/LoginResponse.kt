package com.example.projectbem.Data.response.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("nim")
	val nim: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
