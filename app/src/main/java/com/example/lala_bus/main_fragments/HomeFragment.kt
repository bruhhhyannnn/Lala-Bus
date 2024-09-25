package com.example.lala_bus.main_fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.lala_bus.R
import com.example.lala_bus.data_model.MarkerData
import com.example.lala_bus.settings.SettingsActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var filterButton : ImageButton
    private lateinit var settingsButton : ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        filterButton = view.findViewById(R.id.filter_button)
        settingsButton = view.findViewById(R.id.settings_button)

        filterButton.setOnClickListener {
            // Alert Dialog for filtering
        }
        settingsButton.setOnClickListener {
            startActivity(Intent(requireContext(), SettingsActivity::class.java))
        }

        return view
    }

    override fun onMapReady(map: GoogleMap) {
        // Map Actions
        map.uiSettings.isZoomControlsEnabled = true

        // Default Location
        val defaultLatitudeLongitude = LatLng(18.059362, 120.548539)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLatitudeLongitude, 16f))

        // Get station and stop point data
        val (modernJeepStations, rotationStopPoint_PaoayLaoag, rotationStopPoint_LaoagPaoay) = getStationsLatLong()

        // Add markers to the map
        addMarkersToMap(map, modernJeepStations) // adding modern jeep stations marker
        addMarkersToMap(map, rotationStopPoint_PaoayLaoag) // adding rotation stop points marker for Paoay-Batac-Laoag rotation
        addMarkersToMap(map, rotationStopPoint_LaoagPaoay) // adding rotation stop points marker for Laoag-Batac-Paoay rotation
    }

    private fun getStationsLatLong(): Triple<List<MarkerData>, List<MarkerData>, List<MarkerData>> {
        // Modern Jeep Permanent Stations
        val modernJeepStations = listOf(
            MarkerData(LatLng(18.195729, 120.590441), "Laoag Modern Jeep Station"),
            MarkerData(LatLng(18.060065, 120.520211), "Paoay Modern Jeep Station")
        )

        // Rotation Stop Points (Paoay-Batac-Laoag)
        val rotationStopPoint_PaoayLaoag = listOf(
            MarkerData(LatLng(18.060268, 120.521710), "7-Eleven, Paoay"),
            MarkerData(LatLng(18.061411, 120.522567), "Paoay Pick-Up Point (1)"),
            MarkerData(LatLng(18.061497, 120.524002), "Paoay Pick-Up Point (2)"),
            MarkerData(LatLng(18.057777, 120.543879), "Shortcut COE MMSU, Batac"),
            MarkerData(LatLng(18.057144, 120.547645), "Twin Gate MMSU, Batac"),
            MarkerData(LatLng(18.056291, 120.552822), "Teatro MMSU, Batac"),
            MarkerData(LatLng(18.055938, 120.555326), "Gate 3 MMSU, Batac"),
            MarkerData(LatLng(18.0556, 120.5575), "Crossing (Tealive), Batac"),
            MarkerData(LatLng(18.054762, 120.562627), "Jollibee, Batac"),
            MarkerData(LatLng(18.054760, 120.564350), "Centro (Empanadahan) Batac"),
            MarkerData(LatLng(18.066417, 120.562268), "Market Place, Batac"),
            MarkerData(LatLng(18.108253, 120.568959), "Baay Market Place, Batac"),
            MarkerData(LatLng(18.172889, 120.594202), "San Nicolas Municipality"),
            MarkerData(LatLng(18.179488, 120.590411), "Robinsons, San Nicolas"),
            MarkerData(LatLng(18.185368, 120.588945), "Gabu Terminal, San Nicolas"),
            MarkerData(LatLng(18.196158, 120.592418), "Laoag City Municipality")
        )

        // Rotation Stop Points (Laoag-Batac-Paoay)
        val rotationStopPoint_LaoagPaoay = listOf(
            MarkerData(LatLng(18.185338, 120.588861), "Gabu Terminal, San Nicolas"),
            MarkerData(LatLng(18.179402, 120.590341), "Robinsons, San Nicolas"),
            MarkerData(LatLng(18.172419, 120.593810), "7-Eleven, San Nicolas"),
            MarkerData(LatLng(18.140296, 120.584247), "Unknown Place"),
            MarkerData(LatLng(18.136200, 120.583531), "Bingao Elementary & National High School, San Nicolas"),
            MarkerData(LatLng(18.128695, 120.579486), "Brgy. Bingao, San Nicolas"),
            MarkerData(LatLng(18.115858, 120.572473), "Baay Elementary School, Batac"),
            MarkerData(LatLng(18.111380, 120.570396), "Batac National High School, Batac"),
            MarkerData(LatLng(18.108161, 120.568836), "Baay Market Place, Batac"),
            MarkerData(LatLng(18.088820, 120.568140), "GARSH and Bil-loca Elementary School, Batac"),
            MarkerData(LatLng(18.079645, 120.563124), "Rana-Ann Gas Station, Baligat, Batac"),
            MarkerData(LatLng(18.067465, 120.561356), "Arko Batac"),
            MarkerData(LatLng(18.065592, 120.562959), "Market Place, Batac"),
            MarkerData(LatLng(18.0567, 120.5647), "Pik A Bun, Batac"),
            MarkerData(LatLng(18.055136, 120.561324), "Cebuana Pawnshop, Batac"),
            MarkerData(LatLng(18.056060, 120.555191), "Gate 3 MMSU, Batac"),
            MarkerData(LatLng(18.056414, 120.552752), "Teatro MMSU, Batac"),
            MarkerData(LatLng(18.057171, 120.548076), "Twin Gate MMSU, Batac"),
            MarkerData(LatLng(18.057863, 120.544007), "Shortcut COE MMSU, Batac"),
            MarkerData(LatLng(18.061527, 120.522527), "Paoay Church"),
            MarkerData(LatLng(18.060370, 120.521639), "Paoay Municipality")
        )

        return Triple(modernJeepStations, rotationStopPoint_PaoayLaoag, rotationStopPoint_LaoagPaoay)
    }

    private fun addMarkersToMap(map: GoogleMap, markers: List<MarkerData>) {
        for (marker in markers) {
            map.addMarker(MarkerOptions().position(marker.latLng).title(marker.title))
        }
    }
}