package com.infinity_it_solution_assement.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

/**
 *
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashscenond = 1000
        Handler().postDelayed({
            val splash = Intent(applicationContext, MainActivity::class.java)
            startActivity(splash)
            finish()
        }, splashscenond.toLong())
    }
}