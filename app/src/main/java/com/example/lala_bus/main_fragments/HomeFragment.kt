package com.example.lala_bus.main_fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.CheckBox
import android.widget.ImageButton
import com.example.lala_bus.R
import com.example.lala_bus.data_model.LatLangData
import com.example.lala_bus.data_model.LatLangDataModel
import com.example.lala_bus.settings.SettingsActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.button.MaterialButton

class HomeFragment : Fragment(), OnMapReadyCallback {

    // Maps Fragment
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var googleMap: GoogleMap

    // Home Fragment Buttons
    private lateinit var filterButton : ImageButton
    private lateinit var settingsButton : ImageButton

    // Search Bar
    // to be added

    // Filter Dialog
    private lateinit var dialog : Dialog
    private lateinit var cancelButton : MaterialButton
    private lateinit var applyFilterButton : MaterialButton
    private lateinit var laoagPaoayRotationCheckBox : CheckBox
    private lateinit var paoayLaoagRotationCheckBox : CheckBox

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        // Home Fragment Buttons
        filterButton = view.findViewById(R.id.filter_button)
        settingsButton = view.findViewById(R.id.settings_button)

        // Search Bar
        // to be added

        // Setup Map Fragment
        setupMapFragment()

        // Setup Filter Dialog
        setupFilterDialog()

        // Fragment Buttons
        filterButton.setOnClickListener {
            showFilterDialog()
        }
        settingsButton.setOnClickListener {
            startActivity(Intent(requireContext(), SettingsActivity::class.java))
        }

        return view
    }

    private fun setupFilterDialog() {
        // Filter Dialog
        dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_filter_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        cancelButton = dialog.findViewById(R.id.cancel_button)
        applyFilterButton = dialog.findViewById(R.id.apply_filter_button)
        laoagPaoayRotationCheckBox = dialog.findViewById(R.id.laoag_paoay_rotation_checkbox)
        paoayLaoagRotationCheckBox = dialog.findViewById(R.id.paoay_laoag_rotation_checkbox)
    }

    private fun setupMapFragment() {
        mapFragment = (childFragmentManager.findFragmentById(R.id.map_fragment) as? SupportMapFragment)!!
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map // initialization of the GoogleMap

        // Map Actions
        googleMap.uiSettings.isCompassEnabled = false
        googleMap.uiSettings.isZoomControlsEnabled = true
        // map.mapType = GoogleMap.MAP_TYPE_HYBRID  // i will fix this someday to use map type hybrid for betterness
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.map_style)) // the mapType is overwrite by this kase

        // Default Location
        val defaultLatitudeLongitude = LatLng(18.111380, 120.570396)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLatitudeLongitude, 12f))

        // Set Marker for Permanent Modern Jeep Stations
        val modernJeepStations = LatLangData.modernJeepStations
        addMarkersToMap(modernJeepStations)

        checkFilteredStations() // Check on the filtered stations when first running the app
    }

    private fun showFilterDialog() {
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        applyFilterButton.setOnClickListener {
            checkFilteredStations()
            dialog.dismiss()
        }
        dialog.show()
    }

     private fun checkFilteredStations() {
        val laoagPaoayRotation = laoagPaoayRotationCheckBox.isChecked
        val paoayLaoagRotation = paoayLaoagRotationCheckBox.isChecked
        val laoagPaoayStopPoints = LatLangData.laoagPaoayStopPoints
        val paoayLaoagStopPoints = LatLangData.paoayLaoagStopPoints

        if (laoagPaoayRotation) {
            addMarkersToMap(laoagPaoayStopPoints) // adding rotation stop points marker for Laoag-Batac-Paoay rotation
        }
        if (paoayLaoagRotation) {
            addMarkersToMap(paoayLaoagStopPoints) // adding rotation stop points marker for Paoay-Batac-Laoag rotation
        }
        if (!laoagPaoayRotation) {
            removeMarkersToMap(laoagPaoayStopPoints) // removing rotation stop points marker for Paoay-Batac-Laoag rotation
        }
        if (!paoayLaoagRotation) {
            removeMarkersToMap(paoayLaoagStopPoints) // removing rotation stop points marker for Paoay-Batac-Laoag rotation
        }
    }

    private fun addMarkersToMap(markers: List<LatLangDataModel>) {
        for (markerData in markers) {
            if (markerData.marker == null) {
                markerData.marker = googleMap.addMarker(MarkerOptions().position(markerData.latLng).title(markerData.title))
            }
        }
    }

    private fun removeMarkersToMap(markers: List<LatLangDataModel>) {
        for (markerData in markers) {
            markerData.marker?.remove()
            markerData.marker = null
        }
    }
}