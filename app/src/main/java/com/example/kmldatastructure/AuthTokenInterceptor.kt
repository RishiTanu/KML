package com.example.kmldatastructure

import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor(private val tokenStorage: TokenStorage) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val authToken = tokenStorage.authToken
        if (authToken == null) {
            return chain.proceed(originalRequest)
        }

        // Add the token to the request header
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $authToken")
            .build()

        return chain.proceed(newRequest)
    }
}
