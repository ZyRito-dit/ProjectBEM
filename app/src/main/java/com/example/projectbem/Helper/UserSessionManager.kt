package com.example.projectbem.Helper // sesuaikan dengan package kamu

import android.content.Context
import android.content.SharedPreferences

class UserSessionManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("USER_SESSION", Context.MODE_PRIVATE)

    companion object {
        private const val USERNAME = "USERNAME"
        private const val NIM = "NIM"
        private const val ROLE = "ROLE"
        private const val IMAGE_URL = "IMAGE_URL"
    }

    fun saveUserData(username: String, nim: String, role: String, imageUrl: String) {
        prefs.edit().apply {
            putString(USERNAME, username)
            putString(NIM, nim)
            putString(ROLE, role)
            putString(IMAGE_URL, imageUrl)
            apply()
        }
    }

    fun getUsername(): String? = prefs.getString(USERNAME, null)

    fun getNim(): String? = prefs.getString(NIM, null)

    fun getRole(): String? = prefs.getString(ROLE, null)

    fun getImageUrl(): String? = prefs.getString(IMAGE_URL, null)

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
