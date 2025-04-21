package com.example.projectbem.Data.token

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val sharedPref = context.getSharedPreferences("MYAPP", Context.MODE_PRIVATE)
        val token = sharedPref.getString("TOKEN", "")

        val request = chain.request().newBuilder()
            .addHeader("Cookie", "token $token")
            .build()

        return chain.proceed(request)
    }
}
