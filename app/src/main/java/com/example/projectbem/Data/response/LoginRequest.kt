package com.example.projectbem.Data.response

import com.google.gson.annotations.SerializedName

data class LoginRequest(

	@field:SerializedName("nim")
	val nim: String,

	@field:SerializedName("username")
	val username: String
)
