package com.example.projectbem.Data.retrofit

import android.content.Context
import com.example.projectbem.Data.Cookie.AddCookiesInterceptor
import com.example.projectbem.Data.Cookie.ReceivedCookiesInterceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy


object ApiConfig {
    fun getApiService(context: Context): ApiService {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        val client = OkHttpClient.Builder()
            .cookieJar(JavaNetCookieJar(cookieManager))
            .addInterceptor(AddCookiesInterceptor(context))
            .addInterceptor(ReceivedCookiesInterceptor(context))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://profur.rikpetik.site/api/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}

