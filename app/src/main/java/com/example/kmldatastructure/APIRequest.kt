package com.example.kmldatastructure

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface MyApiService {
    @Headers("Content-Type: application/xml")
    @POST("your_endpoint")
    fun postXmlData(@Body body: RequestBody): Call<ResponseBody>
}