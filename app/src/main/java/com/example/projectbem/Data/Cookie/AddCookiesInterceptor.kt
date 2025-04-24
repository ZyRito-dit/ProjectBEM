package com.example.projectbem.Data.Cookie

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AddCookiesInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val prefs = context.getSharedPreferences("COOKIE_PREFS", Context.MODE_PRIVATE)
        val cookies = prefs.getStringSet("cookies", HashSet())
        for (cookie in cookies!!) {
            builder.addHeader("Cookie", cookie)
            Log.d("COOKIE_LOG", "Cookies yang dikirim: $cookies")

        }
        return chain.proceed(builder.build())

    }
}
