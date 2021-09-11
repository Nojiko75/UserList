package com.example.userlist.ui.base

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    fun showLoading(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }

    fun hideLoading(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
    }
}