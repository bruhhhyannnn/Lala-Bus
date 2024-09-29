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
import android.widget.Toast
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

    private fun showFilterDialog() {
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        applyFilterButton.setOnClickListener {
            Toast.makeText(context, "Clicked Apply Filter", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }

    private fun setupMapFragment() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        // Map Actions
        map.uiSettings.isCompassEnabled = false
        map.uiSettings.isZoomControlsEnabled = true
        // map.mapType = GoogleMap.MAP_TYPE_HYBRID  // i will fix this someday to use map type hybrid for betterness
        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.map_style)) // the mapType is overwrite by this kase

        // Default Location
        val defaultLatitudeLongitude = LatLng(18.111380, 120.570396)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLatitudeLongitude, 12f))

        // Check Filtered Stations
        checkFilteredStations()

        // Get station and stop point data
        val (modernJeepStations, rotationStopPoint_LaoagPaoay, rotationStopPoint_PaoayLaoag) = getStationsLatLong()

        // Add markers to the map
        addMarkersToMap(map, modernJeepStations) // adding modern jeep stations marker
        addMarkersToMap(map, rotationStopPoint_LaoagPaoay) // adding rotation stop points marker for Laoag-Batac-Paoay rotation
        addMarkersToMap(map, rotationStopPoint_PaoayLaoag) // adding rotation stop points marker for Paoay-Batac-Laoag rotation
    }

    private fun getStationsLatLong(): Triple<List<LatLangDataModel>, List<LatLangDataModel>, List<LatLangDataModel>> {
        val modernJeepStations = LatLangData.modernJeepStations
        val rotationStopPoints_LaoagPaoay = LatLangData.rotationStopPoint_LaoagPaoay
        val rotationStopPoints_PaoayLaoag = LatLangData.rotationStopPoint_PaoayLaoag

        return Triple(modernJeepStations, rotationStopPoints_LaoagPaoay, rotationStopPoints_PaoayLaoag)
    }

    private fun addMarkersToMap(map: GoogleMap, markers: List<LatLangDataModel>) {
        for (marker in markers) {
            map.addMarker(MarkerOptions().position(marker.latLng).title(marker.title))
        }
    }

    private fun checkFilteredStations() {
//        val laoagPaoayRotation = laoagPaoayRotationCheckBox.isChecked
//        val paoayLaoagRotation = paoayLaoagRotationCheckBox.isChecked
    }
}