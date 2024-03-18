package com.example.kmldatastructure.other

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import com.example.kmldatastructure.R

class MyCustomAdapter(context: Context, resource: Int, objects: List<String>) : ArrayAdapter<String>(context, resource, objects) {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val itemList = objects

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent, false)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent, true)
    }

    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup, isDropdown: Boolean): View {
        val view = convertView ?: inflater.inflate(R.layout.spinner_item_checkbox, parent, false)
        val checkBox = view.findViewById<CheckBox>(R.id.checkbox)
        checkBox.text = itemList[position]
        // If you need to handle checkbox changes, add listeners here

        // For dropdown views, you might want to adjust visibility or appearance
        if (isDropdown) {
            // Adjust if needed
        }

        return view
    }
}
