package com.example.kmldatastructure.log

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy

val cookieHandler = CookieManager().apply {
    setCookiePolicy(CookiePolicy.ACCEPT_ALL)
}

val okHttpClient = OkHttpClient.Builder()
    .cookieJar(SimpleCookieJar())
    .build()

val retrofit = Retrofit.Builder()
    .baseUrl("https://your.api.baseurl/")
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


class SimpleCookieJar : CookieJar {
    private val cookieStore: HashMap<String, List<Cookie>> = HashMap()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore[url.host] = cookies
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookieStore[url.host] ?: ArrayList()
    }
}
