package com.example.kmldatastructure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MyViewModel : ViewModel() {
    private val retrofitInstance = Retrofit.Builder()
        .baseUrl("YOUR_BASE_URL")
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    // Assuming you have a Retrofit service defined as MyApiService
    private val myApiService = retrofitInstance.create(MyApiService::class.java)

    private val _postResult = MutableStateFlow<Resource<String>>(Resource.Loading())
    val postResult: StateFlow<Resource<String>> = _postResult

    fun postXml(xmlData: String) {
        viewModelScope.launch {
            val requestBody = xmlData.toRequestBody("application/xml".toMediaType())
            try {
                val response = myApiService.postXmlData(requestBody)
                if (response.isSuccessful) {
                    // Assuming the response body can be converted to a String
                    _postResult.value = Resource.Success(response.body()?.string() ?: "Success")
                } else {
                    _postResult.value = Resource.Error("Error Code: ${response.code()}")
                }
            } catch (e: Exception) {
                _postResult.value = Resource.Error(e.message ?: "An error occurred")
            }
        }
    }
}
