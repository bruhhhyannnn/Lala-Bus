package com.example.lala_bus.settings

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lala_bus.R
import com.example.lala_bus.databinding.ActivitySettingsBinding
import com.example.lala_bus.starters.SplashArtWelcomeActivity
import com.example.lala_bus.starters.StartNowActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.settingsProfile.setOnClickListener {
            // New activity on Users Profile
        }
        binding.settingsLocationServices.setOnClickListener {
            // New activity on Location Services Management
        }
        binding.settingsAbout.setOnClickListener {
            aboutDialog()
        }
        binding.settingsLogout.setOnClickListener {
            logoutDialog()
        }
    }

    private fun aboutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("About")
        builder.setMessage("Lorem Ipsum")
        val dialog = builder.create()
        dialog.show()
    }

    private fun logoutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to log out?")
        builder.setPositiveButton("Yes") { dialog, _ ->
            logout()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun logout() {
        // Logout function to be added here before going back to splash

        val intent = Intent(this, SplashArtWelcomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}