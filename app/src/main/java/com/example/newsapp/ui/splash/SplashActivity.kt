package com.example.newsapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivitySplashBinding
import com.example.newsapp.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        Handler(Looper.getMainLooper()).postDelayed(kotlinx.coroutines.Runnable {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }, 500)

    }
}