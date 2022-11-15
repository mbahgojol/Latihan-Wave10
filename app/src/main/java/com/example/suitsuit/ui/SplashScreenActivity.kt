package com.example.suitsuit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.example.suitsuit.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .into(binding.ivJudulSplash)

        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, LandingPageActivity::class.java).apply {
                startActivity(this)
            }
            finish()
        }, 3000)
    }
}