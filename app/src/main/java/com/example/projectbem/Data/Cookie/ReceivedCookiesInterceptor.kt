package com.example.projectbem.Data.Cookie

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class ReceivedCookiesInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            val cookies = originalResponse.headers("Set-Cookie").toSet()
            val prefs = context.getSharedPreferences("COOKIE_PREFS", Context.MODE_PRIVATE)
            prefs.edit().putStringSet("cookies", cookies).apply()
        }
        return originalResponse
    }
}
