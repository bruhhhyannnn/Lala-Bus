package com.example.lala_bus.authentication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lala_bus.R
import com.example.lala_bus.databinding.ActivityVerificationBinding
import com.example.lala_bus.starters.StartFinalActivity

class VerificationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.resendCodeText.setOnClickListener {
            // Resend Code functionality
        }
        binding.verifyButton.setOnClickListener {
            startActivity(Intent(this, StartFinalActivity::class.java))
        }
    }
}