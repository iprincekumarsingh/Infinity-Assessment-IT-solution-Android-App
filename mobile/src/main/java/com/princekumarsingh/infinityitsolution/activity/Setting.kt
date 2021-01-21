package com.princekumarsingh.infinityitsolution.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.princekumarsingh.infinityitsolution.R
import android.widget.LinearLayout
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.widget.Toolbar

class Setting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val toolbar = findViewById<Toolbar>(R.id.hometoolbar)
        setSupportActionBar(toolbar)
        title = "Setting"
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
        clearcache()
    }

    private fun clearcache() {
        val cache = findViewById<LinearLayout>(R.id.clearcache)
        cache.setOnClickListener {
            val ii = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            ii.data = Uri.parse("package:$packageName")
            startActivity(ii)
        }
    }
}