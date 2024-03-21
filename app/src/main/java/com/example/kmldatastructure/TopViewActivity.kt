package com.example.kmldatastructure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner

class TopViewActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner2)

        spinner = findViewById(R.id.spinner_navigation)
        listView = findViewById(R.id.listView_options)
        listView.visibility = View.GONE // Initially hide the ListView

        val categories =
            arrayOf("Select Category", "Students", "Teachers") // Added "Select Category" as default
        spinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Hide ListView when the default item is selected
                if (position == 0) {
                    listView.visibility = View.GONE
                } else {
                    // Show the ListView when any other item is selected
                    listView.visibility = View.VISIBLE
                    when (position) {
                        1 -> updateListViewWithStudents()
                        2 -> updateListViewWithTeachers()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                listView.visibility = View.GONE // Hide the ListView if nothing is selected
            }
        }
    }

    private fun updateListViewWithStudents() {
        val students = arrayOf("Alice", "Bob", "Charlie")
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, students)
    }

    private fun updateListViewWithTeachers() {
        val teachers = arrayOf("Professor X", "Teacher Y", "Instructor Z")
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, teachers)
    }
}