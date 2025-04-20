package com.example.projectbem.Data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class UsersResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("user")
	val user: List<UserItem?>? = null
)
@Parcelize
data class UserItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("status_online")
	val statusOnline: Boolean? = null,

	@field:SerializedName("nim")
	val nim: String? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("divisi")
	val divisi: String? = null,

	@field:SerializedName("authId")
	val authId: Int? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
): Parcelable
