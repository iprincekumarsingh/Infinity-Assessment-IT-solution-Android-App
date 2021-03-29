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
        contacts()
        about()
        website()
        privacy()
    }

    private fun clearcache() {
        val cache = findViewById<LinearLayout>(R.id.clearcache)
        cache.setOnClickListener {
            val ii = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            ii.data = Uri.parse("package:$packageName")
            startActivity(ii)
        }
    }

    private  fun contacts(){
        val contactt = findViewById<LinearLayout>(R.id.contactt)
//        val url ="http://mview.infinityitsolution.co.in/web/contact"
        contactt.setOnClickListener {
            val privacyurl = Intent(android.content.Intent.ACTION_VIEW)
            privacyurl.data = Uri.parse("http://mview.infinityitsolution.co.in/web/contact")
            startActivity (privacyurl)
        }
    }


    private  fun about(){
        val contactt = findViewById<LinearLayout>(R.id.aboutt)
//        val url ="http://mview.infinityitsolution.co.in/web/about"
        contactt.setOnClickListener {
            val privacyurl = Intent(android.content.Intent.ACTION_VIEW)
            privacyurl.data = Uri.parse("http://infinityitsolution.co.in/web/about")
            startActivity (privacyurl)
        }
    }
    private  fun website(){
        val contactt = findViewById<LinearLayout>(R.id.websitee)
//        val url ="https://infinityitsolution.co.in"
        contactt.setOnClickListener {
            val ci = Intent(android.content.Intent.ACTION_VIEW)
            ci.data = Uri.parse("https://infinityitsolution.co.in")
            startActivity (ci)
        }
    }

    private  fun privacy(){
        val contactt = findViewById<LinearLayout>(R.id.privacy)
//        val url ="https://infinityitsolution.co.in/privacy/privacy.html"
        contactt.setOnClickListener {
           val privacyurl = Intent(android.content.Intent.ACTION_VIEW)
            privacyurl.data = Uri.parse("https://infinityitsolution.co.in/privacy/privacy.html")
            startActivity (privacyurl)
        }
    }
}