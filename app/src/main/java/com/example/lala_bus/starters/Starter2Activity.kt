package com.example.lala_bus.starters

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lala_bus.MainActivity
import com.example.lala_bus.R
import com.example.lala_bus.databinding.ActivityStarter2Binding

class Starter2Activity : AppCompatActivity() {

    private lateinit var binding : ActivityStarter2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStarter2Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Skip Button (to go to authentication)
        binding.skipButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // Continue Button
        binding.continueButton.setOnClickListener {
            startActivity(Intent(this, Starter3Activity::class.java))
        }
    }
}