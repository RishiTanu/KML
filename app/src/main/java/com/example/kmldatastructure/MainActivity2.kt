package com.example.kmldatastructure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    @Inject lateinit var tokenStorage: TokenStorage

    fun saveToken(token: String) {
        tokenStorage.authToken = token
    }

    fun clearToken() {
        tokenStorage.authToken = null
    }
}