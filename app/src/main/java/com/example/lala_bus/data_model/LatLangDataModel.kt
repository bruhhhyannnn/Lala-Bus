package com.example.lala_bus.data_model

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker

data class LatLangDataModel(
    val latLng: LatLng,
    val title: String,
    var marker: Marker? = null // Store marker reference
)