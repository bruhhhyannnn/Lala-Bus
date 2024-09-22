package com.example.lala_bus.main_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lala_bus.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class HomeFragment : Fragment(), OnMapReadyCallback {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)


        return view
    }

    override fun onMapReady(map: GoogleMap) {
        val latitudeLongitude = LatLng(18.057205, 120.548080)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latitudeLongitude, 15f)) // not working
        map.addMarker(com.google.android.gms.maps.model.MarkerOptions().position(latitudeLongitude).title("Marker")) // not working

        // Map Actions
        map.uiSettings.isZoomControlsEnabled = true
    }
}