package com.example.kmldatastructure

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class DrawerRightActivity2 : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_right2)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view_right)

        // Handling the back press to close the drawer if it's open
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val drawerView = findViewById<View>(R.id.nav_view_right)
                if (drawerLayout.isDrawerOpen(drawerView)) {
                    drawerLayout.closeDrawer(drawerView)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })

        // Example of how to open the drawer programmatically, e.g., when a button is clicked
        findViewById<View>(R.id.drawer_layout)?.setOnClickListener {
            drawerLayout.openDrawer(navigationView)
        }
    }

    private fun showCustomLogoutDialog() {
        // Create the custom dialog
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.logout)

        // Initialize dialog components
        val btnCancel: Button = dialog.findViewById(R.id.btnCancel)
        val btnOk: Button = dialog.findViewById(R.id.btnOk)

        // Close the dialog on "Cancel"
        btnCancel.setOnClickListener { dialog.dismiss() }

        // Perform logout logic and close dialog on "OK"
        btnOk.setOnClickListener {
            // Implement your logout logic here
           // logOutUser()
            dialog.dismiss()
        }

        dialog.show()
    }
}