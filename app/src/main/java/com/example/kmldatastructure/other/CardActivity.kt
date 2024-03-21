package com.example.kmldatastructure.other

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kmldatastructure.R
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CardActivity : AppCompatActivity() {
    private lateinit var uiHelper: UIHelper
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

        uiHelper = UIHelper(this)
        // Create a CoroutineScope with Dispatchers.Main
        val coroutineScope = CoroutineScope(Dispatchers.Main)

        // Launch a coroutine within the created scope
        coroutineScope.launch {
            // Perform some asynchronous operations
            delay(1000) // Simulating some background work

            // Update UI or perform other main thread related operations
            println("Coroutine finished on ${Thread.currentThread().name}")
            someOperation()
        }



        val spinnerItems = listOf(
            SpinnerItem("Item 1", R.drawable.ic_launcher_background), // Replace with actual data and drawable resources
            SpinnerItem("Item 2", R.drawable.ic_launcher_background),
            SpinnerItem("Item 2", R.drawable.ic_launcher_background),
            SpinnerItem("Item 2", R.drawable.ic_launcher_background)
            // Add more items as needed
        )

        val spinner: Spinner = findViewById(R.id.spinner_navigation)
        val adapter11 = CustomSpinnerAdapter(this, spinnerItems)
        spinner.adapter = adapter11

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                // Get the selected item
                val selectedItem = spinnerItems[position]

                // Handle the selection
                // Example: Show a Toast or update the UI based on the selection
                showToast("Selected: ${selectedItem.text}")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Optionally handle the case where nothing is selected, if needed
            }
        }
    }
    private fun showToast(message: String) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
    }
    suspend  fun someOperation() {
        uiHelper.showProgressBar()
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
      //  uiHelper.hideProgressBar()
        hide()
    }

    private suspend fun hide(){
        delay(5000)
        uiHelper.hideProgressBar()
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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