package com.example.kmldatastructure

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.text.SpannableString
import android.text.Spanned
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 2000 // Duration of wait in milliseconds
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

        richText()

        edit()
    }
    private fun edit(){
        val confirmPasswordInputLayout = findViewById<TextInputLayout>(R.id.textInputConfirmPasswordnew)
        val editTextConfirmPassword = findViewById<EditText>(R.id.editTextConfirmPasswordnew)

        confirmPasswordInputLayout.setEndIconOnClickListener() {
            // Toggle the password visibility
            if (editTextConfirmPassword.transformationMethod is PasswordTransformationMethod) {
                editTextConfirmPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                confirmPasswordInputLayout.endIconDrawable = ContextCompat.getDrawable(this, R.drawable.ic_action_name)
            } else {
                editTextConfirmPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                confirmPasswordInputLayout.endIconDrawable = ContextCompat.getDrawable(this, R.drawable.ic_action_close)
            }
            // Move cursor to the end
            editTextConfirmPassword.setSelection(editTextConfirmPassword.text?.length ?: 0)
        }
    }

    private fun richText(){
        val checkBoxAgree = findViewById<CheckBox>(R.id.checkbox_agree)
        val textTermsConditions = findViewById<TextView>(R.id.text_terms_conditions)

        val spannableString = SpannableString("I agree to the Terms & Conditions")
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Handle the click event here, for example, open a web page or a dialog
            }
        }

        val start = spannableString.indexOf("Terms & Conditions")
        val end = start + "Terms & Conditions".length


        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.blue)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannableString.setSpan(UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textTermsConditions.text = spannableString
        textTermsConditions.movementMethod = LinkMovementMethod.getInstance() // This makes the link clickable

        checkBoxAgree.setOnCheckedChangeListener { _, isChecked ->
            // Handle checkbox checked change
            if (isChecked) {
                Toast.makeText(this@MainActivity, "hello 3", Toast.LENGTH_SHORT).show()
            } else {
                // Unchecked logic
            }
        }

        // Optional: If you want the checkbox to toggle when the text is clicked
        textTermsConditions.setOnClickListener {
           // checkBoxAgree.isChecked = !checkBoxAgree.isChecked
        }
    }

    private fun spalsh(){
        // Start a coroutine
        GlobalScope.launch {
            delay(2000) // Wait for 2 seconds
            withContext(Dispatchers.Main) {

            }
        }
    }
}