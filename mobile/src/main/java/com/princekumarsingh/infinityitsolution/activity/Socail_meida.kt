package com.princekumarsingh.infinityitsolution.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import com.princekumarsingh.infinityitsolution.R

class Socail_meida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socail_meida)

        val toolbar = findViewById<Toolbar>(R.id.hometoolbar)
        setSupportActionBar(toolbar)
        title = "Social-Media"
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
        instagram()
        facebook()
        twitter()

    }


    private fun instagram() {
        val cache = findViewById<LinearLayout>(R.id.instagram)
        cache.setOnClickListener {
            val instgram1 = Intent(Intent.ACTION_VIEW)
            instgram1.data = Uri.parse("https://www.instagram.com/infinityassessmentitsolution/?hl=en")
            startActivity (instgram1)
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

    private fun twitter(){
        val twitter = findViewById<LinearLayout>(R.id.twitter)
        twitter.setOnClickListener {
            val twiiter1 = Intent(Intent.ACTION_VIEW)
            twiiter1.data =Uri.parse("https://twitter.com/InfinitySolut18/")
            startActivity(twiiter1)

        }
    }

//    private fun youtube(){
//        val youtube = findViewById<LinearLayout>(R.id.youtube)
//        youtube.setOnClickListener {
//            val youtube1 = Intent(Intent(Intent.ACTION_VIEW))
//            youtube1.data=Uri.parse("")
//        }
//    }
}