package com.example.lala_bus.starters

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lala_bus.R
import com.example.lala_bus.authentication.SignInActivity
import com.example.lala_bus.authentication.SignUpActivity
import com.example.lala_bus.databinding.ActivityStartNowBinding

class StartNowActivity : AppCompatActivity() {

    private lateinit var binding : ActivityStartNowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartNowBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.signInButton.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
        binding.termsOfServiceText.setOnClickListener {
            termsOfServiceDialog()
        }
        binding.privacyPolicyText.setOnClickListener {
            privacyPolicyDialog()
        }
    }

    private fun termsOfServiceDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Terms Of Service")
        builder.setMessage("Lorem Ipsum")
        val dialog = builder.create()
        dialog.show()
    }

    private fun privacyPolicyDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Privacy Policy")
        builder.setMessage("Lorem Ipsum")
        val dialog = builder.create()
        dialog.show()
    }
}