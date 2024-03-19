package com.example.kmldatastructure.other

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.TextView
import com.example.kmldatastructure.R
import com.google.android.material.tabs.TabLayout

class CardActivity : AppCompatActivity() {

    // Initialize an array to hold the check state of each spinner item
    private lateinit var itemCheckedStates: BooleanArray
    // Track the index of the currently selected item
    private var selectedItemIndex: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        val mySpinner: Spinner = findViewById(R.id.mySpinner)

        val items = listOf("Option 1", "Option 2", "Option 3")

        val adapter = object : ArrayAdapter<String>(this, R.layout.custom_spinner_item, R.id.textView, items) {
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, parent, false)
                val textView: TextView = view.findViewById(R.id.textView)
                val checkBox: CheckBox = view.findViewById(R.id.checkBox)

                textView.text = getItem(position)
                // CheckBox is visible only if the item is selected
                checkBox.isChecked = position == selectedItemIndex

                view.setOnClickListener {
                    selectedItemIndex = position
                    notifyDataSetChanged() // Notify to refresh the spinner view
                    mySpinner.setSelection(position) // Update the spinner selection
                    mySpinner.performClick() // Close the spinner dropdown
                }

                return view
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                // Customizing the spinner selected item view (the one that is always visible)
                val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, parent, false)
                val textView: TextView = view.findViewById(R.id.textView)
                val checkBox: CheckBox = view.findViewById(R.id.checkBox)
                textView.text = getItem(position)
                checkBox.visibility = View.GONE // Hide checkbox here
                return view
            }
        }

        mySpinner.adapter = adapter
        tabview()
    }

    private fun tabview(){

        val tabLayout: TabLayout = findViewById(R.id.tabLayout)

        // Add tabs
        tabLayout.apply {
            addTab(tabLayout.newTab().setText("Airplane"))
            addTab(tabLayout.newTab().setText("Helicopter"))
            addTab(tabLayout.newTab().setText("Drone"))
        }

        // Tab selection listener
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Here you can handle tab selection
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselection if needed
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselection if needed
            }
        })
    }

}