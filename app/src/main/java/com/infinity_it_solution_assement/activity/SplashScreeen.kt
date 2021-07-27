package com.infinity_it_solution_assement.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.infinity_it_solution_assement.R


class SplashScreeen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screeen)
        val splashscenond = 1500
        Handler().postDelayed({
            val splash = Intent(applicationContext, MainActivity::class.java)
            startActivity(splash)
            finish()
        }, splashscenond.toLong())
    }
}