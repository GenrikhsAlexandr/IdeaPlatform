package com.aleksandrgenrikhs.ideaplatformtesttask

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        val intent = Intent(
            this@SplashActivity,
            MainActivity::class.java
        )
        startActivity(intent)
        finish()
    }
}