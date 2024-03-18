package com.example.kmldatastructure.other

import SingleSelectionAdapter
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.kmldatastructure.R
import com.example.kmldatastructure.databinding.ActivitySpinnerBinding


class SpinnerActivity : AppCompatActivity() {
    private lateinit var mySpinner: Spinner
    private lateinit var adapter: SingleSelectionAdapter
    private lateinit var binding: ActivitySpinnerBinding
    private lateinit var notificationSwitch: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

      /*  val mySpinner: Spinner = findViewById(R.id.my_spinner)
        val spinnerItems = listOf("Option 1", "Option 2", "Option 3", "Option 4")

        // Setup custom adapter
        val adapter = MyCustomAdapter(this, R.layout.spinner_item_checkbox, spinnerItems)
        mySpinner.adapter = adapter*/

        dropDown();
        notification();
    }

    private fun dropDown(){
        val mySpinner = findViewById<Spinner>(R.id.my_spinner)
        val spinnerItems: List<String> =
            mutableListOf("Option 1", "Option 2", "Option 3", "Option 4")

        val adapter =
            SingleSelectionAdapter(this,  spinnerItems)
        mySpinner.adapter = adapter


        mySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                adapter.setSelectedPosition(position)
                // Spinner will close automatically upon selection
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun notification(){
        notificationSwitch = findViewById(R.id.notificationSwitch)

        // Initialize notification switch state
        val areNotificationsEnabled = getNotificationStateFromStorage() // Implement this function to get state from preferences or storage
        notificationSwitch.isChecked = areNotificationsEnabled

        // Set listener for switch toggle
        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Save notification state to preferences or storage
            saveNotificationStateToStorage(isChecked) // Implement this function to save state to preferences or storage

            if (isChecked) {
                // Enable notifications
                // Implement your notification enabling logic here
            } else {
                // Disable notifications
                // Implement your notification disabling logic here
            }
        }
    }

    private fun getNotificationStateFromStorage(): Boolean {
        // Implement logic to retrieve notification state from preferences or storage
        // Return true if notifications are enabled, false otherwise
        return false // Change this accordingly
    }

    private fun saveNotificationStateToStorage(isEnabled: Boolean) {
        // Implement logic to save notification state to preferences or storage
    }
}