package com.example.filmapplication.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.filmapplication.databinding.ActivitySplashScreenBinding
import com.example.filmapplication.ui.list.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val toMain = Intent(this@SplashScreenActivity, HomeActivity::class.java)
            startActivity(toMain)
            finish()
        },3000)
    }
}