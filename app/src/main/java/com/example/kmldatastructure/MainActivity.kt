package com.example.kmldatastructure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    val jsonString = """{
        "locations": [
            {"name": "Location 1", "latitude": "40.689247", "longitude": "-74.044502"},
            {"name": "Location 2", "latitude": "48.858372", "longitude": "2.294481"}
        ]
    }"""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val kml = convertJsonToKml(jsonString)
        Log.d("TAG", "onCreate========== ${kml}")
    }

    /*val jsonString = // Your JSON string here
    val kmlData = jsonToKml(jsonString)
    sendKmlData(kmlData)*/
}