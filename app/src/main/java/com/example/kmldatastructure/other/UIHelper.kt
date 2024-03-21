package com.example.kmldatastructure.other

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ProgressBar

class UIHelper(private val activity: Activity) {
    private var progressBar: ProgressBar? = null

    init {
        // Initialize the ProgressBar
        progressBar = ProgressBar(activity, null, android.R.attr.progressBarStyleLarge).apply {
            isIndeterminate = true
            visibility = View.GONE
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = android.view.Gravity.CENTER
            }
        }

        // Assuming the activity's root content view is a ViewGroup
        (activity.findViewById<View>(android.R.id.content) as? FrameLayout)?.addView(progressBar)
    }

    fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
        // Disable touch interactions in the entire window
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    fun hideProgressBar() {
        progressBar?.visibility = View.GONE
        // Re-enable touch interactions in the window
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}

