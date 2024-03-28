package com.example.kmldatastructure

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor @Inject constructor(private val tokenRepository: TokenRepository) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()

        tokenRepository.getToken()?.let { token ->
            newRequest.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(newRequest.build())
    }
}
