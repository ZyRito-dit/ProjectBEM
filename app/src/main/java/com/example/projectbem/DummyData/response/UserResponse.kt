package com.example.projectbem.DummyData.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("user")
	val user: List<UserItem?>? = null
)

data class UserItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("nim")
	val nim: String? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("divisi")
	val divisi: Any? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
