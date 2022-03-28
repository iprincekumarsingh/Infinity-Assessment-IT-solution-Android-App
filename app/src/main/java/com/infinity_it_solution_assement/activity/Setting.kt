package com.infinity_it_solution_assement.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.infinity_it_solution_assement.R
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
        twitter()
        facebook()
        instagram()
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
            privacyurl.data = Uri.parse("http://infinityitsolution.co.in/web/contact")
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
    private fun twitter(){
        val twitter = findViewById<LinearLayout>(R.id.twitter)
        twitter.setOnClickListener {
            val twiiter1 = Intent(Intent.ACTION_VIEW)
            twiiter1.data =Uri.parse("https://twitter.com/InfinitySolut18/")
            startActivity(twiiter1)

        }
    }
    private  fun facebook(){
        val contactt = findViewById<LinearLayout>(R.id.facebook)
//        val url ="http://mview.infinityitsolution.co.in/web/contact"
        contactt.setOnClickListener {
            val facebook1 = Intent(Intent.ACTION_VIEW)
            facebook1.data = Uri.parse("https://m.facebook.com/infinityassessmentanditsolution/")
            startActivity (facebook1)
        }
    }
    private fun instagram() {
        val cache = findViewById<LinearLayout>(R.id.instagram)
        cache.setOnClickListener {
            val instgram1 = Intent(Intent.ACTION_VIEW)
            instgram1.data = Uri.parse("https://www.instagram.com/infinityassessmentitsolution/?hl=en")
            startActivity (instgram1)
        }
    }
}