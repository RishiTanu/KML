package com.example.kmldatastructure.geographical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.kmldatastructure.MyViewModel
import com.example.kmldatastructure.R
import com.example.kmldatastructure.Resource
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.data.kml.KmlLayer
import org.xmlpull.v1.XmlPullParserException
import java.io.ByteArrayInputStream
import java.io.IOException

class GeographicalMapDataActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val myViewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geographical_map_data)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        lifecycleScope.launchWhenStarted {
            myViewModel.postResult.collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        // Update your UI with the success result
                    }
                    is Resource.Error -> {
                        // Show the error message
                    }
                    is Resource.Loading -> {
                        // Optionally show a loading indicator
                    }
                }
            }
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }


    fun onMap(googleMap: GoogleMap) {
        mMap = googleMap

        // Assuming your KML data is stored in a String variable named kmlData
        val kmlData = """<?xml version="1.0" encoding="UTF-8"?> ... </kml>""" // Your KML data

        try {
            val kmlLayer = KmlLayer(mMap, ByteArrayInputStream(kmlData.toByteArray(Charsets.UTF_8)), applicationContext)
            kmlLayer.addLayerToMap()
            // Optionally, move the camera to the KML data bounds or a specific point
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        }
    }

}