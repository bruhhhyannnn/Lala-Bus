package com.example.lala_bus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lala_bus.databinding.ActivityMainBinding
import com.example.lala_bus.main_fragments.HomeFragment
import com.example.lala_bus.main_fragments.StationFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        // Code Start

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

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

    override fun onMapReady(map: GoogleMap) {
        val latitudeLongitude = LatLng(18.057205, 120.548080)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latitudeLongitude, 15f)) // not working
        map.addMarker(com.google.android.gms.maps.model.MarkerOptions().position(latitudeLongitude).title("Marker")) // not working

        // Possible Additions:
        map.uiSettings.isZoomGesturesEnabled = true
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isCompassEnabled = true
        map.uiSettings.isRotateGesturesEnabled = true
        map.uiSettings.isScrollGesturesEnabled = true
        map.uiSettings.isTiltGesturesEnabled = true
        map.uiSettings.isMyLocationButtonEnabled = true
        map.uiSettings.isMapToolbarEnabled = true
        map.uiSettings.isIndoorLevelPickerEnabled = true

    }
}