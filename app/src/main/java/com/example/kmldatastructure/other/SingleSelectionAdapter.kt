import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.TextView
import com.example.kmldatastructure.R

class SingleSelectionAdapter(
    context: Context,
    private val objects: List<String>
) : ArrayAdapter<String>(context, 0, objects) {
    private var selectedPosition = -1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflate a custom layout for the currently selected item in the spinner
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false)
        if (view is TextView) {
            // Set the text of the spinner's selected view (not the dropdown) to reflect the current selection
            view.text = if (selectedPosition != -1) objects[selectedPosition] else ""
        }
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflate your custom layout for dropdown items
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_item_checkbox, parent, false)
        val checkBox = view.findViewById<CheckBox>(R.id.checkbox)
        checkBox.text = objects[position]
        checkBox.isChecked = position == selectedPosition

        // Since the checkboxes are disabled for user interaction, handle selection changes on the whole item view
        view.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
            // This will notify the spinner to update the selected item view outside of the dropdown
            // Close the dropdown. The actual method to close it depends on your spinner setup.
            val parentSpinner = parent as? Spinner
            parentSpinner?.performClick() // This might not directly close the dropdown; see note below
        }
        return view
    }

    fun setSelectedPosition(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }
}
