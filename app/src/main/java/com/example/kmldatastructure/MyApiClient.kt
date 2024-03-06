package com.example.kmldatastructure

import com.example.kmldatastructure.RetrofitClientInstance.retrofitInstance
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyApiClient {

    fun postXml(xmlData: String?) {
        val service = retrofitInstance!!.create(
            MyApiService::class.java
        )
        val requestBody = RequestBody.create(MediaType.parse("application/xml"), xmlData)
        val call: Call<ResponseBody> = service.postXmlData(requestBody)
        call.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                if (response.isSuccessful) {
                    // Handle success
                } else {
                    // Handle failure
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                // Handle failure
            }
        })
    }
}

