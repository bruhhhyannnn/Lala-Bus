package com.example.lala_bus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lala_bus.databinding.ActivityMainBinding
import com.example.lala_bus.main_fragments.HomeFragment
import com.example.lala_bus.main_fragments.StationFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        // Code Start

        replaceFragment(HomeFragment()) // Main Start of the app is to go to Home Fragment

        // Bottom Navigation Bar Fragment Replacement
        binding.bottomNavBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.stations -> replaceFragment(StationFragment())
                else -> {
                    replaceFragment(HomeFragment())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame_layout, fragment)
        fragmentTransaction.commit()
    }
}