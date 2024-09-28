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
import com.example.lala_bus.data_model.MarkerData
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

        // Call Checked Stations
        checkFilteredStations(laoagPaoayRotationCheckBox, paoayLaoagRotationCheckBox)

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

    private fun checkFilteredStations(laoagPaoayRotation: CheckBox, paoayLaoagRotation: CheckBox) {
        val laoag_paoay_checked = if (laoagPaoayRotation.isChecked) "Laoag-Paoay" else "No Filter"
        val paoay_laoag_checked = if (paoayLaoagRotation.isChecked) "Paoay-Laoag" else "No Filter"
        Toast.makeText(context, laoag_paoay_checked, Toast.LENGTH_SHORT).show()
        Toast.makeText(context, paoay_laoag_checked, Toast.LENGTH_SHORT).show()
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
        val defaultLatitudeLongitude = LatLng(18.059362, 120.548539)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLatitudeLongitude, 16f))

        // Get station and stop point data
        val (modernJeepStations, rotationStopPoint_LaoagPaoay, rotationStopPoint_PaoayLaoag) = getStationsLatLong()

        // Add markers to the map
        addMarkersToMap(map, modernJeepStations) // adding modern jeep stations marker
        addMarkersToMap(map, rotationStopPoint_LaoagPaoay) // adding rotation stop points marker for Laoag-Batac-Paoay rotation
        addMarkersToMap(map, rotationStopPoint_PaoayLaoag) // adding rotation stop points marker for Paoay-Batac-Laoag rotation
    }

    private fun getStationsLatLong(): Triple<List<MarkerData>, List<MarkerData>, List<MarkerData>> {
        // Modern Jeep Permanent Stations
        val modernJeepStations = listOf(
            MarkerData(LatLng(18.195729, 120.590441), "Laoag Modern Jeep Station"),
            MarkerData(LatLng(18.051355, 120.518746), "Paoay Modern Jeep Station")
        )

        // Rotation Stop Points (Laoag-Batac-Paoay)
        val rotationStopPoint_LaoagPaoay = listOf(
            MarkerData(LatLng(18.186152, 120.588687), "Before Bridge of Laoag City"),
            MarkerData(LatLng(18.179402, 120.590341), "Robinsons Ilocos, San Nicolas"),
            MarkerData(LatLng(18.172783, 120.593965), "San Nicolas Municipality"),
            MarkerData(LatLng(18.153305, 120.585531), "Cockfight Arena, San Nicolas"),
            MarkerData(LatLng(18.140267, 120.584148), "San Lorenzo, San Nicolas"),
            MarkerData(LatLng(18.136204, 120.583580), "Bingao Elementary & National High School, San Nicolas"),
            MarkerData(LatLng(18.128952, 120.579599), "Brgy. Bingao, San Nicolas"),
            MarkerData(LatLng(18.116064, 120.572574), "Baay Elementary School, Batac"),
            MarkerData(LatLng(18.111539, 120.570348), "Batac National High School, Batac"),
            MarkerData(LatLng(18.108161, 120.568836), "Market Place, Brgy. Baay, Batac"),
            MarkerData(LatLng(18.088731, 120.568068), "GARSH and Bil-loca Elementary School, Batac"),
            MarkerData(LatLng(18.079227, 120.562914), "Rana-Ann Gas Station, Baligat, Batac"),
            MarkerData(LatLng(18.067465, 120.561356), "Arko Batac"),
            MarkerData(LatLng(18.065589, 120.562940), "Market Place, Batac"),
            MarkerData(LatLng(18.0567, 120.5647), "Pik A Bun (Centro), Batac"),
            MarkerData(LatLng(18.055136, 120.561324), "Cebuana Pawnshop (Centro), Batac"),
            MarkerData(LatLng(18.055791, 120.557112), "Eco-Oil Gas Station (Crossing), Batac"),
            MarkerData(LatLng(18.056060, 120.555191), "MMSU Gate 3, Batac"),
            MarkerData(LatLng(18.056383, 120.552828), "MMSU Teatro, Batac"),
            MarkerData(LatLng(18.057171, 120.548076), "MMSU Twin Gate, Batac"),
            MarkerData(LatLng(18.057863, 120.544007), "MMSU COE Shortcut, Batac"),
            MarkerData(LatLng(18.061506, 120.523962), "Salbang, Paoay"),
            MarkerData(LatLng(18.061527, 120.522527), "Paoay Church"),
            MarkerData(LatLng(18.060370, 120.521639), "Paoay Municipality")
        )

        // Rotation Stop Points (Paoay-Batac-Laoag)
        val rotationStopPoint_PaoayLaoag = listOf(
            MarkerData(LatLng(18.060268, 120.521710), "7-Eleven (Centro), Paoay"),
            MarkerData(LatLng(18.061411, 120.522567), "Paoay Church"),
            MarkerData(LatLng(18.061446, 120.524006), "Salbang, Paoay"),
            MarkerData(LatLng(18.057726, 120.544146), "Phil-Rice Research Institute, Batac"),
            MarkerData(LatLng(18.057144, 120.547645), "MMSU Twin Gate, Batac"),
            MarkerData(LatLng(18.056291, 120.552822), "MMSU Teatro, Batac"),
            MarkerData(LatLng(18.055938, 120.555326), "MMSU Gate 3, Batac"),
            MarkerData(LatLng(18.0556, 120.5575), "Crossing (Tealive), Batac"),
            MarkerData(LatLng(18.054762, 120.562627), "Jollibee, Batac"),
            MarkerData(LatLng(18.054760, 120.564350), "In-Front of City of Batac Municipality"),
            MarkerData(LatLng(18.056616, 120.565189), "Ricarte Park, Batac"),
            MarkerData(LatLng(18.066417, 120.562268), "Market Place, Batac"),
            MarkerData(LatLng(18.078790, 120.563053), "Rana-Ann Gas Station, Baligat, Batac"),
            MarkerData(LatLng(18.068090, 120.561330), "Arko Batac"),
            MarkerData(LatLng(18.088505, 120.568151), "GARSH and Bil-loca Elementary School, Batac"),
            MarkerData(LatLng(18.108253, 120.568959), "Market Place, Brgy. Baay, Batac"),
            MarkerData(LatLng(18.111380, 120.570396), "Batac National High School, Batac"),
            MarkerData(LatLng(18.115854, 120.572622), "Baay Elementary School, Batac"),
            MarkerData(LatLng(18.128701, 120.579635), "Brgy. Bingao, San Nicolas"),
            MarkerData(LatLng(18.136172, 120.583707), "Bingao Elementary & National High School, San Nicolas"),
            MarkerData(LatLng(18.140290, 120.584265), "San Lorenzo, San Nicolas"),
            MarkerData(LatLng(18.152961, 120.585532), "Cockfight Arena, San Nicolas"),
            MarkerData(LatLng(18.172889, 120.594202), "San Nicolas Municipality"),
            MarkerData(LatLng(18.179488, 120.590411), "Robinsons Ilocos, San Nicolas"),
            MarkerData(LatLng(18.185368, 120.588945), "Gabu Terminal, San Nicolas"),
            MarkerData(LatLng(18.196158, 120.592418), "Laoag City Municipality")
        )

        return Triple(modernJeepStations, rotationStopPoint_PaoayLaoag, rotationStopPoint_LaoagPaoay)
    }

    private fun addMarkersToMap(map: GoogleMap, markers: List<MarkerData>) {
        for (marker in markers) {
            map.addMarker(MarkerOptions().position(marker.latLng).title(marker.title))
        }
    }
}