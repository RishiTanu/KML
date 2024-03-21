package com.example.kmldatastructure.other

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.kmldatastructure.R

class CustomSpinnerAdapter(context: Context, private val items: List<SpinnerItem>) :
    ArrayAdapter<SpinnerItem>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    private fun createItemView(position: Int, recycledView: View?, parent: ViewGroup): View {
        val item = getItem(position)
        val view = recycledView ?: LayoutInflater.from(context).inflate(R.layout.spinner_top, parent, false)

        val imageView = view.findViewById<ImageView>(R.id.image_view)
        val textView = view.findViewById<TextView>(R.id.text_view)

        item?.let {
            imageView.setImageDrawable(ContextCompat.getDrawable(context, it.imageRes))
            textView.text = it.text
        }

        return view
    }
}

data class SpinnerItem(val text: String, val imageRes: Int)
